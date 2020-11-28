package io.pan.graphapi.authentication.request.credential;

import java.net.URL;
import java.util.List;

/**
 * Base credentials
 */
public interface Credential {

  String clientId();

  String clientSecret();

  List<String> scopes();

  URL tokenUrl();
}
