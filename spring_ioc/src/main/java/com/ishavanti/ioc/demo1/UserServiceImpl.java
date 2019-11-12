package com.ishavanti.ioc.demo1;

public class UserServiceImpl implements UserService {

    // 添加一个属性
    private String name;

    public void sayHello() {
        System.out.println("Hello " + name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
