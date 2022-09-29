package com.tuling.concurrency.course13_aqs_queue;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * description：
 * 活锁：互相谦让，A拿到资源给B后等待，唤醒B，B拿到资源给A后等待，唤醒A
 *
 * @author：roc.zou 2022/9/29 4:39 下午
 */
@Slf4j
public class LiveLockLearn {

    public static void main(String[] args) throws InterruptedException {
        final Diner husband = new Diner(true, "丈夫");
        final Diner wife = new Diner(true, "妻子");
        final Spoon spoon = new Spoon(wife);
        Thread wifeT = new Thread(() -> {
            wife.eatWith(husband, spoon);
        });
        Thread husbandT = new Thread(() -> {
            husband.eatWith(wife, spoon);
        });
        wifeT.start();
        husbandT.start();
        Thread.sleep(10000);
        wifeT.interrupt();
        husbandT.interrupt();
    }

    /**
     * 定义一个勺子，ower 表示这个勺子的拥有者
     */
    @Data
    static class Spoon {
        Diner owner;

        public Spoon(Diner diner) {
            this.owner = diner;
        }

        public String getOwnerName() {
            return owner.getName();
        }

        public void setOwner(Diner diner) {
            this.owner = diner;
        }

        //表示正在用餐
        public void use() {
            log.info( "{} 用这个勺子吃饭.",owner.getName());
        }
    }

    /**
     * 定义一个晚餐类
     */
    @Data
    static class Diner {

        private boolean isHungry;
        //用餐者的名字
        private String name;

        public Diner(boolean isHungry, String name) {
            this.isHungry = isHungry;
            this.name = name;
        }

        public void eatWith(Diner diner, Spoon sharedSpoon) {
            synchronized (sharedSpoon){
                try{
                    while (isHungry){
                        while (!sharedSpoon.getOwnerName().equals(name)){
                            sharedSpoon.wait();
                        }
                        if (diner.isHungry()){
                            sharedSpoon.setOwner(diner);
                            log.info( "{}：亲爱的我饿了，然后{}把勺子给了{}",
                                    diner.getName(),name,diner.getName());
                            sharedSpoon.use();
                            sharedSpoon.notifyAll();
                        }else {
                            sharedSpoon.use();
                            sharedSpoon.setOwner(diner);
                            isHungry = false;
                        }
                        Thread.sleep(500);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
