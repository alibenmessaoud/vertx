package ali.vertx.wm.common;

import java.util.UUID;

public enum IdGenerator {
    GENERATOR;

    public String get() {
        return UUID.randomUUID().toString();
    }
    // private final static Hashids hashId = new Hashids("vert.x", 8, "0123456789abcdefghijklmnopqrstuvwxyz");
}
