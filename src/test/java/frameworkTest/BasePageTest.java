package frameworkTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import framework.BasePage;
import framework.UIAuto;
import org.junit.jupiter.api.*;

import java.io.FileNotFoundException;

class BasePageTest {

    private static BasePage basePage;

    @BeforeAll
    static void beforeAll(){
        basePage =new BasePage();
    }


    @Test
    void load() throws JsonProcessingException, FileNotFoundException {
                                    //此处文件路径格式有讲究
        UIAuto uiauto = basePage.load("UIAuto.yaml");
        ObjectMapper mapper=new ObjectMapper();
        System.out.println(mapper.writeValueAsString(uiauto));
    }

    @Test
    void run() throws FileNotFoundException {
        UIAuto uiauto = basePage.load("UIAuto.yaml");
        basePage.run(uiauto);
    }

//    @AfterAll
//    static void afterAll(){
//        basePage.quit();
//    }


}