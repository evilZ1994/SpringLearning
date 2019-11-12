package com.ishavanti.ioc.demo3;

public class UserDaoImpl implements UserDao {
    public void add() {
        System.out.println("增加用户");
    }

    public void delete() {
        System.out.println("查找用户");
    }

    public void update() {
        System.out.println("修改用户");
    }

    public void find() {
        System.out.println("查找用户");
    }
}
