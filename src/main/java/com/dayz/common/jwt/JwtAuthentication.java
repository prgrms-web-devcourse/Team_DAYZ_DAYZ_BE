package com.dayz.common.jwt;

import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public class JwtAuthentication {

  public final Long id;

  public final String token;

  public final String username;

  public final String providerId;

  JwtAuthentication(Long id, String providerId, String token, String username) {
//    checkArgument(isNotEmpty(token), "token must be provided.");
//    checkArgument(isNotEmpty(username), "username must be provided.");
    Assert.notNull(id, "id must not be null!");
    Assert.notNull(token, "token must not be null!");
    Assert.notNull(username, "username must not be null!");
    Assert.notNull(providerId, "providerId must not be null!");

    this.id = id;
    this.token = token;
    this.username = username;
    this.providerId = providerId;
  }

}
