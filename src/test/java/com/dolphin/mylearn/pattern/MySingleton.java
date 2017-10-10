package com.dolphin.mylearn.pattern;

/**
 * Created by jichuan.wang on 2017/10/10.
 */
public class MySingleton {
    public static class MyInstance{
        public static MyInstance instance = new MyInstance();
    }
    public class MySingletonHolder{

    }
}
