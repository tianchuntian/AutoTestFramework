package framework;

import App.AppBasePage;
import Web.WebBasePage;

public class Factory {

    public static BasePage create(String driverName){
        if (driverName.toLowerCase().equals("web") || driverName.toLowerCase().equals("selenium")){
            return new WebBasePage();
        }
        if (driverName.toLowerCase().equals("app") || driverName.toLowerCase().equals("appium")){
            return new AppBasePage();
        }
        if (driverName.toLowerCase().equals("uiautomator")){
            //返回uiautomator相关引擎
//            return new WebBasePage();
        }
        if (driverName.toLowerCase().equals("atx")){
            //返回atx相关引擎
//            return new WebBasePage();
        }


        return null;
    }


}
