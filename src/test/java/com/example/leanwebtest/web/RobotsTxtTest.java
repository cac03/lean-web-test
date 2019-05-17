package com.example.leanwebtest.web;

import com.example.leanwebtest.lean.LeanWebTest;
import com.panforge.robotstxt.Grant;
import com.panforge.robotstxt.RobotsTxt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.Resource;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Here an external library is used to test {@code robots.txt} file
 * The {@code robots.txt} is located at /static/robots.txt path
 */
@LeanWebTest
class RobotsTxtTest {
  @Autowired
  private TestRestTemplate restTemplate;

  private RobotsTxt fetchRobotsTxt() throws Exception {
    Resource resource = restTemplate.getForObject("/robots.txt", Resource.class);
    try (InputStream is  = resource.getInputStream()) {
      return RobotsTxt.read(is);
    }
  }

  @Test
  void noRobotsAllowed() throws Exception {
    RobotsTxt robotsTxt = fetchRobotsTxt();
    Grant grant = robotsTxt.ask("Whatever", "/");
    boolean hasAccess = grant.hasAccess();
    assertFalse(hasAccess);
  }

  @Test
  void specialUserAgentHasAccessToSpecialPath() throws Exception {
    RobotsTxt robotsTxt = fetchRobotsTxt();
    Grant grant = robotsTxt.ask("Special-UA", "/special");
    boolean hasAccess = grant.hasAccess();
    assertTrue(hasAccess);
  }
}
