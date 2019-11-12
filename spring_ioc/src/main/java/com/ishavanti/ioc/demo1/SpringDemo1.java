package com.ishavanti.ioc.demo1;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDemo1 {
    /**
     * 传统方式开发
     */
    @Test
    public void demo1() {
        // UserService us = new UserServiceImpl();
        // UserServiceImpl中新增了一个属性name，UserService对象不可以操作这个属性
        UserServiceImpl us = new UserServiceImpl();
        us.setName("张三");
        us.sayHello();
    }

    /**
     * 使用Spring的方式
     */
    @Test
    public void demo2() {
        // 创建Spring的工厂
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 通过工厂获得类对象
        UserService us = (UserService) applicationContext.getBean("userService");
        us.sayHello();
    }
}
