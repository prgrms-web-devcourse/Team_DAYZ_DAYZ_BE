package com.dayz.common.jwt;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.apache.logging.log4j.util.Strings.isNotEmpty;

import com.dayz.member.service.MemberService;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;

public class JwtAuthenticationFilter extends GenericFilterBean {

  private final Logger log = LoggerFactory.getLogger(getClass());

  private final String headerKey;

  private final Jwt jwt;

  private final MemberService memberService;

  public JwtAuthenticationFilter(String headerKey, Jwt jwt, MemberService memberService) {
    this.headerKey = headerKey;
    this.jwt = jwt;
    this.memberService = memberService;
  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
    throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) res;

    if (SecurityContextHolder.getContext().getAuthentication() == null) {
      String token = getToken(request);
      if (token != null) {
        try {
          Jwt.Claims claims = verify(token);
          log.debug("Jwt parse result: {}", claims);

          String username = claims.username;
          Long id = claims.id;
          String providerId = claims.providerId;
          List<GrantedAuthority> authorities = getAuthorities(claims);

          if (isNotEmpty(username) && authorities.size() > 0) {
            JwtAuthenticationToken authentication =
              new JwtAuthenticationToken(new JwtAuthentication(id, providerId, token, username), null, authorities);
//            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            authentication.setDetails(memberService.findById(id));
            SecurityContextHolder.getContext().setAuthentication(authentication);
          }
        } catch (Exception e) {
          log.warn("Jwt processing failed: {}", e.getMessage());
        }
      }
    } else {
      log.debug("SecurityContextHolder not populated with security token, as it already contained: '{}'",
        SecurityContextHolder.getContext().getAuthentication());
    }

    chain.doFilter(request, response);
  }

  private String getToken(HttpServletRequest request) {
    String token = request.getHeader(headerKey);
    if (isNotEmpty(token)) {
      log.debug("Jwt authorization api detected: {}", token);
      try {
        return URLDecoder.decode(token, "UTF-8");
      } catch (UnsupportedEncodingException e) {
        log.error(e.getMessage(), e);
      }
    }
    return null;
  }

  private Jwt.Claims verify(String token) {
    return jwt.verify(token);
  }

  private List<GrantedAuthority> getAuthorities(Jwt.Claims claims) {
    String[] roles = claims.roles;
    return roles == null || roles.length == 0 ?
      emptyList() :
      Arrays.stream(roles).map(SimpleGrantedAuthority::new).collect(toList());
  }

}