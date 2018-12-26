package com.fronzec.springvertxdemo.verticles;

/**
 * @author eflores@rappi.com
 * @version 1.0
 * @since 14/12/18
 */

public class Endpoints {

  private Endpoints() {
    throw new IllegalStateException("Enpoint Class");
  }

  public static final String GET_ALL_ARTICLES_ENDPOINT = "/api/v1/articles";
  public static final String HEALTH_CHECK_ENDPOINT = "/api/v1/health";
  public static final String STATUS_CHECK_ENDPOINT = "/api/v1/status";
}
