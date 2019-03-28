package ali.vertx.wt;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;
import io.vertx.reactivex.ext.web.handler.BodyHandler;
import io.vertx.reactivex.ext.web.handler.StaticHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class RxWebVerticle extends AbstractVerticle {

    private static final Logger logger = LoggerFactory.getLogger(RxWebVerticle.class);

    private static final HashMap<Integer, String> users = new HashMap<>();
    private static final int defaultPortValue = 9000;
    private static final String defaultPortKey = "wt.server.port";

    @Override
    public void start(Future<Void> startFuture) {
        users.put(1, "ali");

        JsonObject config = config();
        Router router = Router.router(vertx);

        router.route().consumes("application/json");
        router.route().consumes("application/json");

        router.get("/api/user/:id").handler(this::getUser);
        router.get("/api/health").handler(ctx -> AppUtils.reply(ctx, 200, new JsonObject()));
        router.get("/api/flaky").handler(new FlakyHandler()).failureHandler(new FlakyFandler());

        router.route().handler(BodyHandler.create());
        router.route().handler(StaticHandler.create());
        router.route("/*").handler(this::defaultHandler);

        vertx.createHttpServer()
                .requestHandler(router)
                .listen(config.getInteger(defaultPortKey, defaultPortValue),
                        httpServerAsyncResult -> startFuture.complete()
                );
    }

    private void defaultHandler(RoutingContext rc) {
        rc.response().sendFile("webroot/index.html");
    }

    private void getUser(RoutingContext rc) {
        try {
            int id = Integer.parseInt(rc.pathParam("id"));
            if (users.keySet().contains(id)) {
                logger.info("200 - Found user for id: {}", id);
                AppUtils.reply(rc, 200, new JsonObject().put("name", users.get(id)));
            } else {
                logger.info("404 - Cannot find user for id: {}", id);
                AppUtils.reply(rc, 404, new JsonObject());
            }
        } catch (NumberFormatException nfe) {
            logger.info("500 - Cannot handle id parameter");
            AppUtils.reply(rc, 500, new JsonObject());
        }
    }

}
