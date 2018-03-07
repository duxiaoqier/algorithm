package com.duxiaoqier.algorithm.product.consume;

import java.util.LinkedList;
import java.util.Queue;

public class TraditionalBlockingQueue<T> {
    private final int containerSize = 10;
    private Queue<T> container = new LinkedList<>();

    public void put(T element) throws InterruptedException {
        synchronized (container){
            while (container.size() == containerSize){
                container.wait();
            }
            container.add(element);
            container.notifyAll();
        }
    }

    public T take() throws InterruptedException {
        synchronized (container) {
            while (container.isEmpty()){
                container.wait();
            }
            T result = container.poll();
            container.notifyAll();
            return result;
        }
    }
}
