package com.fronzec.springvertxdemo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author eflores@rappi.com
 * @version 1.0
 * @since 13/12/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SpringVertxDemoApplicationIntegrationTest {

  private TestRestTemplate restTemplate = new TestRestTemplate();

  @Test
  public void givenUrl_whenReceivedArticles_thenSuccess() {
    ResponseEntity<String> responseEntity = restTemplate
      .getForEntity("http://localhost:8080/api/v1/articles", String.class);

    assertEquals(200, responseEntity.getStatusCodeValue());
  }
}
