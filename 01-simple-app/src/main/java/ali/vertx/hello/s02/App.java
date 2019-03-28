package ali.vertx.hello.s02;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;


/**
 * Without future.complete() in the verticle, the deployer will not show the id;
 * > Deployment id is: 73378d39-339b-4e81-88a7-ed452d0cf4e7
 */
public class App {

    public static void main(String[] args) {

        VertxOptions options = new VertxOptions();
        options.setMaxEventLoopExecuteTime(Long.MAX_VALUE);

        Vertx vertx = Vertx.vertx(options);

        vertx.deployVerticle(new SimpleVerticle(), res -> {
            if (res.succeeded()) {
                System.out.println("Deployment id is: " + res.result());
            } else {
                System.out.println("Deployment failed!");
            }
        });
    }
}
