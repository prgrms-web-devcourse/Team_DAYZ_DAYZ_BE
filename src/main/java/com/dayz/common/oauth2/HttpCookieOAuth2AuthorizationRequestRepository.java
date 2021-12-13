package com.dayz.common.oauth2;

import static java.util.Optional.ofNullable;

import com.nimbusds.oauth2.sdk.util.StringUtils;
import java.util.Base64;
import java.util.Optional;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.util.SerializationUtils;
import org.springframework.web.util.WebUtils;

public class HttpCookieOAuth2AuthorizationRequestRepository implements AuthorizationRequestRepository<OAuth2AuthorizationRequest> {

  private static final String OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME = "OAUTH2_AUTHORIZATION_REQUEST";
  public static final String REDIRECT_URI_PARAM_COOKIE_NAME = "redirect_uri";
  private static final int COOKIE_EXPIRE_SECONDS = 180;

  private final String authorizationRequestCookieName;

  private final String redirectUri;

  private final int cookieExpireSeconds;

  public HttpCookieOAuth2AuthorizationRequestRepository() {
    this(OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME, REDIRECT_URI_PARAM_COOKIE_NAME, COOKIE_EXPIRE_SECONDS);
  }

  public HttpCookieOAuth2AuthorizationRequestRepository(String authorizationRequestCookieName, String redirectUri, int cookieExpireSeconds) {
    this.authorizationRequestCookieName = authorizationRequestCookieName;
    this.redirectUri = redirectUri;
    this.cookieExpireSeconds = cookieExpireSeconds;
  }

  @Override
  public OAuth2AuthorizationRequest loadAuthorizationRequest(HttpServletRequest request) {
    return getCookie(request,authorizationRequestCookieName)
            .map(this::getOAuth2AuthorizationRequest)
            .orElse(null);
  }

  @Override
  public void saveAuthorizationRequest(OAuth2AuthorizationRequest authorizationRequest, HttpServletRequest request, HttpServletResponse response) {
    if (authorizationRequest == null) {
      getCookie(request, authorizationRequestCookieName).ifPresent(cookie -> clear(cookie, response));
      getCookie(request, redirectUri).ifPresent(cookie -> clear(cookie, response));
    } else {
      String value = Base64.getUrlEncoder().encodeToString(SerializationUtils.serialize(authorizationRequest));
      addCookie(response, authorizationRequestCookieName, value, cookieExpireSeconds);
      String redirectUriAfterLogin = request.getParameter(REDIRECT_URI_PARAM_COOKIE_NAME);
      if (StringUtils.isNotBlank(redirectUriAfterLogin)) {
        addCookie(response, redirectUri, redirectUriAfterLogin, cookieExpireSeconds);
      }
    }
  }

  @Override
  public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest request) {
    return loadAuthorizationRequest(request);
  }

  @Override
  public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest request, HttpServletResponse response) {
    return getCookie(request, authorizationRequestCookieName)
            .map(cookie -> {
              OAuth2AuthorizationRequest oauth2Request = getOAuth2AuthorizationRequest(cookie);
              clear(cookie, response);
              return oauth2Request;
            })
            .orElse(null);
  }

  private Optional<Cookie> getCookie(HttpServletRequest request, String name) {

    return ofNullable(WebUtils.getCookie(request, name));
  }

  public void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
    Cookie cookie = new Cookie(name, value);
    cookie.setPath("/");
    cookie.setHttpOnly(true);
    cookie.setMaxAge(maxAge);

    response.addCookie(cookie);
  }

  public void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
    Cookie[] cookies = request.getCookies();

    if (cookies != null && cookies.length > 0) {
      for (Cookie cookie : cookies) {
        if (name.equals(cookie.getName())) {
          cookie.setValue("");
          cookie.setPath("/");
          cookie.setMaxAge(0);
          response.addCookie(cookie);
        }
      }
    }
  }

  private void clear(Cookie cookie, HttpServletResponse response) {
    cookie.setValue("");
    cookie.setPath("/");
    cookie.setMaxAge(0);
    response.addCookie(cookie);
  }

  private OAuth2AuthorizationRequest getOAuth2AuthorizationRequest(Cookie cookie) {
    return (OAuth2AuthorizationRequest) SerializationUtils.deserialize(
            Base64.getUrlDecoder().decode(cookie.getValue())
    );
  }

  public void removeAuthorizationRequestCookies(HttpServletRequest request, HttpServletResponse response) {
    deleteCookie(request, response, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME);
    deleteCookie(request, response, REDIRECT_URI_PARAM_COOKIE_NAME);
  }

}
