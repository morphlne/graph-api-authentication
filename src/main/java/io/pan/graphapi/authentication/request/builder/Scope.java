package io.pan.graphapi.authentication.request.builder;

import java.util.List;
import java.util.function.Supplier;

public class Scope implements Supplier<String> {

  private final List<String> scopes;

  public Scope(List<String> scopes) {
    this.scopes = scopes;
  }

  @Override
  public String get() {
    return scopes == null || scopes.isEmpty()
        ? "https://graph.microsoft.com/.default"
        : String.join(" ", scopes);
  }
}
