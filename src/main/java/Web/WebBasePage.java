package Web;

import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;

public class WebBasePage extends BasePage {
    RemoteWebDriver driver;
    WebDriverWait wait;

    public WebBasePage() {
        ChromeOptions options = new ChromeOptions();
        //脚本开发阶段调试时浏览器复用
//        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        //取消infobars提示
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 10);
    }

    public WebBasePage(RemoteWebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }


    //重载父类BasePage的action方法,action方法在load解析后得到的是一个map类型对象,与click方法稍有不同.
    @Override
    public void action(HashMap<String, Object> map) {
        super.action(map);
        if (map.get("action").toString().toLowerCase().equals("get")){
            driver.get(map.get("url").toString());
        }



    }

    public void clear(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).clear();
    }

    public void click(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).click();
    }


    //重载父类BasePage中的方法
    @Override
    public void click(HashMap<String, Object> map) {
        super.click(map);
        //click已经被父类BasePage中的click方法过滤了,只剩{id: "name"}了.
        //获取map对象的键,click的键就是yaml文件中原始数据的value的key,如:click: {id: "name"},这里的key取出后,就是id,value就是name
        String key= (String) map.keySet().toArray()[0];
        //获取map对象的值
        String value= (String) map.values().toArray()[0];
        By by=null;
        if (key.toLowerCase().equals("id")){
            by=By.id(value);
        }
        if (key.toLowerCase().equals("linktext")){
            by=By.linkText(value);
        }
        if (key.toLowerCase().equals("partillinktext")){
            by=By.linkText(value);
        }
        if (key.toLowerCase().equals("xpath")){
            by=By.xpath(value);
        }
        if (key.toLowerCase().equals("name")){
            by=By.name(value);
        }
        if (key.toLowerCase().equals("class")){
            by=By.className(value);
        }
        if (key.toLowerCase().equals("cssselector")) {
            by = By.cssSelector(value);
        }
        if (key.toLowerCase().equals("tagname")) {
            by = By.tagName(value);
        }

        click(by);

    }

    public void sendKeys(By by, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).sendKeys(text);
    }

    public void upload(By by, String path) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).sendKeys(path);
    }

    public String Text(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return driver.findElement(by).getText();
    }

    public void quit() {
        driver.quit();
    }


    public void saveCookie() {
        //声明文件读写器和缓存器来对数据io操作
        try {
            //声明文件读写器
            FileWriter fileWriter = new FileWriter("./cookies.txt");
            //声明缓冲器来将读取到的数据先全部缓冲到bufferd中,缓冲完毕在一次性写入到文件file中,减少文件操作,优化IO效率,提升性能
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            //driver.manage().getCookies()得到的是一个数组,for循环该数组中的每个元素,将每个元素中我们需要的内容提取出来,写入缓冲器
            for (Cookie cookie : driver.manage().getCookies()) {
                bufferedWriter.write(
                        cookie.getName() + ';' +
                                cookie.getValue() + ';' +
                                cookie.getDomain() + ';' +
                                cookie.getPath() + ';' +
                                cookie.getExpiry() + ';' +
                                cookie.isSecure()
                );
                //每循环写入一次数据,就换行写入下一次循环数据
                bufferedWriter.newLine();
            }
            //循环写入结束后将缓冲器中的数据将一次性写入文件中,减少文件操作,优化io
            //写入完毕后关闭缓冲器的写入
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadCookie() {
        //加载已存在的cookie的方法
        try {
            FileReader fileReader = new FileReader("./cookies.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            //定义line为读取到的数据
            String line;
            //当从bufferedReader中按行读取的内容不为空时
            while ((line = bufferedReader.readLine()) != null) {
                //StringTokenizer类提供了对字符串进行分解的方法,默认按空格分解,类似python中字符串的的splite分割方法
                StringTokenizer tokenizer = new StringTokenizer(line, ";");//将cookie中读取到的line按照";"分割.
                //tokenizer.nextToken()方法,调用一次后下一次调用会接位偏移,此处表示分割后的字符串的各个部分
                //将分割后的第一部分赋值给String类型的name,一次类推,顺序按照saveCookie方法中保存的各部分的排列顺序读取即可
                String name = tokenizer.nextToken();
                String value = tokenizer.nextToken();
                String domain = tokenizer.nextToken();
                String path = tokenizer.nextToken();
                //先定义一个date 类型的expiry
                Date expiry = null;
                String dt = tokenizer.nextToken();
                if (!dt.equals("null")) {
                    SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyy", Locale.ENGLISH);
                    //把string转换成date类型
                    expiry = sdf.parse(dt);
                }
                //把string转换成boolean类型
                boolean isSecure = Boolean.parseBoolean(tokenizer.nextToken());
                Cookie cookie = new Cookie(name, value, domain, path, expiry, isSecure);
                //循环地将cookie添加到浏览器,循环结束之后,所有的cookie就都添加到了浏览器
                driver.manage().addCookie(cookie);
            }
            //捕获所有异常,不然上面的bufferedReader.readLine()方法会报红
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
