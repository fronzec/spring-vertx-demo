package com.fronzec.springvertxdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringVertxDemoApplication.class)
public class SpringVertxDemoApplicationTests {

  /**
   * Test correct load of spring context, should no throw any exceptions
   */
  @Test
  public void contextLoads() {

  }

}
