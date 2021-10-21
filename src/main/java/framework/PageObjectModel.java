package framework;

import java.util.HashMap;
import java.util.List;


//basepage中load方法可将各page页面反序列化为如下格式,方便我们取值
public class PageObjectModel {
    public String name;
    public HashMap<String, List<HashMap<String, Object>>> methods;

}



