package com.dayz.common.oauth2;

import com.dayz.common.dto.ApiResponse;
import com.dayz.common.jwt.Jwt;
import com.dayz.member.domain.Member;
import com.dayz.member.service.MemberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class OAuth2AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final Jwt jwt;

    private final MemberService memberService;

    public OAuth2AuthenticationSuccessHandler(Jwt jwt, MemberService memberService) {
        this.jwt = jwt;
        this.memberService = memberService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        /**
         * JWT
         */
        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauth2Token = (OAuth2AuthenticationToken) authentication;
            OAuth2User principal = oauth2Token.getPrincipal();
            String registrationId = oauth2Token.getAuthorizedClientRegistrationId();

            Member member = processUserOAuth2UserJoin(principal, registrationId);
            String loginSuccessJson = generateLoginSuccessJson(member);
            response.setContentType("application/json;charset=UTF-8");
            response.setContentLength(loginSuccessJson.getBytes(StandardCharsets.UTF_8).length);
            response.getWriter().write(loginSuccessJson);
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }

    private Member processUserOAuth2UserJoin(OAuth2User oAuth2User, String registrationId) {
        return memberService.join(oAuth2User, registrationId);
    }

    private String generateLoginSuccessJson(Member member) throws JsonProcessingException {
        String token = generateToken(member);
        ObjectMapper mapper = new ObjectMapper();
        ApiResponse<Map<String, String>> response = ApiResponse.ok(Map.of("token", token));
        String responseString = mapper.writeValueAsString(response);
        log.debug("Jwt({}) created for oauth2 login user {}", token, member.getUsername());
        return responseString;
    }

    private String generateToken(Member member) {
        return jwt.sign(Jwt.Claims.from(member.getUsername(), new String[]{member.getPermission().getName()}, member.getProviderId()));
    }

}
