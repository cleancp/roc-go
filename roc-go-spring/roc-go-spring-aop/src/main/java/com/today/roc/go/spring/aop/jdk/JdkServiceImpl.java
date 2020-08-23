package com.today.roc.go.spring.aop.jdk;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年08月23日 15:59*
 * log.info()
 */
public class JdkServiceImpl implements JdkService {
    @Override
    public void test() {
        System.out.println("test");
    }
}
