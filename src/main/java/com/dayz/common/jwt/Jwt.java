package com.dayz.common.jwt;

import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class Jwt {

  private final String issuer;

  private final String clientSecret;

  private final int expirySeconds;

  private final Algorithm algorithm;

  private final JWTVerifier jwtVerifier;

  public Jwt(String issuer, String clientSecret, int expirySeconds) {
    this.issuer = issuer;
    this.clientSecret = clientSecret;
    this.expirySeconds = expirySeconds;
    this.algorithm = Algorithm.HMAC512(clientSecret);
    this.jwtVerifier = com.auth0.jwt.JWT.require(algorithm)
      .withIssuer(issuer)
      .build();
  }

  public String sign(Claims claims) {
    Date now = new Date();
    JWTCreator.Builder builder = com.auth0.jwt.JWT.create();
    builder.withIssuer(issuer);
    builder.withIssuedAt(now);
    if (expirySeconds > 0) {
      builder.withExpiresAt(new Date(now.getTime() + expirySeconds * 1_000L));
    }
    builder.withClaim("username", claims.username);
    builder.withArrayClaim("roles", claims.roles);
    builder.withClaim("providerId", claims.providerId);
    return builder.sign(algorithm);
  }

  public Claims verify(String token) throws JWTVerificationException {
    return new Claims(jwtVerifier.verify(token));
  }

  public String getIssuer() {
    return issuer;
  }

  public String getClientSecret() {
    return clientSecret;
  }

  public int getExpirySeconds() {
    return expirySeconds;
  }

  public Algorithm getAlgorithm() {
    return algorithm;
  }

  public JWTVerifier getJwtVerifier() {
    return jwtVerifier;
  }

  static public class Claims {
    String username;
    String[] roles;
    Date iat;
    Date exp;
    Long id;
    String providerId;

    private Claims() {/*no-op*/}

    Claims(DecodedJWT decodedJWT) {
      Claim username = decodedJWT.getClaim("username");
      if (!username.isNull())
        this.username = username.asString();
      Claim roles = decodedJWT.getClaim("roles");
      if (!roles.isNull()) {
        this.roles = roles.asArray(String.class);
      }
      this.iat = decodedJWT.getIssuedAt();
      this.exp = decodedJWT.getExpiresAt();
      Claim id = decodedJWT.getClaim("id");
      if (!id.isNull())
        this.id = id.asLong();
      Claim providerId = decodedJWT.getClaim("providerId");
      if (!id.isNull())
        this.providerId = id.asString();
    }

    public static Claims from(Long id, String providerId, String username, String[] roles) {
      Claims claims = new Claims();
      claims.username = username;
      claims.roles = roles;
      claims.id = id;
      claims.providerId = providerId;

      return claims;
    }

    public Map<String, Object> asMap() {
      Map<String, Object> map = new HashMap<>();
      map.put("username", username);
      map.put("roles", roles);
      map.put("iat", iat());
      map.put("exp", exp());
      map.put("id", "id");
      map.put("providerId", "providerId");
      return map;
    }

    long iat() {
      return iat != null ? iat.getTime() : -1;
    }

    long exp() {
      return exp != null ? exp.getTime() : -1;
    }

    void eraseIat() {
      iat = null;
    }

    void eraseExp() {
      exp = null;
    }

  }

}
