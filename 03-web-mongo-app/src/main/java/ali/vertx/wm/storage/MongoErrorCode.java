package ali.vertx.wm.storage;

/**
 * https://developers.google.com/my-business/reference/rest/Shared.Types/ErrorCode
 */
public enum MongoErrorCode {

    MISSING_VALUE(1),
    INVALID_VALUE(2),
    MONGO_NOT_FOUND(3),
    MONGO_QUERY_ERROR(4),
    MONGO_SERVER_ERROR(5),


    MONGO_NETWORK_ERROR(21);

    private int errorCode;

    MongoErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getCode() {
        return errorCode;
    }
}
