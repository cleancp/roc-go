package com.today.roc.go.spring.aop.test;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
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
@Component
public class AspectJTwoTest {

    @Pointcut("@annotation(com.today.roc.go.spring.aop.annotation.AspectJTwoAnnotation)")
    public void testTwo() {
    }

//    @Pointcut("execution(* *.testAnnotation(..))")
//    public void testTwo() {
//    }

    @Before("testTwo()")
    public void beforeTwoTest() {
        System.out.println("beforeTwoTest");
    }

    @After("testTwo()")
    public void afterTwoTest() {
        System.out.println("afterTwoTest");
    }

    @Around("testTwo()")
    public Object aroundTwoTest(ProceedingJoinPoint joinPoint) {
        System.out.println("aroundTwoTest before");
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("aroundTwoTest after");
        return proceed;
    }

    @AfterReturning(returning = "rvt", pointcut = "testTwo()")
    // 声明rvt时指定的类型会限制目标方法必须返回指定类型的值或没有返回值
    // 此处将rvt的类型声明为Object，意味着对目标方法的返回值不加限制
    public void afterTwoReturning(Object rvt) {
        System.out.println("afterTwoReturning:" + rvt);
    }

    @AfterThrowing(pointcut = "testTwo()", throwing = "e")
    public void afterTwoThrowing(Throwable e) {
        System.out.println("afterTwoThrowing "+e.getMessage());
    }

    public void noTwoAnnotation(){
        System.out.println("没有注解的普通方法");
    }
}
