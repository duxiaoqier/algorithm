package com.duxiaoqier.algorithm.stack.get.min;

import java.util.Stack;

public class Solution2 {

    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public void push(int node) {
        stack.push(node);
        if (minStack.empty() || node <= minStack.peek()){
            minStack.push(node);
        }
    }

    public void pop() {
        if (stack.pop().equals(minStack.peek())){
            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        solution.push(2);
        solution.push(3);
        solution.push(1);
        solution.push(5);
        System.out.println(solution.min());
        solution.pop();
        System.out.println(solution.min());
        solution.pop();
        System.out.println(solution.min());
        solution.pop();
        solution.pop();
    }
}