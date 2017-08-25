package com.dolphin.mylearn.springboot.config;

import com.dolphin.mylearn.springboot.util.PrintUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jichuan.wang on 2017/8/25.
 */
@ControllerAdvice
public class ControllerAdviceTest {
    @ExceptionHandler(RuntimeException.class)
    public void processException(HttpServletRequest request,Throwable e){
        PrintUtil.print("有异常来了");
    }
}
