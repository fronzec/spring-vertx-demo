package com.fronzec.springvertxdemo.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.springframework.stereotype.Component;

/**
 * @author eflores@rappi.com
 * @version 1.0
 * @since 13/12/18
 */
@Component
public class ServerVerticle extends AbstractVerticle {

  private void getAllArticlesHandler(RoutingContext routingContext) {
    vertx.eventBus()
      .<String>send(ArticleRecipientVerticle.GET_ALL_ARTICLES, "", result -> {
        if (result.succeeded()) {
          routingContext.response()
            .putHeader("content-type", "application/json")
            .setStatusCode(200)
            .end(result.result()
              .body());
        } else {
          routingContext.response()
            .setStatusCode(500)
            .end();
        }
      });
  }

  @Override
  public void start() throws Exception {
    super.start();

    //In this section we register the route with their handler funtion
    Router router = Router.router(vertx);
    router.get(Endpoints.GET_ALL_ARTICLES_ENDPOINT)
      .handler(this::getAllArticlesHandler);

    vertx.createHttpServer()
      .requestHandler(router::accept)
      .listen(config().getInteger("http.port", 8080));
  }

}
