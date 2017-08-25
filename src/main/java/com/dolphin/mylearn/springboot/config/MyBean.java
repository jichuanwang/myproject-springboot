package com.dolphin.mylearn.springboot.config;

import com.dolphin.mylearn.springboot.util.PrintUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

/**
 * Created by jichuan.wang on 2017/8/24.
 */
@Component
public class MyBean {
    @Value("${name}")
    private String name;
    @Value("${sayToYou}")
    private String sayToYou;

    public String getSayToYou() {
        return sayToYou;
    }

    public String getName(){
       return name;
    }
}
