package com.dolphin.mylearn.regex;

import com.dolphin.mylearn.springboot.util.PrintUtil;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jichuan.wang on 2017/10/9.
 */
public class MyRegexTest {
    @Test
    public void test01(){
        Pattern p=Pattern.compile(".*?w$");
        Matcher matcher = p.matcher("wo . dew \t asdfasss");

        while(matcher.find()){
            PrintUtil.print(matcher.group()+"-------"+matcher.start()+"-------"+matcher.end());
        }
    }
}
