package com.fronzec.springvertxdemo.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.healthchecks.HealthCheckHandler;
import io.vertx.ext.healthchecks.HealthChecks;
import io.vertx.ext.healthchecks.Status;
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
    // Health check handlers
    //HealthCheckHandler healthHandler = HealthCheckHandler.create(vertx);
    HealthCheckHandler healthHandler = HealthCheckHandler
      .createWithHealthChecks(HealthChecks.create(vertx));
    healthHandler.register("server-online", statusFuture -> statusFuture.complete(Status.OK()));
    // A single procedure should check one aspect of the system, you can group by category,groups can be nested
    HealthCheckHandler healthCheckWithProcedures = HealthCheckHandler.create(vertx);
    healthCheckWithProcedures.register("memory/memory-usage", statusFuture -> {
      // Check memory status if ok ...
      boolean memoryCheck = true;
      if (memoryCheck) {
        statusFuture.complete(Status.OK(new JsonObject().put("memory-usage", 90)));
      } else {
        // If fails send status KO
        statusFuture.complete(Status.KO());
      }
    });
    // TODO agregar checkeo con la base de datos
    healthCheckWithProcedures.register("database/check", statusFuture ->
      statusFuture.complete(Status.OK(new JsonObject().put("online", "YES")))
    );
    healthCheckWithProcedures.register("memory/free-memory", 3000, statusFuture -> {
      int freeMemory = 255;// MB
      statusFuture.complete(Status.OK(new JsonObject().put("free", freeMemory)));

    });

    //In this section we register the route with their handler funtion
    Router router = Router.router(vertx);
    router.get(Endpoints.GET_ALL_ARTICLES_ENDPOINT)
      .handler(this::getAllArticlesHandler);
    router.get(Endpoints.HEALTH_CHECK_ENDPOINT).handler(healthHandler);
    router.get(Endpoints.STATUS_CHECK_ENDPOINT).handler(healthCheckWithProcedures);

    vertx.createHttpServer()
      .requestHandler(router::accept)
      .listen(config().getInteger("http.port", 8080));
  }

}
