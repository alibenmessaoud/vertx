package ali.vertx.wm;

import io.vertx.core.Launcher;
import io.vertx.core.VertxOptions;

public class AppLauncher extends Launcher {

    public static void main(String[] args) {
        new AppLauncher().dispatch(args);
    }

    @Override
    public void beforeStartingVertx(VertxOptions options) {
        super.beforeStartingVertx(options);
        options.setClusterHost("localhost");
    }
}
