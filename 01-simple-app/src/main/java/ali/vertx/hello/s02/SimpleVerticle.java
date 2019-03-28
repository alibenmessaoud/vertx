package ali.vertx.hello.s02;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

/**
 * As mentioned, Vert.x is asynchronous and non-blocking.
 * The future parameter is important in terms of notification of the completion process.
 * This means when the verticle deployed it won’t wait until the start method has been completed.
 *
 */
public class SimpleVerticle extends AbstractVerticle {

    @Override
    public void start(Future<Void> future) {

        vertx.createHttpServer()
                .requestHandler(httpServerRequest -> httpServerRequest.response().end("Hello Vert.x"))
                .listen(2002, result -> {
                    if (result.succeeded()) {
                        future.complete();
                    } else {
                        future.fail(result.cause());
                    }
                });
    }

    @Override
    public void stop(Future<Void> stopFuture) throws Exception {
        stopFuture.complete();
    }

}
