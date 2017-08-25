package com.dolphin.mylearn.springboot.service.impl;

import com.dolphin.mylearn.springboot.domain.Student;
import com.dolphin.mylearn.springboot.service.StudentService;
import com.dolphin.mylearn.springboot.util.PrintUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.List;

/**
 * Created by jichuan.wang on 2017/8/25.
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<Student> getStudent() {
        String sql = "select * from student";
        return jdbcTemplate.query(sql, new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet resultSet, int i) throws SQLException {
                Student student = new Student();
                student.setId(resultSet.getLong("ID"));
                student.setName(resultSet.getString("name"));
                return student;
            }
        });
    }

    @Override
    public Student addStudent(final Student student) {
        final String sql = "insert into student (name) values(?)";
        KeyHolder holder = new GeneratedKeyHolder();
        int count = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1,student.getName());
                return preparedStatement;
            }
        },holder);
        Long newStudentId = holder.getKey().longValue();
        student.setId(newStudentId);
        return student;
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        String sql = "delete from student where id=?";
        int result = jdbcTemplate.update(sql,new Object[]{id});
        PrintUtil.print(result);
//        throw new RuntimeException("测试事务");
    }
}
