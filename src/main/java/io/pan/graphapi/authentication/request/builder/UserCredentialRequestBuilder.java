package io.pan.graphapi.authentication.request.builder;

import io.pan.graphapi.authentication.request.credential.UserCredential;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest.TokenRequestBuilder;
import org.apache.oltu.oauth2.common.message.types.GrantType;

public class UserCredentialRequestBuilder implements CredentialRequestBuilder {

  private final UserCredential credential;
  private final CredentialRequestBuilder builder;

  public UserCredentialRequestBuilder(UserCredential credential, CredentialRequestBuilder builder) {
    this.credential = credential;
    this.builder = builder;
  }

  public UserCredentialRequestBuilder(UserCredential credential) {
    this(
        credential,
        new DefaultCredentialRequestBuilder(credential)
    );
  }

  @Override
  public TokenRequestBuilder get() {
    return builder.get()
        .setUsername(credential.user())
        .setPassword(credential.password())
        .setGrantType(GrantType.PASSWORD);
  }
}
