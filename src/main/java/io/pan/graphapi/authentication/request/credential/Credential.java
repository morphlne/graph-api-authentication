package io.pan.graphapi.authentication.request.credential;

import java.util.List;

/**
 * Base credentials
 */
public interface Credential {

  String clientId();

  String clientSecret();

  List<String> scopes();

  String tenant();

  String nationalCloud();
}
