package framework;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

class FactoryTest {

    @Test
    void creat() throws FileNotFoundException {
        BasePage web= Factory.create("web");
        UIAuto uiAuto=web.load("WebUIAuto.yaml");
        System.out.println("111111");
        System.out.println(uiAuto);
        System.out.println("222222");
        web.run(uiAuto);


    }
}