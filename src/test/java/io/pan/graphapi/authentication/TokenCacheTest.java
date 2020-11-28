package io.pan.graphapi.authentication;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TokenCacheTest {

  @Test
  public void givenActiveToken_whenGet_thenReturnTheSameToken() {
    final String expectedValue = "value";
    final TokenCache tokenCache = new TokenCache(
        () -> {
          throw new UnsupportedOperationException();
        },
        new Token(expectedValue, LocalDateTime.now().plusSeconds(1), 0)
    );
    assertEquals(expectedValue, tokenCache.get());
    assertEquals(expectedValue, tokenCache.get());
  }

  @Test
  public void givenExpiredToken_whenGet_thenReturnNewToken() {
    final String expectedValue = "value";
    final TokenCache tokenCache = new TokenCache(
        () -> new Token(expectedValue, LocalDateTime.now().plusSeconds(1), 0),
        new Token("expiredValue", LocalDateTime.now().minusSeconds(1), 0)
    );
    assertEquals(expectedValue, tokenCache.get());
  }
}
