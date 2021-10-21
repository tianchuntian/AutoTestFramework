package framework;

import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class BasePage {

    List<PageObjectModel> pages=new ArrayList<>();

    public void click(HashMap<String, Object> map) {
        System.out.println("调用了basepage的click方法");
        System.out.println(map);
    }


    public void sendKeys(HashMap<String, Object> map) {
        System.out.println("调用了basepage的sendKeys方法");
        System.out.println(map);
    }


    public void action(HashMap<String, Object> map) {
//        System.out.println("调用了basepage的action方法");
        System.out.println(map);
        //如果关键字是page,就去找对应封装的xxxPage.yaml文件,并加载
        if (map.containsKey("page")){
            //获取解析出的pageName,方便在众多pages中对应查找
            String pageName=map.get("page").toString();
            String action=map.get("action").toString();
            //输出找到的对应的文件名
            pages.forEach(pom->System.out.println(pom.name));
            //从pages中过滤寻找与xxxPage文件中相对应的pageName,然后取出methods对应的各操作方法
            pages.stream().filter(pom->pom.name.equals(pageName)).findFirst().get().methods.get(action).forEach(step->{action(step);});
        }else{
            //前期封装的自动化级别的
            if (map.containsKey("click")) {
                //此处将m.get("click")出的数据强转成hashmap类型对象
                HashMap<String, Object> by = (HashMap<String, Object>) map.get("click");
                click(by);
            }
            if (map.containsKey("sendKeys")) {
                sendKeys(map);
            }
        }

    }




    public UIAuto load(String path) throws FileNotFoundException {
//        System.out.println("调用了basepage的load方法");
        //从外部数据源中加载数据,并返回一个uiAUTO类型对象,供run方法调用
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        UIAuto uiauto = null;
        //把从yaml文件中读取出的数据强转成uiAuto类型
        try {
            uiauto = mapper.readValue(
                    BasePage.class.getResourceAsStream(path),
                    UIAuto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println("调用了basepage的load方法");
        return uiauto;
    }


    //读取外部xxxPage的yaml文件,将读取出的数据反序列化为PageObjectModel类型数据
    public PageObjectModel loadPage(String path)  {
//        System.out.println("调用了basepage的loadPage方法");
        //从外部数据源中加载数据,并返回一个uiAUTO类型对象,供run方法调用
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        PageObjectModel pom = null;
        //把从yaml文件中读取出的数据强转成uiAuto类型
        try {
            pom = mapper.readValue(
                    new File(path),
                    PageObjectModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pom;
    }



    //读取外部某个目录中的所有名字为xxxPage的yaml文件,将读取出的数据反序列化为PageObjectModel类型数据
    public void loadPages(String dir) {
        //从外部数据源中加载数据,并返回一个uiAUTO类型对象,供run方法调用
        Stream.of(new File(dir).list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.contains("_page");
            }
        })).forEach(path -> {
            path = dir + "/" + path;
            System.out.println(path);
            pages.add(loadPage(path));
        });
    }








    public void run(UIAuto uiauto) {
//        System.out.println("调用了basepage的run方法");
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



//            if (m.containsKey("page")) {
//                action(m);
//            }
        });
    }


    public void quit() {
        System.out.println("quit");
    }
}





