package com.example.leanwebtest.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Some service to demonstrate that it is not
 * picked by auto-configuration
 */
@Service
public class SomeService {
  @PersistenceContext
  private final EntityManager entityManager;

  public SomeService(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @PostConstruct
  public void init() {
    throw new RuntimeException("SomeService is picked by tests");
  }
}
