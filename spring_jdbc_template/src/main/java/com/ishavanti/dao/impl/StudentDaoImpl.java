package com.ishavanti.dao.impl;

import com.ishavanti.dao.StudentDao;
import com.ishavanti.entity.Student;
import com.ishavanti.entity.StudentRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert(Student student) {
        String sql = "insert into student(name, sex, born) values(?, ?, ?)";
        jdbcTemplate.update(sql, student.getName(), student.getSex(), student.getBorn());
    }

    public void update(Student student) {
        String sql = "update student set name=?, sex=?, born=? where id=?";
        jdbcTemplate.update(sql, student.getName(), student.getSex(), student.getBorn(), student.getId());
    }

    public void delete(int id) {
        String sql = "delete from student where id=?";
        jdbcTemplate.update(sql, id);
    }

    public Student select(int id) {
        String sql = "select * from student where id=?";
        return jdbcTemplate.queryForObject(sql, new StudentRowMapper(), id);
    }

    public List<Student> selectAll() {
        String sql = "select * from student";
        return jdbcTemplate.query(sql, new StudentRowMapper());
    }
}
