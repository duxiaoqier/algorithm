package com.duxiaoqier.algorithm.current.id.generator;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class Solution3 {
    public static void main(String[] args) {
        final int threadNum = 50000;
        final Map<String, Integer> count = new HashMap<>();
        final CountDownLatch endLatch = new CountDownLatch(threadNum);
        Runnable task = () -> {
            Integer oldValue, newValue;
            for (int i = 0; i < 500; i++) {
                while (true) {
                    oldValue = count.get("a");
                    if (null == oldValue) {
                        newValue = 1;
                        if (count.putIfAbsent("a", newValue) == null) {
                            break;
                        }
                    } else {
                        newValue = oldValue + 1;
                        if (count.replace("a", oldValue, newValue)) {
                            break;
                        }
                    }
                }
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
