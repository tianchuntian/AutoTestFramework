package AppTest;

import App.mainPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class mainPageTest {
    static mainPage main;
    @BeforeAll
    static void beforeAll(){
        main=new mainPage();
    }


    @AfterAll
    static void afterAll(){
        main.quit();
    }


    @Test
    void toDailySchedule() {
        System.out.println("1111");
        main.toDailySchedule();
    }

    @Test
    void toToDo() {
        System.out.println("2222");
        main.toToDo();
    }
}