package com.example.leanwebtest.web;

import com.example.leanwebtest.lean.LeanWebTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This test checks if the server is configured correctly.
 * {@code application.properties} have {@code server.tomcat.remote-ip-header} defined,
 * so {@link ServletRequest#getRemoteAddr()} expected to return value of
 * {@code X-Extraordinary-Ip-Header} header
 */
@LeanWebTest
@ContextConfiguration(classes = TomcatRemoteIpHeaderTest.Config.class)
class TomcatRemoteIpHeaderTest {
  @Autowired
  private TestRestTemplate restTemplate;

  @Configuration
  @Order(1000) // Since another WebSecurityConfigurerAdapter exposed @Order is required
  public static class Config extends WebSecurityConfigurerAdapter {
    @Bean
    public IpController ipController() {
      return new IpController();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
      web.ignoring().antMatchers("/ip");
    }
  }

  @RestController
  static class IpController {
    @RequestMapping("/ip")
    public String getIp(HttpServletRequest request) {
      return request.getRemoteAddr();
    }
  }

  @Test
  void extraordinaryRemoteHeaderIsTakenIntoAccount() {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.set("X-Extraordinary-Ip-Header", "8.8.8.8");
    ResponseEntity<String> entity = restTemplate.exchange("/ip", HttpMethod.GET,
        new HttpEntity<>(null, httpHeaders), String.class);

    assertEquals("8.8.8.8", entity.getBody());
  }
}
