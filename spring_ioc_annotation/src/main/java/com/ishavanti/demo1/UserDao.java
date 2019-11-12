package com.ishavanti.demo1;

import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDao {

    public void save() {
        System.out.println("DAO中的保存用户");
    }
}
