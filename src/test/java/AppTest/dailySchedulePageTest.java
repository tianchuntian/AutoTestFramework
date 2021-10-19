package AppTest;

import App.dailySchedulePage;
import App.mainPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class dailySchedulePageTest {
    static mainPage main;
    static dailySchedulePage dailySchedule;
    @BeforeAll
    static void beforeAll(){
        main=new mainPage();
        dailySchedule=main.toDailySchedule();
    }

    @AfterAll
    static void tearDown(){
        dailySchedule.quit();
    }


    @Test
    void addSchedule() {
        dailySchedule.addSchedule("1234");
    }

//    @Test
//    void input() {
//        dailySchedule.input("刘备");
//    }


}