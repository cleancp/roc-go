package com.today.roc.go.understand.类加载;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年04月21日 22:48*
 * log.info()
 */
public class ClassTest {
    public static void main(String[] args) {
        SingleTon singleTon = SingleTon.getInstance();
        System.out.println("count1=" + singleTon.count1);
        //1
        System.out.println("count2=" + singleTon.count2);
        //1
        System.out.println("count3=" + singleTon.count3);
        //1
    }
}

class SingleTon {

    private static SingleTon singleTon = new SingleTon();

    public static int count1;
    public static int count2 = 0;

    public SingleTon() {
        count3 = 1;
        count1++;
        count2++;
    }

    {
        count1 += 1;
    }

    public int count3 = count1 + count2;

    {
        count3 += 3;
    }

    public static SingleTon getInstance() {
        return singleTon;
    }
}


