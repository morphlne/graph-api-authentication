package io.pan.graphapi.authentication.request.builder;

import io.pan.graphapi.authentication.request.credential.Credential;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;

public class DefaultCredentialRequestBuilder implements CredentialRequestBuilder {

  private final Credential credential;

  public DefaultCredentialRequestBuilder(Credential credential) {
    this.credential = credential;
  }

  @Override
  public OAuthClientRequest.TokenRequestBuilder get() {
    return OAuthClientRequest.tokenLocation(new TokenUrl(credential).get())
        .setClientId(credential.clientId())
        .setClientSecret(credential.clientSecret())
        .setScope(
            String.join(
                " ",
                credential.scopes()
            )
        );
  }
}
