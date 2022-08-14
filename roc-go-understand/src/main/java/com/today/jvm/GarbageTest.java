package com.today.jvm;

import java.io.IOException;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年04月17日 23:46*
 * log.info()
 */
public class GarbageTest {
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
// TODO Auto-generated method stub
        try {
            gcTest();
        } catch (IOException e) {
// TODO Auto-generated catch blocke.printStackTrace();
        }
        System.out.println("has exited gcTest!");
        System.in.read();
        System.in.read();
        System.out.println("out begin gc!");
        for (int i = 0; i < 100; i++) {
            System.gc();
            System.in.read();
            System.in.read();
        }
    }

    private static void gcTest() throws IOException {
        System.in.read();
        System.in.read();
        Person p1 = new Person();
        System.in.read();
        System.in.read();
        Person p2 = new Person();
        p1.setMate(p2);
        p2.setMate(p1);
        System.out.println("before exit gctest!");
        System.in.read();
        System.in.read();
        System.gc();
        System.out.println("exit gctest!");
    }

    private static class Person {
        byte[] data = new byte[20000000];
        Person mate = null;

        public void setMate(Person other) {
            mate = other;
        }
    }
}
