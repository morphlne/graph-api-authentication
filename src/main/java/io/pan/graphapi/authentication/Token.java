package io.pan.graphapi.authentication;

import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;

import java.time.LocalDateTime;

public class Token implements TemporaryToken {

  private final String value;
  private final LocalDateTime expiration;
  private final int expirationBufferSeconds;

  public Token(String value, LocalDateTime expiration, int expirationBufferSeconds) {
    this.value = value;
    this.expiration = expiration;
    this.expirationBufferSeconds = expirationBufferSeconds;
  }

  public Token(OAuthJSONAccessTokenResponse response, int expirationBufferSeconds) {
    this(
        response.getAccessToken(),
        LocalDateTime.now().plusSeconds(response.getExpiresIn()),
        expirationBufferSeconds
    );
  }

  @Override
  public String value() {
    return value;
  }

  @Override
  public boolean expired() {
    return LocalDateTime.now().plusSeconds(expirationBufferSeconds).isAfter(expiration);
  }
}
