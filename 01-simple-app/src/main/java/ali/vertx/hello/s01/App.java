package ali.vertx.hello.s01;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;

public class App {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.createHttpServer()
            .requestHandler(App::handler)
            .listen(2000);
    }

    private static void handler(HttpServerRequest req) {
        req.response().end("Hello Vert.x");
    }
}
