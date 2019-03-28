package ali.vertx.wm.web;

import ali.vertx.wm.event.EventsMap;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.http.HttpServerResponse;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;
import io.vertx.reactivex.ext.web.handler.BodyHandler;
import io.vertx.reactivex.ext.web.handler.StaticHandler;

import java.util.Objects;

import static ali.vertx.wm.common.HttpResponse.reply;
import static ali.vertx.wm.storage.MongoErrorCode.MONGO_NOT_FOUND;

public class RxWebVerticle extends AbstractVerticle {

    @Override
    public void start(Future<Void> startFuture) {
        JsonObject config = config();

        Router router = Router.router(vertx);

        router.route().handler(BodyHandler.create());
        router.route().handler(StaticHandler.create());

        router.get("/api/user/:id").handler(this::getUser);
        router.post("/api/comment/save").handler(this::saveComment);
        router.get("/api/comment/:id").handler(this::getComment);
        router.get("/api/comment").handler(this::getComments);
        router.route("/*").handler(this::defaultHandler);

        vertx.createHttpServer()
                .requestHandler(router)
                .listen(config.getInteger("wm.server.port", 9000), httpServerAsyncResult -> startFuture.complete());
    }

    private void defaultHandler(RoutingContext rc) {
        rc.response().sendFile("webroot/index.html");
    }

    private void getUser(RoutingContext ctx) {
        String id = ctx.pathParam("id");
        HttpServerResponse response = ctx.response().putHeader("Content-Type", "application/json");
        response.end(new JsonObject().put("id", id).put("type", "user").encode());
    }

    private void saveComment(RoutingContext ctx) {
        JsonObject commentQuery;

        try {
            commentQuery = ctx.getBodyAsJson();
        } catch (RuntimeException e) {
            reply(ctx, 415, new JsonObject().put("result", "Invalid payload"));
            return;
        }

        if (commentQuery == null) {
            reply(ctx, 415, new JsonObject().put("result", "Invalid payload"));
            return;
        }

        vertx.eventBus().<JsonObject>rxSend(EventsMap.SAVE.name(), commentQuery).subscribe(
                result -> reply(ctx, 201, result.body()),
                failure -> reply(ctx, 500, new JsonObject(failure.getMessage()))
        );
    }

    private void getComment(RoutingContext ctx) {
        String id = ctx.pathParam("id");

        vertx.eventBus().<JsonObject>rxSend(EventsMap.GET.name(), new JsonObject().put("id", id)).subscribe(
                result -> reply(ctx, Objects.equals(result.body().getString("code"), MONGO_NOT_FOUND.name()) ? 404: 200, result.body()),
                failure -> reply(ctx, 500, new JsonObject(failure.getMessage()))
        );
    }

    private void getComments(RoutingContext ctx) {
        vertx.eventBus().<JsonObject>rxSend(EventsMap.ALL.name(), new JsonObject()).subscribe(
                result -> reply(ctx, 200, result.body()),
                failure -> reply(ctx, 500, new JsonObject(failure.getMessage()))
        );
    }

}
