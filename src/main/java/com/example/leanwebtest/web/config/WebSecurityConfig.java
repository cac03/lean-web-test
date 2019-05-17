package com.example.leanwebtest.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/**")
        .authenticated();
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    // also we might want to ignore other paths serving static content
    web.ignoring()
        .antMatchers("/js/**", "/robots.txt");
  }
}
