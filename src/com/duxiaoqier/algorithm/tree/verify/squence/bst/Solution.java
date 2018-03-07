package com.duxiaoqier.algorithm.tree.verify.squence.bst;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        int[] arr = { 4, 3, 7, 9, 8, 1, 5};
//        int[] arr = {4, 1};
        new Solution().VerifySquenceOfBST(null);
        System.out.println(new Solution().VerifySquenceOfBST(arr, 0, arr.length - 1));
    }

    public boolean VerifySquenceOfBST(int[] sequence, int start, int end) {
        if (start >= end) return true;
        int i = start;
        for (; i < end; i++) {
            if (sequence[i] > sequence[end]) break;
        }

        for (int j = i; j < end; j++) {
            if (sequence[j] < sequence[end]) return false;
        }
        boolean left = VerifySquenceOfBST(sequence, start, i - 1);
        boolean right = VerifySquenceOfBST(sequence, i, end - 1);

        return  left && right;
    }

    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence == null) return false;

        int length = sequence.length;
        if (length == 0) return false;
        if (length <= 2) return true;
        int index = length;
        for (int i = 0; i < length - 1; i++) {
            if (sequence[i] > sequence[length - 1]) {
                index = i;
                break;
            }
        }

        for (int i = index + 1; i < length - 1; i++) {
            if (sequence[i] < sequence[length - 1]) {
                return false;
            }
        }

        if (index != 0 && index != length) {
            int[] left = Arrays.copyOfRange(sequence, 0, index);
            if (!VerifySquenceOfBST(left)) return false;
        }

        boolean rightResult = true;
        if (index != length) {
            int[] right = Arrays.copyOfRange(sequence, index, length - 1);
            rightResult = VerifySquenceOfBST(right);
        }

        return rightResult;
    }
}