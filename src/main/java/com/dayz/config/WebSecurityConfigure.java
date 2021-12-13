package com.dayz.config;

import com.dayz.common.jwt.Jwt;
import com.dayz.common.jwt.JwtAuthenticationFilter;
import com.dayz.common.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import com.dayz.common.oauth2.OAuth2AuthenticationSuccessHandler;
import com.dayz.member.service.MemberService;
import java.util.Arrays;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigure extends WebSecurityConfigurerAdapter {

  private final Logger log = LoggerFactory.getLogger(getClass());

  private final JwtConfigure jwtConfigure;

  private final MemberService memberService;

  private final CorsProperties corsProperties;

  public WebSecurityConfigure(JwtConfigure jwtConfigure, MemberService memberService, CorsProperties corsProperties) {
    this.jwtConfigure = jwtConfigure;
    this.memberService = memberService;
    this.corsProperties = corsProperties;
  }

  @Override
  public void configure(WebSecurity web) {
    web.ignoring().antMatchers("/assets/**", "/h2-console/**");
  }

  @Bean
  public AccessDeniedHandler accessDeniedHandler() {
    return (request, response, e) -> {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      Object principal = authentication != null ? authentication.getPrincipal() : null;
      log.warn("{} is denied", principal, e);
      response.setStatus(HttpServletResponse.SC_FORBIDDEN);
      response.setContentType("text/plain;charset=UTF-8");
      response.getWriter().write("ACCESS DENIED");
      response.getWriter().flush();
      response.getWriter().close();
    };
  }

  @Bean
  public Jwt jwt() {
    return new Jwt(
            jwtConfigure.getIssuer(),
            jwtConfigure.getClientSecret(),
            jwtConfigure.getExpirySeconds()
    );
  }

////////////////////////

  public JwtAuthenticationFilter jwtAuthenticationFilter() {
    Jwt jwt = getApplicationContext().getBean(Jwt.class);
    return new JwtAuthenticationFilter(jwtConfigure.getHeader(), jwt, memberService);
  }

  @Bean
  public OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler() {
    Jwt jwt = getApplicationContext().getBean(Jwt.class);
    return new OAuth2AuthenticationSuccessHandler(jwt, memberService);
  }

  @Bean
  public AuthorizationRequestRepository<OAuth2AuthorizationRequest> authorizationRequestRepository() {
    return new HttpCookieOAuth2AuthorizationRequestRepository();
  }

//  @Bean
//  public OAuth2AuthorizedClientService authorizedClientService(
//          JdbcOperations jdbcOperations,
//          ClientRegistrationRepository clientRegistrationRepository
//  ) {
//    return new JdbcOAuth2AuthorizedClientService(jdbcOperations, clientRegistrationRepository);
//  }
//
//  @Bean
//  public OAuth2AuthorizedClientRepository authorizedClientRepository(OAuth2AuthorizedClientService authorizedClientService) {
//    return new AuthenticatedPrincipalOAuth2AuthorizedClientRepository(authorizedClientService);
//  }

  /*
   * Cors 설정
   * */
  @Bean
  public UrlBasedCorsConfigurationSource corsConfigurationSource() {
    UrlBasedCorsConfigurationSource corsConfigSource = new UrlBasedCorsConfigurationSource();

    CorsConfiguration corsConfig = new CorsConfiguration();
    corsConfig.setAllowedHeaders(Arrays.asList(corsProperties.getAllowedHeaders().split(",")));
    corsConfig.setAllowedMethods(Arrays.asList(corsProperties.getAllowedMethods().split(",")));
    corsConfig.setAllowedOrigins(Arrays.asList(corsProperties.getAllowedOrigins().split(",")));
    corsConfig.setAllowCredentials(true);
    corsConfig.setMaxAge(corsConfig.getMaxAge());

    corsConfigSource.registerCorsConfiguration("/**", corsConfig);
    return corsConfigSource;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .cors()
            .and()
            .authorizeRequests()
//            .antMatchers("/api/v1/login").permitAll()
//            .antMatchers("/api/v1/*").hasAnyRole("USER", "ATELIER","ADMIN")
            .anyRequest().permitAll()
            .and()
            /**
             * formLogin, csrf, headers, http-basic, rememberMe, logout filter 비활성화
             */
            .formLogin()
            .disable()
            .csrf()
            .disable()
            .headers()
            .disable()
            .httpBasic()
            .disable()
            .rememberMe()
            .disable()
            .logout()
            .disable()
            /**
             * Session 사용하지 않음
             */
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            /**
             * 예외처리 핸들러
             */
            .exceptionHandling()
            .accessDeniedHandler(accessDeniedHandler())
            .and()
            /**
             * OAuth
             */
            .oauth2Login()
              .authorizationEndpoint()
                .authorizationRequestRepository(authorizationRequestRepository())
              .and()
//            .authorizedClientRepository(getApplicationContext().getBean(AuthenticatedPrincipalOAuth2AuthorizedClientRepository.class))
            .successHandler(oAuth2AuthenticationSuccessHandler())
            .and()
            /**
             * jwtAuthenticationFilter 추가
             */
            .addFilterAfter(jwtAuthenticationFilter(), SecurityContextPersistenceFilter.class)
    ;
  }

}

