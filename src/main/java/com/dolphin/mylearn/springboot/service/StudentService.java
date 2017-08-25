package com.dolphin.mylearn.springboot.service;

import com.dolphin.mylearn.springboot.domain.Student;

import java.util.List;

/**
 * Created by jichuan.wang on 2017/8/25.
 */
public interface StudentService {
    List<Student> getStudent();
    Student addStudent(Student student);
    void deleteById(Long id);
}
