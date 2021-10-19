package Web;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

public class contactPageWeb extends WebBasePage {

    By countsImport=By.linkText("批量导入/导出");
    By fileImport=By.linkText("文件导入");
    By username=By.name("username");
    By fileUpload=By.linkText("上传文件");

    //重中之重:此处必须重写父类的driver,如果不重写,会启动2个浏览器.
    public contactPageWeb(RemoteWebDriver driver) {
        super(driver);
    }

    public contactPageWeb importFromFile(URL path){
        //todo:
//        System.out.println(path.getPath());

        String path_utf="";
        try {
            path_utf= URLDecoder.decode(path.getFile(), "UTF-8");
            System.out.println(path_utf);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        click(By.cssSelector(".ww_operationBar:nth-child(1) .ww_btn_PartDropdown_left"));
//        click(countsImport);
        click(fileImport);
        upload(fileUpload, path_utf);

        click(By.linkText("导入"));
        click(By.linkText("完成"));

        return this;
    }



    public contactPageWeb addMember(String name, String acctid, String mobile) {
        while (driver.findElements(username).size() == 0) {
            try {
                Thread.sleep(1000);
                click(By.linkText("添加成员"));
                Thread.sleep(1000);
                sendKeys(username, name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        sendKeys(By.name("acctid"), acctid);
        sendKeys(By.name("mobile"), mobile);
        click(By.linkText("保存"));
        return this;
    }

    public contactPageWeb serach(String keyword) {
        clear(By.id("memberSearchInput"));
        sendKeys(By.id("memberSearchInput"), keyword);
        return this;
    }

    public String getName(){
        return Text(By.className("member_display_cover_detail_name"));
    }

    public contactPageWeb delete() {
        try {
            Thread.sleep(1000);
            click(By.linkText("删除"));
            click(By.linkText("确认"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }
}
