package ali.vertx.wm.storage;

import ali.vertx.wm.common.IdGenerator;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.eventbus.Message;
import io.vertx.reactivex.ext.mongo.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ali.vertx.wm.event.EventsMap.*;
import static ali.vertx.wm.storage.MongoErrorCode.*;
import static ali.vertx.wm.storage.MongoResponse.error;
import static ali.vertx.wm.storage.MongoResponse.success;

public class RxStorageVerticle extends AbstractVerticle {

    private static final Logger logger = LoggerFactory.getLogger(RxStorageVerticle.class);
    private final MongoClient mongoClient;
    public static final String COMMENT_COLLECTION = "comments";

    public RxStorageVerticle(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Override
    public void start(Future<Void> startFuture) {
        logger.info("RxStorageVerticle started");
        vertx.eventBus().consumer(SAVE.name(), this::saveComment);
        vertx.eventBus().consumer(GET.name(), this::getComment);
        vertx.eventBus().consumer(ALL.name(), this::getComments);
        startFuture.complete();
    }

    @Override
    public void stop() {
        logger.info("RxStorageVerticle stopped");
    }

    private void saveComment(Message<JsonObject> message) {

        JsonObject commentBody = message.body();

        if (commentBody == null) {
            logger.error("IllegalArgumentException {} - {}", "The comment object must not be null", MISSING_VALUE.getCode());
            message.fail(MISSING_VALUE.getCode(), error(MISSING_VALUE.name(), "IllegalArgumentException: The comment object must not be null").encode());
        }

        if (commentBody != null && checkCommentValue(commentBody)) {
            logger.error("IllegalArgumentException {} - {}", "The values must not be null or empty", MISSING_VALUE.getCode());
            message.fail(MISSING_VALUE.getCode(), error(MISSING_VALUE.name(), "IllegalArgumentException: The values must not be null or empty").encode());
        }

        String hashId = IdGenerator.GENERATOR.get();
        commentBody.put("id", hashId);
        mongoClient.rxSave(COMMENT_COLLECTION, commentBody).subscribe(
                mongoId -> {
                    logger.info("Stored and id is {}", hashId);
                    message.reply(success(hashId));
                },
                error -> {
                    logger.error("Error during storage {}", error);
                    message.fail(MONGO_QUERY_ERROR.getCode(), error(MONGO_QUERY_ERROR.name(), String.format("Exception: %s", error.getMessage())).encode());
                }
        );

    }

    private void getComment(Message<JsonObject> message) {
        JsonObject commentId = message.body();

        if (commentId == null) {
            logger.error("IllegalArgumentException {} - {}", "The query object must not be null", MISSING_VALUE.getCode());
            message.fail(MISSING_VALUE.getCode(), error(MISSING_VALUE.name(), "IllegalArgumentException: The query object must not be null").encode());
        }

        if (commentId.getString("id") == null) {
            logger.error("IllegalArgumentException {} - {}", "The id must not be null or empty", MISSING_VALUE.getCode());
            message.fail(MISSING_VALUE.getCode(), error(MISSING_VALUE.name(), "IllegalArgumentException: The id must not be null or empty").encode());
        }

        logger.info("Trying to get comment commentId: {}", commentId);

        mongoClient.rxFind(COMMENT_COLLECTION, new JsonObject().put("id", commentId.getString("id"))).subscribe(
                comments -> {
                    if (comments.size() == 1) {
                        logger.info("Found comment with id {}", commentId);
                        message.reply(formatComment(comments.get(0)));
                    } else {
                        logger.error("Comment not found");
                        message.reply(error(MONGO_NOT_FOUND.name(), "Comment not found"));
                    }
                },
                error -> {
                    logger.error("Error during querying {}", error);
                    message.fail(MONGO_QUERY_ERROR.getCode(), error(MONGO_QUERY_ERROR.name(), "Exception: " + error.getMessage()).encode());
                }
        );

    }

    private void getComments(Message<JsonObject> message) {
        JsonObject query = message.body();
        if (query == null) {
            logger.error("IllegalArgumentException {} - {}", "The query object must not be null or empty", MISSING_VALUE.getCode());
            message.fail(MISSING_VALUE.getCode(), error(MISSING_VALUE.name(), "IllegalArgumentException: The query object must not be null or empty").encode());
        }

        mongoClient.rxFind(COMMENT_COLLECTION, new JsonObject()).subscribe(
                comments -> {
                    JsonArray resultComment = new JsonArray();
                    comments.forEach(c -> resultComment.add(formatComment(c)));
                    message.reply(new JsonObject().put("comments", resultComment));
                },
                error -> {
                    logger.error("Error during querying {}", error);
                    message.fail(MONGO_QUERY_ERROR.getCode(), error(MONGO_QUERY_ERROR.name(), "Exception: " + error.getMessage()).encode());
                }
        );

    }

    private static JsonObject formatComment(JsonObject comment) {
        JsonObject resultComment = new JsonObject();
        comment.fieldNames().stream().filter(key -> !key.equals("_id")).forEach(key -> resultComment.put(key, comment.getValue(key)));
        return resultComment;
    }

    private static boolean checkCommentValue(JsonObject commentBody) {
        return commentBody.getString("user") == null || commentBody.getString("user").isEmpty() ||
                commentBody.getString("content") == null || commentBody.getString("content").isEmpty();
    }

}
