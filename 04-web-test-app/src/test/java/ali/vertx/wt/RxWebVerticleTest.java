package ali.vertx.wt;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.buffer.Buffer;
import io.vertx.reactivex.core.http.HttpClient;
import io.vertx.reactivex.ext.web.client.HttpResponse;
import io.vertx.reactivex.ext.web.client.WebClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(VertxUnitRunner.class)
public class RxWebVerticleTest {

    private static final Logger logger = LoggerFactory.getLogger(RxWebVerticleTest.class);

    private Vertx vertx;
    private HttpClient rxVertxHttpClient;
    private WebClient rxVertxWebClient;
    private int port;

    @Before
    public void before(TestContext testContext) throws IOException {
        availablePort();
        DeploymentOptions deploymentOptions = new DeploymentOptions().setConfig(new JsonObject().put("wt.server.port", port));
        vertx = Vertx.vertx();
        vertx.deployVerticle(new RxWebVerticle(), deploymentOptions, testContext.asyncAssertSuccess());
        rxVertxHttpClient = vertx.createHttpClient();
        rxVertxWebClient = WebClient.wrap(vertx.createHttpClient());
    }

    @After
    public void after(TestContext testContext) {
        vertx.close(testContext.asyncAssertSuccess());
    }

    private void availablePort() throws IOException {
        ServerSocket serverSocket = new ServerSocket(0);
        port = serverSocket.getLocalPort();
        serverSocket.close();
        logger.info("port {}", port);
    }

    @Test
    public void test_undeploy(TestContext testContext) {
        vertx.deployVerticle(
                RxWebVerticle.class.getName(), testContext.asyncAssertSuccess(deployId -> vertx.undeploy(deployId, testContext.asyncAssertSuccess()))
        );
    }

    @Test
    public void test_with_http_client(TestContext testContext) {
        Async async = testContext.async();
        rxVertxHttpClient.getNow(port, "localhost", "/api/health", resp -> {
            resp.bodyHandler(body -> {
                testContext.assertEquals(new JsonObject("{\"code\":200,\"path\":\"/api/health\"}"), new JsonObject(body.toString()));
                rxVertxHttpClient.close();
                async.complete();
            });
        });
        async.awaitSuccess();
    }

    @Test
    public void test_web_client_v1(TestContext testContext) {
        Async async = testContext.async();
        rxVertxWebClient.get(port, "localhost", "/api/health")
                .send(requestResponse -> {
                    testContext.verify(event -> {
                        assertThat(requestResponse.result().statusCode()).isEqualTo(OK.code());
                        assertThat(new JsonObject().put("code", 200).put("path", "/api/health")).isEqualTo(requestResponse.result().bodyAsJsonObject());
                    });
                    async.complete();
                });
        async.awaitSuccess();
    }

    @Test
    public void test_rx_web_client_non_blocking(TestContext testContext) {
        Async async = testContext.async();
        rxVertxWebClient.get(port, "localhost", "/api/health")
                .rxSend()
                .subscribeOn(Schedulers.io())
                .subscribe(
                        s -> {
                            testContext.assertEquals(new JsonObject().put("code", 200).put("path", "/api/health"), s.bodyAsJsonObject());
                            async.complete();
                        },
                        e -> testContext.fail(e)
                );
        async.awaitSuccess();
    }

    @Test
    public void test_rx_web_client_blocking(TestContext testContext) {
        Single<HttpResponse<Buffer>> single = rxVertxWebClient
                .get(port, "localhost", "/api/health")
                .rxSend().subscribeOn(Schedulers.io());

        assertThat(new JsonObject().put("code", 200).put("path", "/api/health"))
                .isEqualTo(single.blockingGet().bodyAsJsonObject());
    }
}