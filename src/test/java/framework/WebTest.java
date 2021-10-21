package framework;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class WebTest {

    private static framework.BasePage BasePage;


    @ParameterizedTest(name = "{index} {1}")
    @MethodSource
    @DisplayName("😱")
    void classic(UIAuto uiAuto,String path){
        BasePage.run(uiAuto);
    }

    //从外读取多个文件中的测试数据,然后驱动run
    static List<Arguments> classic(){
        BasePage= Factory.create("web");
        List<Arguments> all= new ArrayList<Arguments>();
        Arrays.asList(
                "WebUIAuto.yaml",
                "WebUIAuto2.yaml"
        ).stream().forEach(path->{
            UIAuto uiAuto= null;
            try {
                        //此处load 可能产生空指针异常
                uiAuto = BasePage.load(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            uiAuto.description=path;
            all.add(arguments(uiAuto, path));
        });
        return all;



    }


}
