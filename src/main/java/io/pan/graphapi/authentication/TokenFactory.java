package io.pan.graphapi.authentication;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

import java.util.function.Supplier;

public class TokenFactory implements Supplier<Token> {

  private final Supplier<OAuthClientRequest> requestFactory;
  private final OAuthClient client;
  private final int expirationBufferSeconds;

  public TokenFactory(Supplier<OAuthClientRequest> requestFactory, OAuthClient client, int expirationBufferSeconds) {
    this.requestFactory = requestFactory;
    this.client = client;
    this.expirationBufferSeconds = expirationBufferSeconds;
  }

  @Override
  public Token get() {
    try {
      return new Token(client.accessToken(requestFactory.get()), expirationBufferSeconds);
    } catch (OAuthSystemException | OAuthProblemException e) {
      throw new NoTokenException(e);
    }
  }
}
