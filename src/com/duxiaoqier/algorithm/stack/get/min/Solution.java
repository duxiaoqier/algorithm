package com.duxiaoqier.algorithm.stack.get.min;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Stack;

public class Solution {

    private Stack<Integer> stack = new Stack<>();
    private LinkedList<Integer> integers = new LinkedList<>();

    public void push(int node) {
        stack.push(node);
        integers.add(node);
        integers.sort(Comparator.comparing(p -> p));
    }

    public void pop() {
        integers.remove(stack.pop());
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return integers.getFirst();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.push(2);
        solution.push(3);
        solution.push(1);
        solution.push(5);
        System.out.println(solution.min());
        solution.pop();
        System.out.println(solution.min());
        solution.pop();
        System.out.println(solution.min());
    }
}