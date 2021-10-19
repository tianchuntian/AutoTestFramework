package Web;

import org.openqa.selenium.By;

public class mainPageWeb extends WebBasePage {
    String url = "https://work.weixin.qq.com/wework_admin/frame#index";

    public mainPageWeb() {
        //打开网址进入首页
        driver.manage().window().maximize();
        driver.get(url);
//        try {
//            Thread.sleep(15000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        //保存cookie
//        saveCookie();
        //给浏览器加载cookie
        loadCookie();
        //再次打开网页
        driver.get(url);
    }

    //进入首页后去联系人菜单
    public contactPageWeb toContact() {
        click(By.linkText("通讯录"));
        //重中之重,此处必须将driver传给下一个页面contactPage,contactPage必须有个方法来承接这个driver
        return new contactPageWeb(driver);
    }
}
