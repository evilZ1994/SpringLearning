package com.ishavanti.aop.demo1;
import org.junit.Test;

public class SpringDemo1 {
    @Test
    public void demo1() {
        UserDao userDao = new UserDaoImpl();

        UserDao proxy = (UserDao) new MyJdkProxy(userDao).createProxy();

//        userDao.save();
//        userDao.update();
//        userDao.delete();
//        userDao.find();
        proxy.save();
        proxy.update();
        userDao.delete();
        proxy.find();
    }
}
