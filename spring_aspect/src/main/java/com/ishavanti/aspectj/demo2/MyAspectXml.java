package com.ishavanti.aspectj.demo2;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class MyAspectXml {

    // 前置通知
    public void before(JoinPoint joinPoint) {
        // 可以通过JointPoint参数获取切点对象
        System.out.println("XML方式的前置通知 ..." + joinPoint);
    }

    // 后置通知
    public void afterReturning(Object result) {
        // 可以通过参数获取目标方法的返回值（注意这里的参数命名要与配置文件中returning属性的值一致）
        System.out.println("XML方式的后置通知 ..." + result);
    }

    // 环绕通知
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 可以通过ProceedingJoinPoint参数获取切点对象，并决定是否执行目标方法
        System.out.println("XML方式的环绕前通知 ...");
        Object obj = joinPoint.proceed();  // 执行目标方法
        System.out.println("XML方式的环绕后通知 ...");
        return obj;
    }

    // 异常抛出通知
    public void afterThrowing(Throwable e) {
        // 可以通过Throwable参数获取异常对象（注意这里的参数命名要与配置文件中throwing属性的值一致）
        System.out.println("XML方式的异常抛出通知 ..." + e.getMessage());
    }

    // 最终通知
    public void after() {
        // 无论是否有异常，都会执行
        System.out.println("XML方式的最终通知 ...");
    }
}
