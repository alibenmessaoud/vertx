package ali.vertx.wt;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlakyFandler implements Handler<RoutingContext> {

    private static final Logger logger = LoggerFactory.getLogger(FlakyFandler.class);

    @Override
    public void handle(RoutingContext routingContext) {
        Throwable throwable = routingContext.failure();
        logger.error("FlakyFandler called because an error occurred", throwable);
        AppUtils.reply(routingContext, 500, new JsonObject());
    }
}
