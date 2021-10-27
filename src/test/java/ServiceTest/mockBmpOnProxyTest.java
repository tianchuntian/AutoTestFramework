package ServiceTest;

import io.netty.handler.codec.http.HttpResponse;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.filters.ResponseFilter;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class mockBmpOnProxyTest {

    //修改response
    @Test
    void mockOnProxy() throws InterruptedException {
//        //根据官方文档介绍引用官方代码
        BrowserMobProxy proxy = new BrowserMobProxyServer();
        //bmp 服务开启监听8083端口
        proxy.start(8083);
        proxy.addResponseFilter(new ResponseFilter() {
            @Override
            public void filterResponse(HttpResponse httpResponse, HttpMessageContents httpMessageContents, HttpMessageInfo httpMessageInfo) {
                httpMessageContents.setTextContents(httpMessageContents.getTextContents().replace("DMS", "DMS111"));
            }
        });
        Thread.sleep(1000000);
    }
}
