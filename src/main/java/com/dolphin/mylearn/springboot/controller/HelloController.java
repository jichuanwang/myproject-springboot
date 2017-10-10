package com.dolphin.mylearn.springboot.controller;

import com.dolphin.mylearn.springboot.config.MyBean;
import com.dolphin.mylearn.springboot.domain.Student;
import com.dolphin.mylearn.springboot.service.HelloService;
import com.dolphin.mylearn.springboot.service.StudentService;
import com.dolphin.mylearn.springboot.util.PrintUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by jichuan.wang on 2017/8/17.
 */
@Controller
public class HelloController {
    @Autowired
    private HelloService helloService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private MyBean myBean;


    @RequestMapping("/")
    public String hello(){
        return "/view/index.html";
    }
    @RequestMapping("/first")
    public String firstPage(){
        return "first";
    }
    @RequestMapping("/getStudents")
    @ResponseBody
    public List<Student> getStudents(){
        return studentService.getStudent();
    }
}
