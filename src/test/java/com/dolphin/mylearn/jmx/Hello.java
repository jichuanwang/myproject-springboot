package com.dolphin.mylearn.jmx;

/**
 * Created by jichuan.wang on 2017/9/13.
 */
public class Hello implements HelloMBean {
    private String name;
    private String age;

    @Override
    public String getName() {
        System.out.println("get name 123");
        return name;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public String getAge() {
        System.out.println("get age 123");
        return age;
    }

    @Override
    public void setAge(String age) {

    }

    @Override
    public void helloWorld() {
        System.out.println("hello world");
    }

    @Override
    public void helloWorld(String str) {
        System.out.println("helloWorld:" + str);
    }

    @Override
    public void getTelephone() {
        System.out.println("get Telephone");
    }
}
