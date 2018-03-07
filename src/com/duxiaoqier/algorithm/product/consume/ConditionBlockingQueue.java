package com.duxiaoqier.algorithm.product.consume;

import java.util.LinkedList;
import java.util.Queue;
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
        try {
            while (container.size() == containerSize) {
                notFull.await();
            }
            container.add(element);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    private T take() throws InterruptedException {
        lock.lock();
        try {
            while (container.isEmpty()) {
                notEmpty.await();
            }
            T result = container.poll();
            notFull.signal();
            return result;
        } finally {
            lock.unlock();
        }
    }
}
