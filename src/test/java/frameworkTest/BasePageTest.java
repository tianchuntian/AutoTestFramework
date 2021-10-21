package frameworkTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import framework.BasePage;
import framework.Factory;
import framework.UIAuto;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.provider.Arguments;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class BasePageTest {
//    static BasePage web;
    private static BasePage basePage;

    @BeforeAll
    static void beforeAll(){
        basePage =new BasePage();
//        web=Factory.create("web");

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

    @Test
    void runPOM() throws FileNotFoundException {
        BasePage web=Factory.create("web");
        basePage.loadPages("src/main/resources/framework");
        UIAuto uiauto = basePage.load("/framework/WebUIAuto3.yaml");
        System.out.println("111111");
        System.out.println(uiauto);
        System.out.println("222222");
        basePage.run(uiauto);
    }




//    @AfterAll
//    static void afterAll(){
//        basePage.quit();
//    }


}