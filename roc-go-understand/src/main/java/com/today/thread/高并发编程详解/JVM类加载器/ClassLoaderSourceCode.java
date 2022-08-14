package com.today.thread.高并发编程详解.JVM类加载器;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月07日 11:37*
 * log.info()
 */
public class ClassLoaderSourceCode {

    /***
     //resolve ； 是否进行解析
     protected Class<?> loadClass(String name, boolean resolve)
     throws ClassNotFoundException
     {
     //加载的类路径同步锁
     synchronized (getClassLoadingLock(name)) {
     // First, check if the class has already been loaded
     //从已加载类中获取，如果存在则返回
     Class<?> c = findLoadedClass(name);
     if (c == null) {
     long t0 = System.nanoTime();
     try {
     if (parent != null) {
     //父加载器加载
     c = parent.loadClass(name, false);
     } else {
     //parent为空 从根加载器加载
     c = findBootstrapClassOrNull(name);
     }
     } catch (ClassNotFoundException e) {
     // ClassNotFoundException thrown if class not found
     // from the non-null parent class loader
     }
     //如果父加载器加载之后还是 null
     if (c == null) {
     // If still not found, then invoke findClass in order
     // to find the class.
     long t1 = System.nanoTime();
     //丛当前加载器加载
     c = findClass(name);

     // this is the defining class loader; record the stats
     sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
     sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
     sun.misc.PerfCounter.getFindClasses().increment();
     }
     }
     if (resolve) {
     resolveClass(c);
     }
     return c;
     }
     }**/

}
