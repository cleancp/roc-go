package com.today.roc.go.understand.thread.AQS;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年04月06日 13:41*
 * log.info()
 */
public class TestCopyOnWriteArrayList {

    public static void main(String[] args) {
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.add(1);
        copyOnWriteArrayList.add(2);
        copyOnWriteArrayList.add(3);

    }
}
