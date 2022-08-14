package com.today.thread.阻塞队列;

import ch.qos.logback.core.util.TimeUtil;
import lombok.Data;

import java.util.concurrent.*;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月22日 18:02*
 * log.info()
 */
public class DelayQueueTest {

    public static void main(String[] args) throws InterruptedException {
        DelayQueue queue = new DelayQueue<>();
        for (int i = 1 ;i<6;i++){
            queue.add(new DelayImpl<String>(i,"数据"+i,TimeUnit.SECONDS));
        }
        while(true){
            if (queue.isEmpty()){
                break;
            }
            Delayed poll = queue.take();
            System.out.println(poll);
        }
    }
    //a.The reason why they didn't used it, is mainly because of the complexity in implementation especially iterators and trade off between complexity and performance gain was not that lucrative.
    //b.It may be that Doug Lea didn't feel that Java needed to support 2 different BlockingQueues that differed only in their allocation scheme.
    //because the java.util.concurrent.ArrayBlockingQueue uses a much simpler data structure to hold the queue items. The ArrayBlockingQueue stores its data in one private final E[] items; array. For multiple threads to deal with this same storage space, either if adding or dequeuing, they have to use the same lock. This is not only because of memory barrier but because of mutex protection since they are modifying the same array. LinkedBlockingQueue on the other hand is a linked list of queue elements that is completely different and allows for the ability to have a dual lock. It is the internal storage of the elements in the queue that enabled the different lock configurations.

    @Data
    static class DelayImpl<T> implements Delayed{

        private long time ; //剩余过期时间
        private  TimeUnit timeUnit;
        private T data;

        public DelayImpl(long time, T data,TimeUnit unit) {
            this.time = TimeUnit.NANOSECONDS.convert(time,unit)+System.nanoTime();
            this.data = data;
        }
        //剩余时间
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(time-System.nanoTime(),TimeUnit.NANOSECONDS);
        }
        @Override
        public int compareTo(Delayed o) {
            long d = getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
            return d==0?0:d>0?1:-1;
        }

        @Override
        public String toString(){
            System.out.println(data.toString() +"时间 "+TimeUnit.SECONDS.convert(time,TimeUnit.NANOSECONDS));
            return data.toString();
        }
    }
}
