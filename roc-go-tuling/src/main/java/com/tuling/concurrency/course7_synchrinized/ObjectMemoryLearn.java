package com.tuling.concurrency.course7_synchrinized;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description 对象内存布局
 * @createTime 2022年09月03日 11:07*
 * log.info()
 */
@Slf4j
public class ObjectMemoryLearn {

    public static void main(String[] args) {
        // 对象头
        objectHeader();
        // 实例数据 instanceData
        // (不包括静态成员变量,因为它是在方法区维护的)
        // 对象填充  填充成8字节整数倍 padding
        log.debug(ClassLayout.parseInstance(new Object()).toPrintable());
    }

    private static  void objectHeader(){
        // 对象头
        // MarkWord 32位系统 占4字节 64位系统 占8字节
        /**
                   hashcode 偏向线程ID  epoch2bit 分代年龄4bit  是否偏向1bit  锁标记2bit
          无锁                                                       0             01
        偏向锁                                                       1             01
        轻量级                                                                     00
        重量级                                                                     10
            GC                                                                     11
         */
        // KclassPointer
        // 开启指针压缩占4字节，未开启指针压缩占8字节

        // 数组长度 4字节 最大值2^32-1
        // arrayMaxSize();
    }

    public static void arrayMaxSize(){
        // Negative 负的
        // Exception in thread "main" java.lang.NegativeArraySizeException
        String[] arr2 = new String[Integer.MAX_VALUE+1];
        // String[] arr1 = new String[Integer.MIN_VALUE];
    }



}
