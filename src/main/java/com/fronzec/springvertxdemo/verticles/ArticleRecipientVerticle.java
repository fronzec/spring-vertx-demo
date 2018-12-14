package com.fronzec.springvertxdemo.verticles;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fronzec.springvertxdemo.service.ArticleService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author eflores@rappi.com
 * @version 1.0
 * @since 13/12/18
 */
@Component
public class ArticleRecipientVerticle extends AbstractVerticle {

  public static final String GET_ALL_ARTICLES = "get.articles.all";

  private final ObjectMapper mapper = Json.mapper;

  @Autowired
  private ArticleService articleService;

  @Override
  public void start() throws Exception {
    super.start();
    vertx.eventBus()
      .<String>consumer(GET_ALL_ARTICLES)
      .handler(getAllArticleService(articleService));
  }

  private Handler<Message<String>> getAllArticleService(ArticleService service) {
    return msg -> vertx.<String>executeBlocking(future -> {
      try {
        future.complete(mapper.writeValueAsString(service.getAllArticle()));
      } catch (JsonProcessingException e) {
        System.out.println("Failed to serialize result");
        future.fail(e);
      }
    }, result -> {
      if (result.succeeded()) {
        msg.reply(result.result());
      } else {
        msg.reply(result.cause()
          .toString());
      }
    });
  }
}
