package io.pan.graphapi.authentication.request.credential;

/**
 * Credentials needed for provide delegate permissions
 */
public interface UserCredential extends Credential {

  String user();

  String password();
}
