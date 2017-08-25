package com.dolphin.mylearn;

import com.dolphin.mylearn.springboot.domain.Student;
import com.dolphin.mylearn.springboot.service.StudentService;
import com.dolphin.mylearn.springboot.util.PrintUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.util.List;

/**
 * Created by jichuan.wang on 2017/8/25.
 */
public class StudentServiceTest extends BaseTest {
    @Autowired
    private StudentService studentService;

    @Test
    public void testInsert(){
        Student student = new Student();
        student.setName("姜健");
        studentService.addStudent(student);
    }

    @Test
    public void testDelete(){
        studentService.deleteById(3L);
    }

}
