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

public class mockBmpTest {


    private BrowserMobProxy proxy;

    @BeforeAll
    void beforeall(){
        //代理服务的初始化
        //根据官方文档介绍引用官方代码
        BrowserMobProxy proxy = new BrowserMobProxyServer();
        //bmp 服务开启监听8083端口
        proxy.start(8083);
    }

    @BeforeEach
    void beforeeach(){
        //每条用例操作执行前创建har文件
        //与每个操作绑定.如下面的click测试
        proxy.newHar("click");
    }

    @AfterEach
    void aftereach(){
        //每条用例操作执行完后生成har文件中的数据
        proxy.endHar();
    }

    @Test
    void click(){
        //自动化测试的各个操作,对各对象的操作将记录下对接口的请求,存在har文件中.
    }


//    @Test
//    void mockOnProxy() throws InterruptedException {
//        //根据官方文档介绍引用官方代码
//        BrowserMobProxy proxy = new BrowserMobProxyServer();
//        //bmp 服务开启监听8083端口
//        proxy.start(8083);
//        proxy.addResponseFilter(new ResponseFilter() {
//            @Override
//            public void filterResponse(HttpResponse httpResponse, HttpMessageContents httpMessageContents, HttpMessageInfo httpMessageInfo) {
//                httpMessageContents.setTextContents(httpMessageContents.getTextContents().replace("DMS后台管理系统", "DMS后管mock"));
//            }
//        });
//        Thread.sleep(100000);
//    }
}
