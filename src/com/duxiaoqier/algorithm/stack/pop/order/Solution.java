package com.duxiaoqier.algorithm.stack.pop.order;

import java.util.Stack;

public class Solution {

    public static void main(String[] args) {
//        int[] pushA = {1, 3, 5, 7, 9, 6};
        int[] pushA = {1, 2, 3, 4, 5};
//        int[] popA = {5, 9, 7, 6, 1, 3};
        int[] popA = {4, 5, 3, 2, 1};

//        System.out.println(new Solution().IsPopOrder(pushA, popA));
        System.out.println(new Solution().isPopOrder(pushA, popA));

    }

    public boolean isPopOrder(int[] pushA, int[] popA) {
        if (popA.length == 0 || pushA.length == 0 || popA.length != pushA.length) return false;

        Stack<Integer> stack = new Stack<>();
        int j=0;
        for (int i=0; i< pushA.length; i++){
            stack.push(pushA[i]);
            while (!stack.empty() && stack.peek().equals(popA[j])) {
                stack.pop();
                j++;
            }
        }

        return stack.empty();
    }

    public boolean IsPopOrder(int[] pushA, int[] popA) {
        Stack<Integer> stack = new Stack<>();

        int maxIndex = -1;
        for (int i = 0; i < popA.length; i++) {
            int index = 0;
            boolean find = false;
            for (int j = 0; j < popA.length; j++) {
                if (pushA[j] == popA[i]) {
                    index = j;
                    find = true;
                    break;
                }
            }
            if (!find) {
                return false;
            }

            if (index > maxIndex) {
                for (int k = maxIndex + 1; k < index; k++) {
                    stack.push(pushA[k]);
                }
                maxIndex = index;
            } else {
                if (!stack.pop().equals(popA[i])) {
                    return false;
                }
            }
        }
        return true;
    }
}