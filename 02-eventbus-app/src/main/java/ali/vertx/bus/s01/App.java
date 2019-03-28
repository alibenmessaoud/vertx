package ali.vertx.bus.s01;

import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.Vertx;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class App extends AbstractVerticle {

    private final AtomicInteger serviceCount = new AtomicInteger();
    private final List<AbstractVerticle> verticles = Arrays.asList(new SecretServiceVerticle(), new WebVerticle());

    public static void main(final String... args) {
        final Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new App());
    }

    @Override
    public void start() {
        verticles.stream().forEach(verticle ->
                vertx.deployVerticle(
                        verticle, deployResponse -> {
                            if (!deployResponse.failed()) {
                                serviceCount.incrementAndGet();
                                System.out.println(verticle.getClass().getSimpleName() + " deployed");
                            } else {
                                System.out.println("Unable to deploy verticle " + verticle.getClass().getSimpleName() + " " + deployResponse.cause());
                            }
                        }
                )
        );

        vertx.setTimer(TimeUnit.SECONDS.toMillis(5), event -> {
            if (serviceCount.get() != verticles.size()) {
                System.out.println("Main Verticle was unable to start child verticles");
            } else {
                System.out.println("Start up successful");
            }
        });

    }
}
