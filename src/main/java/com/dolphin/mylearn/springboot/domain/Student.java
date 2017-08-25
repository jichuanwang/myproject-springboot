package com.dolphin.mylearn.springboot.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by jichuan.wang on 2017/8/25.
 */
@Data
public class Student implements Serializable{
    private Long id;
    private String name;
}
