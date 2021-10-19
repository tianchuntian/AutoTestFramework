package WebTest;

import Web.contactPageWeb;
import Web.mainPageWeb;
import com.sun.org.glassfish.gmbal.Description;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class contactPageTest {

    static mainPageWeb main;
    static contactPageWeb contact;

    @BeforeAll
    static void beforeAll() {
        main = new mainPageWeb();
        contact = main.toContact();
    }

    @Description("增加成员")
    @DisplayName("增加成员")
    @Order(1)
    @Test
    void addMember() {
        String text=contact.addMember("aa", "bb", "15528399999").serach("aa").getName();
        assertEquals("aa",text);
    }


    @DisplayName("搜索成员")
    @Order(2)
    @Test
    void searchMember(){
        contact.serach("aa").delete();
    }


//    @Order(3)
//    @Test
//    void testImportFromFile(){
//        //todo: 中文名
//        contact.importFromFile(Objects.requireNonNull(this.getClass().getResource("/通讯录批量导入模板.xlsx")));
//
//    }




//    @Description("删除成员")
//    @DisplayName("删除成员")
//    @Order(3)
//    @Test
//    void delete(){
//        contact.delete();
//    }
    @AfterAll
    static void afterAll() {
        try {
            Thread.sleep(2000);
            contact.quit();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}