package Web;


public class mainCsdnPageWeb extends WebBasePage {
    String urllist[] ={
            "https://blog.csdn.net/qq_40132294/article/details/120706727",
            "https://blog.csdn.net/qq_40132294/article/details/120699419",
            "https://blog.csdn.net/qq_40132294/article/details/120687564",
            "https://blog.csdn.net/qq_40132294/article/details/120661339",
            "https://blog.csdn.net/qq_40132294/article/details/120545298",
            "https://blog.csdn.net/qq_40132294/article/details/120540381",
            "https://blog.csdn.net/qq_40132294/article/details/120539680",
            "https://blog.csdn.net/qq_40132294/article/details/120538715",
            "https://blog.csdn.net/qq_40132294/article/details/120520103",
            "https://blog.csdn.net/qq_40132294/article/details/120518050",
            "https://blog.csdn.net/qq_40132294/article/details/120517669",
            "https://blog.csdn.net/qq_40132294/article/details/120487174",
            "https://blog.csdn.net/qq_40132294/article/details/120424822",
            "https://blog.csdn.net/qq_40132294/article/details/120276921",
            "https://blog.csdn.net/qq_40132294/article/details/120255930",
            "https://blog.csdn.net/qq_40132294/article/details/120253641",
            "https://blog.csdn.net/qq_40132294/article/details/120244348",
            "https://blog.csdn.net/qq_40132294/article/details/120243068",
            "https://blog.csdn.net/qq_40132294/article/details/120714386",
            "https://blog.csdn.net/qq_40132294/article/details/120727988",
            "https://blog.csdn.net/qq_40132294/article/details/120237591",
            "https://blog.csdn.net/qq_40132294/article/details/120773488",
            "https://blog.csdn.net/qq_40132294/article/details/120217656",
            "https://blog.csdn.net/qq_40132294/article/details/120212063",
            "https://blog.csdn.net/qq_40132294/article/details/120209336",
            "https://blog.csdn.net/qq_40132294/article/details/120140137",
            "https://blog.csdn.net/qq_40132294/article/details/120103551",
            "https://blog.csdn.net/qq_40132294/article/details/120238635"
    };

    public mainCsdnPageWeb() {
        //打开网址进入首页
        driver.manage().window().maximize();
        for (int i=0;i<101;i++){
            for (String s : urllist) {
                driver.get(s);
        }
            System.out.println(i);
        }
    }

}
