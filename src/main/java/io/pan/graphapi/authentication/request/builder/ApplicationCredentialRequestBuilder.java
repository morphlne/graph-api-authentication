package io.pan.graphapi.authentication.request.builder;

import io.pan.graphapi.authentication.request.credential.ApplicationCredential;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest.TokenRequestBuilder;
import org.apache.oltu.oauth2.common.message.types.GrantType;

public class ApplicationCredentialRequestBuilder implements CredentialRequestBuilder {

  private final CredentialRequestBuilder builder;

  public ApplicationCredentialRequestBuilder(CredentialRequestBuilder builder) {
    this.builder = builder;
  }

  public ApplicationCredentialRequestBuilder(ApplicationCredential credential) {
    this(new DefaultCredentialRequestBuilder(credential));
  }

  @Override
  public TokenRequestBuilder get() {
    return builder.get().setGrantType(GrantType.CLIENT_CREDENTIALS);
  }
}
