package ali.vertx.hello.s03;

import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.http.HttpServerResponse;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.RoutingContext;
import io.vertx.rxjava.ext.web.handler.BodyHandler;
import rx.Subscription;

public class RxVerticle extends AbstractVerticle {

    @Override
    public void start() {
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        router.get("/api/user/:id").handler(this::getUser);

        initHttpServer(router);
    }

    private Subscription initHttpServer(Router router) {
        return vertx
                .createHttpServer()
                .requestHandler(router)
                .rxListen(2004)
                .subscribe(
                        http -> System.out.println("Server ready on port: " + http.actualPort()),
                        error -> System.out.println("Server no ready: " + error)
                );
    }

    private void getUser(RoutingContext ctx) {
        String id = ctx.pathParam("id");
        JsonObject value = new JsonObject().put("id", id).put("type", "user");
        String encoded = value.encode();
        HttpServerResponse response = ctx.response().putHeader("Content-Type", "application/json");
        response.end(encoded);
    }
}
