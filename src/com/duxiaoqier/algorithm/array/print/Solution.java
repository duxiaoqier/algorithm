package com.duxiaoqier.algorithm.array.print;

import java.util.ArrayList;

public class Solution {

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20}, {21, 22, 23, 24, 25}};
        int[][] matrix2 = {{1, 2}, {3, 4}};
        int[][] matrix3 = {{1}, {2}, {3}, {4}, {5}};
        int[][] matrix4 = {{1, 2, 3, 4, 5}};
        System.out.println(new Solution().printMatrix(matrix));
        System.out.println(new Solution().printMatrix2(matrix));
    }

    public ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> list = new ArrayList<>();
        int high = matrix.length;
        int width = matrix[0].length;

        for (int i = 0; i < (high + 1) / 2 && i < (width + 1) / 2; i++) {
            // 从左到右
            for (int j = i; j < width - i; j++) {
                list.add(matrix[i][j]);
            }
            // 从上到下
            for (int j = i + 1; j < high - i; j++) {
                list.add(matrix[j][width - i - 1]);
            }
            // 从右向左
            for (int j = width - i - 2; j >= i && high - i - 1 > i; j--) {
                list.add(matrix[high - i - 1][j]);
            }
            // 从下向上
            for (int j = high - i - 2; j > i && width - i - 1 > i; j--) {
                list.add(matrix[j][i]);
            }
        }
        return list;
    }

    public ArrayList<Integer> printMatrix2(int[][] matrix) {
        ArrayList<Integer> list = new ArrayList<>();
        int row = matrix.length;
        int col = matrix[0].length;

        int top = 0, left = 0, right = col - 1, down = row - 1;
        while (top <= down && left <= right) {
            for (int i = left; i <= right; i++) {
                list.add(matrix[top][i]);
            }
            for (int i = top + 1; i <= down; i++) {
                list.add(matrix[i][right]);
            }
            if (top != down) {
                for (int i = right - 1; i >= left; i--) {
                    list.add(matrix[down][i]);
                }
            }
            if (right != left) {
                for (int i = down - 1; i > top; i--) {
                    list.add(matrix[i][left]);
                }
            }

            top++;
            left++;
            right--;
            down--;
        }

        return list;
    }
}