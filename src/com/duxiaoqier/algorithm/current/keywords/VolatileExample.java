package com.duxiaoqier.algorithm.current.keywords;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileExample {
    private static Integer integerCounter = 0;
    private static volatile Integer integerCounter2 = 0;
    private static volatile int intCounter = 0;
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    private static Object lock = new Object();

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++){
//                        synchronized (VolatileExample.class){
//                            intCounter++;
//                            integerCounter++;
//                        }
                        synchronized (lock) {
                            integerCounter++;
                        }
                        synchronized (integerCounter2) {
                            integerCounter2++;
                        }
                        intCounter++;
                        atomicInteger.incrementAndGet();
                    }
                }
            });
            thread.start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("integer counter:" + integerCounter);
        System.out.println("integer counter2:" + integerCounter2);
        System.out.println("int counter:" + intCounter);
        System.out.println("atomic counter:" + atomicInteger.intValue());
    }
}
