package com.fronzec.springvertxdemo;

import com.fronzec.springvertxdemo.verticles.ArticleRecipientVerticle;
import com.fronzec.springvertxdemo.verticles.ServerVerticle;
import io.vertx.core.Vertx;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@EnableJpaRepositories("com.fronzec.springvertxdemo.repository")
@EntityScan("com.fronzec.springvertxdemo.entity")
@ComponentScan(basePackages = {"com.fronzec"})
public class SpringVertxDemoApplication {

  // The server verticle
  @Autowired
  private ServerVerticle serverVerticle;

  // A service verticle
  @Autowired
  private ArticleRecipientVerticle articleRecipientVerticle;

  public static void main(String[] args) {
    SpringApplication.run(SpringVertxDemoApplication.class, args);
  }

  @PostConstruct
  public void deployVerticles() {
    final Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(serverVerticle);
    vertx.deployVerticle(articleRecipientVerticle);
  }

}

