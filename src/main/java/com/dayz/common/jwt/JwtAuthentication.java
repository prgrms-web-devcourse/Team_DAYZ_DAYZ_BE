package com.dayz.common.jwt;

import lombok.Getter;

@Getter
public class JwtAuthentication {

  public final String token;

  public final String username;

  public final String providerId;

  JwtAuthentication(String token, String username, String providerId) {
//    checkArgument(isNotEmpty(token), "token must be provided.");
//    checkArgument(isNotEmpty(username), "username must be provided.");

    this.token = token;
    this.username = username;
    this.providerId = providerId;
  }

}
