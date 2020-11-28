package io.pan.graphapi.authentication;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TokenTest {

  @Test
  public void givenToken_whenGetValue_thenSuccess() {
    final String tokenValue = "value";
    Token token = new Token(tokenValue, LocalDateTime.now(), 0);
    assertEquals(token.value(), tokenValue);
  }

  @Test
  public void givenToken_whenCheckBeforeExpirationTime_thenFalse() {
    Token token = new Token("", LocalDateTime.now().plusSeconds(1), 0);
    assertFalse(token.expired());
  }

  @Test
  public void givenToken_whenCheckAfterExpirationTime_thenTrue() {
    Token token = new Token("", LocalDateTime.now().minusSeconds(1), 0);
    assertTrue(token.expired());
  }

  @Test
  public void givenToken_whenCheckBeforeBuffer_thenFalse() {
    Token token = new Token("", LocalDateTime.now().plusSeconds(2), 1);
    assertFalse(token.expired());
  }

  @Test
  public void givenToken_whenCheckWithinBuffer_thenTrue() {
    Token token = new Token("", LocalDateTime.now().plusSeconds(1), 2);
    assertTrue(token.expired());
  }
}
