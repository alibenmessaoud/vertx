package ali.vertx.wm.common;

import ali.vertx.wm.model.Comment;
import com.google.gson.Gson;
import io.vertx.core.json.JsonObject;

public enum ModelJson {
    INSTANCE;

    private final Gson gson = new Gson();

    public String serialize(Comment comment) {
        return gson.toJson(comment);
    }

    public Comment deserialize(String commentAsString) {
        return gson.fromJson(commentAsString, Comment.class);
    }

    public Comment deserialize(JsonObject commentAsJsonObject) {
        return gson.fromJson(commentAsJsonObject.encode(), Comment.class);
    }

}
