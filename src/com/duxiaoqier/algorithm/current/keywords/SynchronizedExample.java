package com.duxiaoqier.algorithm.current.keywords;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class SynchronizedExample {
    private static Integer integerCounter = 0;
    private static volatile Integer integerCounter2 = 0;
    private static volatile Integer integerCounter3 = 0;
    private static volatile Integer integerCounter4 = 0;
    private static volatile Integer integerCounter5 = 0;
    private static volatile int intCounter = 0;
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    private static Object lock = new Object();
    private static String stringLock = "";
    private static LockDemo lockDemo = new LockDemo();
    private static LockDemo lockDemo2 = new LockDemo();

    private static final int threadNum = 100;
    private static final CountDownLatch latch = new CountDownLatch(threadNum);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i=0; i<threadNum; i++) {
            service.submit(() -> {
                for (int i1 = 0; i1 < 1000; i1++){
                    synchronized (lock) {
                        integerCounter++;
                    }

                    // synchronized代码块内不能修改Integer类锁的值
                    // ⚠️这里不能锁住integerCounter2，因为integerCounter2的值会发生改变
                    // 另一个线程看到的是一个新的对象，导致同步失效
                    synchronized (integerCounter2) {
                        integerCounter2++;
                    }

                    // synchronized 代码块内不能修改String锁引用
                    synchronized (stringLock) {
                        integerCounter3++;
                        stringLock = "" + integerCounter3;
                    }

                    // synchronized 代码块内可以修改锁属性
                    synchronized (lockDemo) {
                        integerCounter4++;
                        lockDemo.setDemoField(integerCounter4);
                    }

                    // synchronized 代码块内不能修改Object对象引用
                    synchronized (lockDemo2) {
                        integerCounter5++;
                        lockDemo2 = new LockDemo();
                    }

                    intCounter++;
                    atomicInteger.incrementAndGet();
                }

                latch.countDown();
            });
        }

        latch.await();

        System.out.println("integer counter:" + integerCounter);
        System.out.println("integer counter2:" + integerCounter2);
        System.out.println("integer counter3:" + integerCounter3);
        System.out.println("integer counter4:" + integerCounter4);
        System.out.println("integer counter5:" + integerCounter5);
        System.out.println("int counter:" + intCounter);
        System.out.println("atomic counter:" + atomicInteger.intValue());
    }

    static class LockDemo {
        private int demoField;

        public int getDemoField() {
            return demoField;
        }

        public void setDemoField(int demoField) {
            this.demoField = demoField;
        }
    }
}
