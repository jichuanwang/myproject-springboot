package com.dolphin.mylearn;

/**
 * Created by jichuan.wang on 2017/10/11.
 */
public class MyClassloader extends ClassLoader {
    private String path;
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
}
