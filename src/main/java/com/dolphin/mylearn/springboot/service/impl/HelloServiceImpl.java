package com.dolphin.mylearn.springboot.service.impl;

import com.dolphin.mylearn.springboot.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * Created by jichuan.wang on 2017/8/24.
 */
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello(String name) {
        System.out.println("您好："+name);
    }
}
