package framework;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class FactoryTest {

    @Test
    void creat() throws FileNotFoundException {
        BasePage web= Factory.create("web");
        UIAuto uiAuto=web.load("WebUIAuto.yaml");
        web.run(uiAuto);


    }
}