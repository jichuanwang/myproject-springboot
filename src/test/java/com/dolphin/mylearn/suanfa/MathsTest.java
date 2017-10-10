package com.dolphin.mylearn.suanfa;

import com.dolphin.mylearn.springboot.util.PrintUtil;
import org.junit.Test;

/**
 * Created by jichuan.wang on 2017/9/8.
 */
public class MathsTest {
    @Test
    public void gcdTest(){
        PrintUtil.print(gcd(1997,615));
    }
    public int gcd(int a,int b){
        if(a % b == 0){
            return b;
        }else {
            return gcd(b,a%b);
        }
    }
}
