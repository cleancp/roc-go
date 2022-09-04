package com.tuling.concurrency.course7_synchrinized;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2022年09月04日 11:00*
 * log.info()
 */
public class MESALearn {

    static final Object obj = new Object();
    public static void main(String[] args) {

        /**
         * 编程范式
         * <pre>
         *     synchronized (obj) {
         *         while (&lt;condition does not hold&gt;)
         *             obj.wait();
         *         ... // Perform action appropriate to condition
         *     }
         * </pre>
         */
        boolean condition = true;
        while (condition){
            try {
                obj.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
