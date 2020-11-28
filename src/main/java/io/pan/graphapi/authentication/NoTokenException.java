package io.pan.graphapi.authentication;

public class NoTokenException extends RuntimeException {

  public NoTokenException(Throwable cause) {
    super(cause);
  }
}
