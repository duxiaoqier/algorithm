package com.duxiaoqier.algorithm.stack.to.list;

import java.util.Stack;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.push(1);
        solution.push(2);
        solution.push(3);
        System.out.println(solution.pop());
        System.out.println(solution.pop());
        System.out.println(solution.pop());
        System.out.println(solution.pop());
    }
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    
    public void push(int node) {
        while (!stack1.empty()){
            stack2.push(stack1.pop());
        }
        stack2.push(node);
    }
    
    public int pop() {
        while (!stack2.empty()) {
            stack1.push(stack2.pop());
        }
        return stack1.pop();
    }
}