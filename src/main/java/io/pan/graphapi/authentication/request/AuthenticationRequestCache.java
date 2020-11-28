package io.pan.graphapi.authentication.request;

import io.pan.graphapi.authentication.request.builder.CredentialRequestBuilder;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

import java.util.function.Supplier;

public class AuthenticationRequestCache implements Supplier<OAuthClientRequest> {

  private final CredentialRequestBuilder source;
  private OAuthClientRequest request;

  public AuthenticationRequestCache(CredentialRequestBuilder source) {
    this.source = source;
  }

  @Override
  public OAuthClientRequest get() {
    if (request == null) {
      request = buildRequest();
    }
    return request;
  }

  private OAuthClientRequest buildRequest() {
    try {
      return source.get().buildBodyMessage();
    } catch (OAuthSystemException e) {
      throw new AuthenticationRequestCreatingException(e);
    }
  }
}
