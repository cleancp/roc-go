package com.today.roc.go.spring.aop.jdk;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年08月23日 16:23*
 * log.info()
 */
public class JdkMain {

    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        JdkService jdkService = new JdkServiceImpl();
        JdkInvocationHandler invocationHandler = new JdkInvocationHandler(jdkService);
        JdkService proxy = (JdkService)invocationHandler.getProxy();
        proxy.test("1");
    }
}
