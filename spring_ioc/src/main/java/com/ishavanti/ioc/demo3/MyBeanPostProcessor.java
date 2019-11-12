package com.ishavanti.ioc.demo3;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyBeanPostProcessor implements BeanPostProcessor {
    public Object postProcessBeforeInitialization(Object bean, String s) throws BeansException {
        System.out.println("5. 初始化前方法");
        return bean;
    }

    public Object postProcessAfterInitialization(final Object bean, String beanName) throws BeansException {
        System.out.println("8. 初始化后方法");
        // 对UserDao做增强
        if ("userDao".equals(beanName)) {
            // 生成代理对象
            Object proxy = Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    // 增强add()方法
                    if ("add".equals(method.getName())) {
                        System.out.println("权限校验");
                        return method.invoke(bean, args);
                    }
                    return method.invoke(bean, args);
                }
            });
            return proxy;
        }
        return bean;
    }
}
