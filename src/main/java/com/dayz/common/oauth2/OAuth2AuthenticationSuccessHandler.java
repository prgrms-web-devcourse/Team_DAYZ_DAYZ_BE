package com.dayz.common.oauth2;

import com.dayz.common.dto.ApiResponse;
import com.dayz.common.jwt.Jwt;
import com.dayz.member.domain.Member;
import com.dayz.member.service.MemberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.web.util.UriComponentsBuilder;

public class OAuth2AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    public final String REDIRECT_URI_PARAM_COOKIE_NAME = "redirect_uri";
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final Jwt jwt;

    private final MemberService memberService;

    public OAuth2AuthenticationSuccessHandler(Jwt jwt, MemberService memberService) {
        this.jwt = jwt;
        this.memberService = memberService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws ServletException, IOException {
        /**
         * JWT
         */
        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauth2Token = (OAuth2AuthenticationToken) authentication;
            OAuth2User principal = oauth2Token.getPrincipal();
            String registrationId = oauth2Token.getAuthorizedClientRegistrationId();

            Member member = processUserOAuth2UserJoin(principal, registrationId);
            String token = generateToken(member);

            String targetUrl = determineTargetUrl(request, response, token, member);
            getRedirectStrategy().sendRedirect(request, response, targetUrl);
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }

    }

    private Member processUserOAuth2UserJoin(OAuth2User oAuth2User, String registrationId) {
        return memberService.join(oAuth2User, registrationId);
    }

    private String generateToken(Member member) {
        return jwt
                .sign(Jwt.Claims.from(member.getId(), member.getProviderId(), member.getUsername(), new String[]{member.getPermission().getName()}));
    }

    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, String jwtToken, Member member) {

        Optional<String> redirectUri = getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME).map(Cookie::getValue);

//        if(redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
//            throw new IllegalArgumentException("Sorry! We've got an Unauthorized Redirect URI and can't proceed with the authentication");
//        }

        String targetUrl = redirectUri.orElse(getDefaultTargetUrl());

        String auth = member.getPermission().getName();
        boolean addressInfoFlag = !Objects.isNull(member.getAddress());
        boolean atelierInfoFlag = !Objects.isNull(member.getAtelier());

        return UriComponentsBuilder.fromUriString(targetUrl)
                .queryParam("token", jwtToken)
                .queryParam("addressInfoFlag", addressInfoFlag)
                .queryParam("atelierInfoFlag", atelierInfoFlag)
                .queryParam("auth", auth)
                .build().toUriString();
    }

    public Optional<Cookie> getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return Optional.of(cookie);
                }
            }
        }
        return Optional.empty();
    }

    private String generateLoginSuccessJson(Member member) throws JsonProcessingException {
        String token = generateToken(member);
        ObjectMapper mapper = new ObjectMapper();
        ApiResponse<Map<String, String>> response = ApiResponse.ok(Map.of("token", token));
        String responseString = mapper.writeValueAsString(response);
        log.debug("Jwt({}) created for oauth2 login user {}", token, member.getUsername());
        return responseString;
    }

}
