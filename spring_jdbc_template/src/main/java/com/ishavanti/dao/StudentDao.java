package com.ishavanti.dao;

import com.ishavanti.entity.Student;

import java.util.List;

public interface StudentDao {
    void insert(Student student);

    void update(Student student);

    void delete(int id);

    Student select(int id);

    List<Student> selectAll();
}
