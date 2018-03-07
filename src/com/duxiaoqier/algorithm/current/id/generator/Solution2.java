package com.duxiaoqier.algorithm.current.id.generator;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Solution2 {
    public static void main(String[] args) {
        final int threadNum = 500000;
        final Map<String, AtomicInteger> count = new ConcurrentHashMap<>();
        final CountDownLatch endLatch = new CountDownLatch(threadNum);
        Runnable task = () -> {
            AtomicInteger oldValue;
            for (int i = 0; i < 5; i++) {
                oldValue = count.get("a");
                if (null == oldValue) {
                    AtomicInteger zeroValue = new AtomicInteger(0);
                    oldValue = count.putIfAbsent("a", zeroValue);
                    if (null == oldValue) {
                        oldValue = zeroValue;
                    }
                }
                oldValue.incrementAndGet();
            }
            endLatch.countDown();
        };
        for (int i=0; i< threadNum; i++){
            new Thread(task).start();
        }

        try {
            endLatch.await();
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
