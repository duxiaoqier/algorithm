package com.duxiaoqier.algorithm.product.consume;

import java.util.LinkedList;
import java.util.Queue;

public class TraditionalBlockingQueue<T> {
    private final int containerSize = 10;
    private Queue<T> container = new LinkedList<>();

    private final Object lock = new Object();

    public void put(T element) throws InterruptedException {
        synchronized (lock){
            while (container.size() == containerSize){
                container.wait();
            }
            container.add(element);
            container.notifyAll();
        }
    }

    public T take() throws InterruptedException {
        synchronized (lock) {
            while (container.isEmpty()){
                container.wait();
            }
            T result = container.poll();
            container.notifyAll();
            return result;
        }
    }
}
