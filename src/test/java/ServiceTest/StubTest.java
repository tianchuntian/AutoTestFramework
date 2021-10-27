package ServiceTest;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class StubTest {
    private static WireMockServer wireMockServer;
    @BeforeAll
    static void beforeAll() {
        //beforeall中先启动wiremockserver,服务占用8089端口
        wireMockServer = new WireMockServer(wireMockConfig().port(8089));
        //启动服务
        wireMockServer.start();
        //配置端口,上面已配置,此处可省略......(此处可有可无)
        configureFor("localhost", 8089);
    }


    @Test
    void stub() throws InterruptedException {
        //stubFor这个API:需要一个stub服务,发送请求时如果url中匹配到包含"/user/d",则给我返回我下面特定的内容,
        stubFor(get(urlEqualTo("/user/d"))
//                .withHeader("Accept", equalTo("text/xml"))
                //返回特定的aResponse().
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/xml")
                        .withBody("<response>d info</response>")));

        //todo: use
        Thread.sleep(300000);
    }

    @Test
    void mockOnStub() throws InterruptedException {
        stubFor(get(urlEqualTo("/user/d"))
//                .withHeader("Accept", equalTo("text/xml"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/xml")
                        .withBody("<response>mock on stub</response>")));

        //10s之前返回mock on stub,  10s之后返回exception
        Thread.sleep(20000);

        reset();

        stubFor(get(urlEqualTo("/user/d"))
//                .withHeader("Accept", equalTo("text/xml"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/xml")
                        .withBody("<response>exception</response>")));

        Thread.sleep(100000);
    }

    //设置代理服务,类似charles中的map_remote技术.BeforeAll中的服务启动起来后会占用8089端口,任何访问这个端口的请求都会被代理到另一个地址.
    @Test
    void proxy() throws InterruptedException {
        stubFor(
                //请求任何地址都会代理到proxiedFrom中的地址.
//                get(urlMatching(".*"))
                get(urlMatching(".*"))
                        .atPriority(10)
                        .willReturn(aResponse().proxiedFrom("https://www.baidu.com")));
        Thread.sleep(100000);
    }


    @Test
    void mockOnProxyLocal() throws InterruptedException, IOException {
        //1.跳转至代理网址
        stubFor(
                get(urlMatching(".*"))
                        .atPriority(10)
                        .willReturn(aResponse().proxiedFrom("https://ceshiren.com")));
        //2.访问指定内容,时根据本地文件返回数据,返回的是本地文件mock_loc.json中的内容
        stubFor(
                get(urlMatching("/categories_and_latest"))
                        .atPriority(1)
                        .willReturn(aResponse().withBody(Files.readAllBytes(Paths.get("D:/intelliJ/framework/src/main/resources/framework/mock_local.json"))))
        );
        Thread.sleep(100000);
    }

    @Test
    void mockOnProxy() throws InterruptedException, IOException {
        stubFor(
                get(urlMatching(".*"))
                        .atPriority(10)
                        .willReturn(aResponse().proxiedFrom("https://ceshiren.com").withTransformers("ExampleTransformer")));

        stubFor(
                get(urlMatching("/categories_and_latest"))
                        .atPriority(1)
                        .willReturn(aResponse().withBody(Files.readAllBytes(Paths.get("D:/intelliJ/framework/src/main/resources/framework/mock_local.json"))))
        );
        Thread.sleep(100000);
    }
}
