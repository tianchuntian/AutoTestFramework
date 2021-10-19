package App;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class dailySchedulePage extends AppBasePage {
   By addSchedule= By.id("com.tencent.wework:id/hcg");
   By schedule= By.id("com.tencent.wework:id/bq5");
   By deleteButton= By.id("com.tencent.wework:id/bn_");
   By theme=By.id("com.tencent.wework:id/b8d");
    public dailySchedulePage(AndroidDriver driver) {
        super(driver);
    }

    public dailySchedulePage addSchedule(String keword) {
        while (isEmpty(By.id("com.tencent.wework:id/bq5")) > 0) {
            deleteSchedule();
        }
        click(addSchedule);
        sendKeys(theme,keword);
        click("上午");
        click("保存");
        return this;
    }

    public void deleteSchedule(){
        click(schedule);
        click(deleteButton);
        click("删除");
    }

}
