package io.pan.graphapi.authentication.request.builder;

import io.pan.graphapi.authentication.request.credential.UserCredential;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest.TokenRequestBuilder;
import org.apache.oltu.oauth2.common.message.types.GrantType;

public class UserCredentialRequestBuilder implements CredentialRequestBuilder {

  private final UserCredential credential;

  public UserCredentialRequestBuilder(UserCredential credential) {
    this.credential = credential;
  }

  @Override
  public TokenRequestBuilder get() {
    return OAuthClientRequest.tokenLocation(new TokenUrl(credential).get())
        .setClientId(credential.clientId())
        .setClientSecret(credential.clientSecret())
        .setUsername(credential.user())
        .setPassword(credential.password())
        .setGrantType(GrantType.PASSWORD)
        .setScope(
            String.join(
                " ",
                credential.scopes()
            )
        );
  }
}
