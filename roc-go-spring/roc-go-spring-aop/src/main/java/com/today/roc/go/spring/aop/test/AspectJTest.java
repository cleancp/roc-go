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
public class AspectJTest {

    @Pointcut("execution(* *.test(..))")
    public void test() {

    }

    @Before("test()")
    public void beforeTest(){
        System.out.println("beforeTest");
    }

    @After("test()")
    public void beforeAfter(){
        System.out.println("beforeAfter");
    }

    @Around("test()")
    public Object aroundTest(ProceedingJoinPoint joinPoint){
        System.out.println("aroundTest before");
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("aroundTest after");
        return proceed;
    }

}
