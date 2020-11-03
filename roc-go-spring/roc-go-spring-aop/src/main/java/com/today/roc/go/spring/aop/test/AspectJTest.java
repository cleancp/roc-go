package com.today.roc.go.spring.aop.test;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年08月09日 22:23*
 * log.info()
 */
@Aspect
@Order(3)
@Component
public class AspectJTest {

    @Pointcut("@annotation(com.today.roc.go.spring.aop.annotation.AspectJAnnotation)")
    public void test() {
    }

//    @Pointcut("execution(* *.test(..))")
//    public void test() {
//    }

    @Before("test()")
    public void beforeTest() {
        System.out.println("beforeTest");
    }

    @After("test()")
    public void afterTest() {
        System.out.println("afterTest");
    }

    @Around("test()")
    public Object aroundTest(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("aroundTest before");
        Object proceed = joinPoint.proceed();
        System.out.println("aroundTest after");
        return proceed;
    }

    @AfterReturning(returning = "rvt", pointcut = "test()")
    // 声明rvt时指定的类型会限制目标方法必须返回指定类型的值或没有返回值
    // 此处将rvt的类型声明为Object，意味着对目标方法的返回值不加限制
    public void afterReturning(Object rvt) {
        System.out.println("afterReturning:" + rvt);
    }

    @AfterThrowing(pointcut = "test()", throwing = "e")
    public void afterThrowing(Throwable e) {
        System.out.println("afterThrowing " + e.getMessage());
    }

    public void noAnnotation() {
        System.out.println("没有注解的普通方法");
    }
}
