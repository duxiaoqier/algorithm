package com.duxiaoqier.algorithm.product.consume;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionBlockingQueue<T> {
    private final int containerSize = 10;
    private Queue<T> container = new LinkedList<>();

    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    private void put(T element) throws InterruptedException {
        lock.lock();
        System.out.println("get lock success to put, thread:" + Thread.currentThread());
        try {
            while (container.size() == containerSize) {
                notFull.await();
            }

            System.out.println("away from await to put, thread:" + Thread.currentThread());
            container.add(element);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    private T take() throws InterruptedException {
        lock.lock();
        System.out.println("get lock success to take, thread:" + Thread.currentThread());
        try {
            while (container.isEmpty()) {
                notEmpty.await();
            }

            System.out.println("away from await to take, thread:" + Thread.currentThread());
            T result = container.poll();
            notFull.signal();
            return result;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConditionBlockingQueue<Integer> conditionBlockingQueue = new ConditionBlockingQueue<>();
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i=0; i<5; i++){
            service.submit(()->{
                try {
                    System.out.println(conditionBlockingQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            Thread.sleep(1000);
        }

        for (int i =0; i< 5; i++) {
            service.submit(()->{
                try {conditionBlockingQueue.put(new Random().nextInt());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            Thread.sleep(1000);
        }

        service.shutdown();

    }
}
