package com.duxiaoqier.algorithm.product.consume;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreBlockingQueue<T> {
    private final int containerSize = 10;
    private Queue<T> container = new LinkedList<>();

    private Semaphore put = new Semaphore(containerSize);
    private Semaphore get = new Semaphore(0);
    private Semaphore mutex = new Semaphore(1);

    public void put(T element) throws InterruptedException {
        try {
            put.acquire();
            mutex.acquire();

            System.out.println("get lock success to put, thread:" + Thread.currentThread());
            container.add(element);
        } finally {
            System.out.println("away from put, thread:" + Thread.currentThread());
            mutex.release();
            get.release();
        }
    }

    public T take() throws InterruptedException {
        try{
            get.acquire();
            mutex.acquire();
            System.out.println("get lock success to take, thread:" + Thread.currentThread());
            return container.poll();
        } finally {
            System.out.println("away from get, thread:" + Thread.currentThread());
            mutex.release();
            put.release();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SemaphoreBlockingQueue<Integer> conditionBlockingQueue = new SemaphoreBlockingQueue<>();
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
