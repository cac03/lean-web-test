package com.example.leanwebtest.web;

import com.example.leanwebtest.lean.LeanWebTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@LeanWebTest
class StaticContentTest {
  /**
   * Sine {@link LeanWebTest} is a {@link org.springframework.boot.test.context.SpringBootTest}
   * we have configured {@link TestRestTemplate}
   */
  @Autowired
  private TestRestTemplate restTemplate;

  /**
   * Take a look at {@code /static/js} path.
   */
  @Test
  void myScriptServedWithCorrectCacheControlHeaders() {
    ResponseEntity<String> entity = restTemplate.getForEntity("/js/myscript.js", String.class);
    assertTrue(entity.getStatusCode().is2xxSuccessful());
    // Cache control configured in the application.properties
    assertEquals("max-age=3600, public", entity.getHeaders().getCacheControl());
  }
}
