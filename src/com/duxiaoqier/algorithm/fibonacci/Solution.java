package com.duxiaoqier.algorithm.fibonacci;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().Fibonacci(3));
    }
    public int Fibonacci(int n) {
        int previousNum = 1;
        int currentNum = 1;
        for (int i=2; i<n; i++){
            int temp = previousNum + currentNum;
            previousNum = currentNum;
            currentNum = temp;
        }
        return currentNum;
    }
}