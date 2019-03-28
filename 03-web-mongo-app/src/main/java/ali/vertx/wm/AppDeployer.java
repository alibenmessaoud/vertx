package ali.vertx.wm;

import ali.vertx.wm.storage.RxStorageVerticle;
import ali.vertx.wm.web.RxWebVerticle;
import io.reactivex.Observable;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.mongo.MongoClient;
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
        DeploymentOptions deploymentOptions = new DeploymentOptions().setConfig(config);

        MongoClient mongoClient = initMongoClient(config);

        Observable.just(new RxWebVerticle(), new RxStorageVerticle(mongoClient)).subscribe(
                verticle -> vertx.deployVerticle(verticle, deploymentOptions, asyncResult -> {
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

    private MongoClient initMongoClient(JsonObject config) {
        JsonObject mongoConfig = new JsonObject()
                .put("connection_string", String.format("mongodb://%s:%s@%s:%d",
                        config.getString("wm.mongodb.username", "user"),
                        config.getString("wm.mongodb.password", "user"),
                        config.getString("wm.mongodb.host", "localhost"),
                        config.getInteger("wm.mongodb.port", 27017)))
                .put("db_name", config.getString("wm.mongodb.database", "vertxwmdb"));
        return MongoClient.createShared(vertx, mongoConfig);
    }
}
