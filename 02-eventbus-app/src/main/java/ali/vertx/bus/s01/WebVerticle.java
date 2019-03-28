package ali.vertx.bus.s01;

import io.netty.handler.codec.http.HttpHeaderValues;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.RoutingContext;
import io.vertx.rxjava.ext.web.handler.BodyHandler;

import static ali.vertx.bus.s01.SecretOperations.SAY_SECRET;
import static ali.vertx.bus.s01.Services.SECRET_SERVICE_1;

public class WebVerticle extends AbstractVerticle {

    @Override
    public void start() {
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        router.get("/api/secret").handler(this::getSecret);

        vertx.createHttpServer().requestHandler(router).rxListen(8000).subscribe(
            http -> System.out.println("Server ready on port: " + http.actualPort()),
            error -> System.out.println("Server no ready: " + error)
        );
    }

    private void getSecret(RoutingContext routingContext) {
        vertx.eventBus().rxSend(SECRET_SERVICE_1.name(), SAY_SECRET.name()).toObservable().subscribe(
                response -> {
                    routingContext.response().setStatusCode(200);
                    routingContext.response().putHeader(HttpHeaders.CONTENT_TYPE.toString(), HttpHeaderValues.APPLICATION_JSON.toString());
                    routingContext.response().end(new JsonObject().put("message", response.body().toString()).encode());
                },
                error -> {
                    routingContext.response().setStatusCode(500);
                    routingContext.response().putHeader(HttpHeaders.CONTENT_TYPE.toString(), HttpHeaderValues.APPLICATION_JSON.toString());
                    routingContext.response().end(new JsonObject().put("error", error.getMessage()).put("code", 500).encode());
                }
        );
    }

//    /*Non-rx version goes like this:*/
//    vertx.createHttpServer().requestHandler(this::handleHttpRequest).listen(8080);
//    private void handleHttpRequest(final HttpServerRequest httpRequest) {
//
//        /* Invoke using the event bus. */
//        vertx.eventBus().send(Services.SECRET_SERVICE_1.toString(),
//                SecretOperations.SAY_SECRET.toString(), response -> {
//                    if (response.succeeded()) {
//                        httpRequest.response().end(response.result().body().toString());
//                    } else {
//                        System.out.println(String.format("Can't send message to hello service", response.cause()));
//                        httpRequest.response().setStatusCode(500).end(response.cause().getMessage());
//                    }
//                });
//    }
}
