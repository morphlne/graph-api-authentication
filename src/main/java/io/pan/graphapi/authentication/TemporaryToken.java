package io.pan.graphapi.authentication;

/**
 * Access token
 */
public interface TemporaryToken {

  /**
   * Return string representation of token
   *
   * @return string value
   */
  String value();

  /**
   * Checks if the token is expired
   *
   * @return boolean result of checking
   */
  boolean expired();
}
