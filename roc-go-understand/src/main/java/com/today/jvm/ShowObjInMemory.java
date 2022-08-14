//package com.today.roc.go.understand.jvm;
//
//import org.openjdk.jol.info.ClassLayout;
//
///**
// * ^---^---^---^---^---^---^---^
// * --v---v---v---v---v---v---v--
// *
// * @author zou.cp
// * @version 1.0
// * @Description
// * @createTime 2021年03月10日 00:05*
// * log.info()
// */
//public class ShowObjInMemory {
//    public static void main(String[] args) {
//        Object o = new Object();
//        Object[] objects = new Object[2];
//        String s = ClassLayout.parseInstance(o).toPrintable();
//        System.out.println(s);
//
//        String s1 = ClassLayout.parseInstance(objects).toPrintable();
//        System.out.println(s1);
//
//    }
//}
