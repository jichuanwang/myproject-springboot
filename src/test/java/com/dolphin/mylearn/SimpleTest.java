package com.dolphin.mylearn;

import com.dolphin.mylearn.springboot.util.PrintUtil;
import org.junit.Test;

/**
 * Created by jichuan.wang on 2017/10/11.
 */
public class SimpleTest {
    @Test
    public void testClassLoader(){
        MyClassloader myClassloader = new MyClassloader();
        PrintUtil.print(myClassloader.getParent().getClass());
    }
}
