package ali.vertx.wm.model;

public class Comment {

    private final String id;
    private final String content;
    private final String user;

    public Comment(String id, String content, String user) {
        this.id = id;
        this.content = content;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getUser() {
        return user;
    }

}
