package io.pan.graphapi.authentication.request.builder;

import org.apache.oltu.oauth2.client.request.OAuthClientRequest.TokenRequestBuilder;

import java.util.function.Supplier;

public interface CredentialRequestBuilder extends Supplier<TokenRequestBuilder> {
}
