package com.duxiaoqier.algorithm.number.of1;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().NumberOf12(7));
    }

    public int NumberOf1(int n) {
        int count = 0;
        if (n < 0) return 32 - NumberOf1(-n - 1);
        while (n > 0) {
            if ((n & 1) == 1) {
                count++;
            }
            n = n >> 1;
        }
        return count;
    }

    public int NumberOf12(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = (n & (n - 1));
        }
        return count;
    }
}