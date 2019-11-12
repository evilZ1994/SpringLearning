package com.ishavanti.dao.impl;

import com.ishavanti.dao.CourseDao;
import com.ishavanti.entity.Course;
import com.ishavanti.entity.CourseRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseDaoImpl implements CourseDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert(Course course) {
        String sql = "insert into course(name, score) values(?, ?)";
        jdbcTemplate.update(sql, course.getName(), course.getScore());
    }

    public void update(Course course) {
        String sql = "update course set name=?, score=? where id=?";
        jdbcTemplate.update(sql, course.getName(), course.getScore(), course.getId());
    }

    public void delete(int id) {
        String sql = "delete from course where id=?";
        jdbcTemplate.update(sql, id);
    }

    public Course select(int id) {
        String sql = "select * from course where id=?";
        return jdbcTemplate.queryForObject(sql, new CourseRowMapper(), id);
    }

    public List<Course> selectAll() {
        String sql = "select * from course";
        return jdbcTemplate.query(sql, new CourseRowMapper());
    }
}
