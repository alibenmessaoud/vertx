package ali.vertx.wt;

import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.web.RoutingContext;

public class AppUtils {

    private AppUtils() {
        // intentionally left private
    }

    public static void reply(RoutingContext ctx, int code, JsonObject result) {
        ctx.response()
                .putHeader("Content-Type", "application/json")
                .setStatusCode(code)
                .end(result
                        .put("code", code)
                        .put("path", ctx.request().path())
                        .encodePrettily()
                );
    }

    public static void reply(RoutingContext ctx, int code, String result) {
        ctx.response()
                .putHeader("Content-Type", "text/plain")
                .setStatusCode(code)
                .end(result);
    }

}