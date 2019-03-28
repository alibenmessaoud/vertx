package ali.vertx.wt;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.web.RoutingContext;

import java.util.Random;

import static ali.vertx.wt.AppUtils.reply;

public class FlakyHandler implements Handler<RoutingContext> {
    @Override
    public void handle(RoutingContext routingContext) {
        if (new Random().nextInt(50) % 2 == 0)
            reply(routingContext, 200, new JsonObject());
        else
            routingContext.fail(500);
    }
}
