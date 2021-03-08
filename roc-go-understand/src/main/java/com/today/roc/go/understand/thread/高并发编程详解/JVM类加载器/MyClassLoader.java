package com.today.roc.go.understand.thread.高并发编程详解.JVM类加载器;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月06日 22:42*
 * log.info()
 */
//自定义加载器必须是ClassLoader的直接或间接子类
public class MyClassLoader extends ClassLoader {

    //定义默认的class存放路径
    public static final Path DEFAULT_CLASS_DIR = Paths.get("E:\\");

    //允许指定class存放路径
    private final Path classDir;

    //默认加载目录
    public MyClassLoader() {
        super();
        this.classDir = DEFAULT_CLASS_DIR;
    }

    //默认加载目录
    public MyClassLoader(ClassLoader parent) {
        super(parent);
        this.classDir = DEFAULT_CLASS_DIR;
    }

    public MyClassLoader(String classDir) {
        super();
        this.classDir = Paths.get(classDir);
    }

    public MyClassLoader(ClassLoader parent, String classDir) {
        super(parent);
        this.classDir = Paths.get(classDir);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //获取类的二进制流
        byte[] classBytes = this.readClassBytes(name);
        if (Objects.isNull(classBytes) || classBytes.length == 0) {
            throw new ClassNotFoundException("can not load the class " + name);
        }
        return this.defineClass(name, classBytes, 0, classBytes.length);
    }

    private byte[] readClassBytes(String name) throws ClassNotFoundException {
        //将包名转换为文件路径分隔符
        String classPath = name.replace(".", "/");
        //拼接上classDir路径 class文件全路径
        Path classFullPath = classDir.resolve(Paths.get(classPath + ".class"));
        if (!classFullPath.toFile().exists()) {
            throw new ClassNotFoundException("The class " + name + " not found .");
        }

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Files.copy(classFullPath, baos);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new ClassNotFoundException("load the class " + name + " not occur error .", e);
        }
    }

    @Override
    public String toString() {
        return "My ClassLoader";
    }

}
