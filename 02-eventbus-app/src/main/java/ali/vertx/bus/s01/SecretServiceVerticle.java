package ali.vertx.bus.s01;

import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.eventbus.Message;

public class SecretServiceVerticle extends AbstractVerticle {

    @Override
    public void start() {
        vertx.eventBus().consumer(Services.SECRET_SERVICE_1.toString(), this::dispatchMessage);
    }

    private void dispatchMessage(final Message<Object> message) {
        try {
            final SecretOperations operation = SecretOperations.valueOf(message.body().toString());
            switch (operation) {
                case SAY_SECRET:
                    message.reply("HELLO WORLD!");
                    break;
                default:
                    System.out.println(String.format("Unable to handle operation {}", operation));
                    message.reply("Unsupported operation");
            }
        } catch (final Exception ex) {
            System.out.println(String.format("Unable to handle operation due to exception" + message.body(), ex));
        }
    }

}
