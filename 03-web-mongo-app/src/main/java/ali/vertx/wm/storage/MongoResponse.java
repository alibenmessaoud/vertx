package ali.vertx.wm.storage;

import io.vertx.core.json.JsonObject;

public class MongoResponse {

    private MongoResponse() {
        // intentionally left private
    }

    public static JsonObject error(String code, String message) {
        return new JsonObject()
                .put("code", code)
                .put("message", message);
    }

    public static JsonObject success(String message) {
        return new JsonObject().put("message", message);
    }

    public static JsonObject error(String code, JsonObject message) {
        return new JsonObject()
                .put("code", code)
                .put("message", message);
    }

    public static JsonObject success(JsonObject message) {
        return new JsonObject().put("message", message);
    }
}
