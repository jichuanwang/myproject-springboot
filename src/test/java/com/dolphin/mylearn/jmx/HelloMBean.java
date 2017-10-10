package com.dolphin.mylearn.jmx;

/**
 * Created by jichuan.wang on 2017/9/13.
 */
public interface HelloMBean {
    String getName();
    void setName(String name);
    String getAge();
    void setAge(String age);
    void helloWorld();
    void helloWorld(String str);
    void getTelephone();
}
