package com.duxiaoqier.algorithm.current.id.generator;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Solution {
    private static Map<String, AtomicInteger> count = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        int num = 1000;
        final CountDownLatch latch = new CountDownLatch(num);
        final Solution solution = new Solution();

        for (int i = 0; i < num; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5000; j++) {
                    String string = new Random().nextInt(200) + "string";
                    try{
                        solution.count2(string);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
                latch.countDown();
            }).start();
        }

        latch.await();
        System.out.println(count);
        System.out.println(count.values().stream().mapToInt(AtomicInteger::get).sum());

    }


    private void count(String string) {
        AtomicInteger oldValue;
        if (count.get(string) == null) {
            oldValue = count.putIfAbsent(string, new AtomicInteger(1));
            if (oldValue != null) {
                oldValue.incrementAndGet();
            }
        } else {
            oldValue = count.get(string);
            oldValue.incrementAndGet();
        }
    }

    private void count2(String string){
        AtomicInteger oldValue = count.get(string);
        if (null == oldValue) {
            AtomicInteger zeroValue = new AtomicInteger(0);
            oldValue = count.putIfAbsent(string, zeroValue);
            if (null == oldValue) {
                oldValue = zeroValue;
            }
        }
        oldValue.incrementAndGet();
    }
}
