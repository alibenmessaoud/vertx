package ali.vertx.wt;

import io.reactivex.Observable;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AppDeployer extends AbstractVerticle {

    private static final Logger logger = LoggerFactory.getLogger(AppDeployer.class);

    public static void main(String[] args) {
        new AppDeployer().start();
    }

    @Override
    public void start() {

        VertxOptions options = new VertxOptions();
        options.setMaxEventLoopExecuteTime(Long.MAX_VALUE);
        vertx = Vertx.vertx(options);

        JsonObject config = config();

        Observable.just(new RxWebVerticle()).subscribe(
                verticle -> vertx.deployVerticle(verticle, asyncResult -> {
                    String verticleName = verticle.getClass().getName();
                    if (asyncResult.succeeded()) {
                        logger.info("Succeeded |✅| Deployment for: {} : {}", verticleName, asyncResult.result());
                    } else {
                        logger.error("Failed |❌| Deployment for {}", verticleName);
                    }
                }),
                error -> logger.error("Error occurred during deploy |❌| %s", error),
                () -> logger.info("All verticles sent to the deployer |✅|")
        );

    }
}
