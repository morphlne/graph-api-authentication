package io.pan.graphapi.authentication;

import io.pan.graphapi.authentication.request.AuthenticationRequestCache;
import io.pan.graphapi.authentication.request.builder.ApplicationCredentialRequestBuilder;
import io.pan.graphapi.authentication.request.builder.UserCredentialRequestBuilder;
import io.pan.graphapi.authentication.request.credential.ApplicationCredential;
import io.pan.graphapi.authentication.request.credential.UserCredential;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;

import java.util.function.Supplier;

public class TokenCache implements Supplier<String> {

  private final Supplier<Token> tokenFactory;
  private Token token;

  public TokenCache(Supplier<Token> tokenFactory) {
    this.tokenFactory = tokenFactory;
  }

  public TokenCache(Supplier<Token> tokenFactory, Token token) {
    this.tokenFactory = tokenFactory;
    this.token = token;
  }

  public TokenCache(ApplicationCredential credential) {
    this(credential, 1);
  }

  public TokenCache(UserCredential credential) {
    this(credential, 1);
  }

  public TokenCache(ApplicationCredential credential, int expirationBufferSeconds) {
    this(
        new TokenFactory(
            new AuthenticationRequestCache(
                new ApplicationCredentialRequestBuilder(credential)
            ),
            new OAuthClient(
                new URLConnectionClient()
            ),
            expirationBufferSeconds
        )
    );
  }

  public TokenCache(UserCredential credential, int expirationBufferSeconds) {
    this(
        new TokenFactory(
            new AuthenticationRequestCache(
                new UserCredentialRequestBuilder(credential)
            ),
            new OAuthClient(
                new URLConnectionClient()
            ),
            expirationBufferSeconds
        )
    );
  }

  @Override
  public String get() {
    return token == null || token.expired() ?
        generate() :
        value();
  }

  private String generate() {
    token = tokenFactory.get();
    return value();
  }

  private String value() {
    return token.value();
  }
}
