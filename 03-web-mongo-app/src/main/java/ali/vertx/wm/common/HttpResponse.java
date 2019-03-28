package ali.vertx.wm.common;

import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.web.RoutingContext;

public class HttpResponse {

    private HttpResponse() {
        // intentionally left private
    }

    public static void reply(RoutingContext ctx, int code, JsonObject result) {
        result.put("code", code).put("path", ctx.request().path());

        ctx.response()
                .putHeader("Content-Type", "application/json")
                .setStatusCode(code)
                .end(result.encodePrettily());
    }

}