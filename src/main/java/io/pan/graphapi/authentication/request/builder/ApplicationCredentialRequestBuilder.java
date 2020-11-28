package io.pan.graphapi.authentication.request.builder;

import io.pan.graphapi.authentication.request.credential.ApplicationCredential;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest.TokenRequestBuilder;
import org.apache.oltu.oauth2.common.message.types.GrantType;

public class ApplicationCredentialRequestBuilder implements CredentialRequestBuilder {

  private final ApplicationCredential credential;

  public ApplicationCredentialRequestBuilder(ApplicationCredential credential) {
    this.credential = credential;
  }

  @Override
  public TokenRequestBuilder get() {
    return OAuthClientRequest.tokenLocation(credential.tokenUrl().toString())
        .setClientId(credential.clientId())
        .setClientSecret(credential.clientSecret())
        .setGrantType(GrantType.CLIENT_CREDENTIALS)
        .setScope(
            String.join(
                " ",
                credential.scopes()
            )
        );
  }
}
