package com.today.roc.go.spring.beans.replace_method;

import org.springframework.beans.factory.support.MethodReplacer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Method;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年07月26日 22:27*
 * log.info()
 */
public class TestMethodReplacer implements MethodReplacer {

    @Override
    public Object reimplement(Object o, Method method, Object[] objects) throws Throwable {
        File springPath = new File("WebContent/WEB-INF/config/spring");
        File[] files = springPath.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (!name.startsWith("spring-default") && !name.startsWith("spring-remote")) {
                    return true;
                } else {
                    return false;
                }
            }
        });
        String[] paths = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            if (!files[i].isDirectory()) {
                String abPath = files[i].getAbsolutePath();
                paths[i] = abPath;
            }
        }
        ApplicationContext ctx = new FileSystemXmlApplicationContext(paths, true);
        return null;
    }
}
