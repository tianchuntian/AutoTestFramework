package framework;

import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class basePage {


    public void click(HashMap<String, Object> map){
        System.out.println("调用了basepage的click方法");
        System.out.println(map);
    }

    public void sendKeys(HashMap<String, Object> map){
        System.out.println("调用了basepage的sendKeys方法");
        System.out.println(map);
    }

    public void action(HashMap<String,Object> map){
        System.out.println("调用了basepage的action方法");
        System.out.println(map);
    }

    public uiAuto load(String path) throws FileNotFoundException {
        System.out.println("调用了basepage的load方法");
//        System.out.println("load");
//        System.out.println("111");

        //从外部数据源中加载数据,并返回一个uiAUTO类型对象,供run方法调用
        ObjectMapper mapper= new ObjectMapper(new YAMLFactory());
//        System.out.println("2222");
        uiAuto uiauto=null;
//        System.out.println("333");
        //把从yaml文件中读取出的数据强转成uiAuto类型
        try {
//            System.out.println("4444");
            uiauto = mapper.readValue(
                    basePage.class.getResourceAsStream(path),
                    uiAuto.class);
//            System.out.println("5555");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uiauto;

    }



    public void run (uiAuto uiauto){
        System.out.println("调用了basepage的run方法");
//        System.out.println("run");
        //根据load加载的uiAto类型数据遍历,根据遍历出的内容判断应该执行什么操作
        uiauto.steps.stream().forEach(m -> {
            if (m.containsKey("click")) {
                //此处将m.get("click")出的数据强转成hashmap类型对象
                HashMap<String, Object> by = (HashMap<String, Object>) m.get("click");
                click(by);
            }
            if (m.containsKey("sendKeys")) {
                sendKeys(m);
            }
            if (m.containsKey("action")) {
                action(m);
            }
        });
    }

//        uiauto.steps.stream().forEach(m -> {
//            if (m.containsKey("click")) {
//                HashMap<String, Object> by = (HashMap<String, Object>) m.get("click");
//                click(by);
//            }
//
//            if (m.containsKey("sendKeys")) {
//                sendKeys(m);
//            }
//
//            if (m.containsKey("action")) {
//                action(m);
//            }
//        });
//    }

    public void quit () {
        System.out.println("quit");
    }
}
