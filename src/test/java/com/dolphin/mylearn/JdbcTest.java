package com.dolphin.mylearn;

import com.dolphin.mylearn.springboot.domain.Student;
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
public class JdbcTest extends BaseTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Test
    public void testQuery(){
        String sql = "select * from student";
        List<Student> studentList = jdbcTemplate.query(sql, new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet resultSet, int i) throws SQLException {
                Student student = new Student();
                student.setId(resultSet.getLong("ID"));
                student.setName(resultSet.getString("name"));
                return student;
            }
        });
    }

    @Test
    public void testInsert(){
        final String sql = "insert into student (name) values(?)";
        KeyHolder holder = new GeneratedKeyHolder();
        int count = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1,"姜健");
                return preparedStatement;
            }
        },holder);
       int newStudentId = holder.getKey().intValue();
        PrintUtil.print(newStudentId);
    }
}
