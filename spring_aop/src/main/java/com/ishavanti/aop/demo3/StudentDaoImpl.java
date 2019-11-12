package com.ishavanti.aop.demo3;

public class StudentDaoImpl implements StudentDao {
    @Override
    public void find() {
        System.out.println("查找学生");
    }

    @Override
    public void save() {
        System.out.println("保存学生");
    }

    @Override
    public void update() {
        System.out.println("修改学生");
    }

    @Override
    public void delete() {
        System.out.println("删除学生");
    }
}
