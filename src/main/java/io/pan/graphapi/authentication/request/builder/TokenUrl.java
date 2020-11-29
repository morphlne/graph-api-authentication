package io.pan.graphapi.authentication.request.builder;

import io.pan.graphapi.authentication.request.credential.Credential;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.function.Supplier;

public class TokenUrl implements Supplier<String> {

  private final NationalCloud nationalCloud;
  private final Credential credential;
  private final String tokenPath;

  public TokenUrl(NationalCloud nationalCloud, Credential credential, String tokenPath) {
    this.nationalCloud = nationalCloud;
    this.credential = credential;
    this.tokenPath = tokenPath;
  }

  public TokenUrl(Credential credential) {
    this(
        new NationalCloud(),
        credential,
        "/oauth2/v2.0/token"
    );
  }

  @Override
  public String get() {
    try {
      return new URL(nationalCloud.apply(credential.nationalCloud()) + credential.tenant() + tokenPath).toString();
    } catch (MalformedURLException e) {
      throw new IllegalStateException("Invalid token URL configuration.");
    }
  }
}
