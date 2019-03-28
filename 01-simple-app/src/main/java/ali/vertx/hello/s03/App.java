package ali.vertx.hello.s03;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class App {

    public static void main(String[] args) {

        VertxOptions options = new VertxOptions();
        options.setMaxEventLoopExecuteTime(Long.MAX_VALUE);

        Vertx vertx = Vertx.vertx(options);

        vertx.deployVerticle(new RxVerticle().getClass().getName(), res -> {
            if (res.succeeded()) {
                System.out.println("Deployment id is: " + res.result());
            } else {
                System.out.println("Deployment failed!");
            }
        });
    }
}
