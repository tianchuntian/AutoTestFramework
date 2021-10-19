package frameworkTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import framework.basePage;
import framework.uiAuto;
import org.junit.jupiter.api.*;

import java.io.FileNotFoundException;

class basePageTest {

    private static basePage basePage;

    @BeforeAll
    static void beforeAll(){
        basePage =new basePage();
    }


//    @BeforeEach
//    void setUp() {
//    }
//
//    @AfterEach
//    void tearDown() {
//    }

//    @Test
//    void click() {
////        basePage.click();
//    }
//
//    @Test
//    void send() {
////        basePage.send();
//    }

    @Test
    void load() throws JsonProcessingException, FileNotFoundException {
                                    //此处文件路径格式有讲究
        uiAuto uiauto = basePage.load("uiAuto.yaml");
        ObjectMapper mapper=new ObjectMapper();
        System.out.println(mapper.writeValueAsString(uiauto));
    }

    @Test
    void run() throws FileNotFoundException {
        uiAuto uiauto = basePage.load("uiAuto.yaml");
        basePage.run(uiauto);
    }

//    @AfterAll
//    static void afterAll(){
//        basePage.quit();
//    }


}