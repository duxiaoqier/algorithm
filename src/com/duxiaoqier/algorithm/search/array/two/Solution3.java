package com.duxiaoqier.algorithm.search.array.two;

/**
 * 复杂度为O(n)，。最简单的算法
 */
public class Solution3 {
    public static void main(String[] args) {
        int arr[][] = {{2,4,5,8,9,12,15,18,19,21},
                       {5,7,8,10,13,16,18,20,21,24},
                       {7,9,11,12,14,19,22,24,25,28},
                       {8,10,14,17,19,22,23,27,30,32},
                       {10,12,16,20,22,25,27,30,32,35},
                       {11,13,17,23,25,28,31,32,35,38}};
        System.out.println(new Solution3().Find(34, arr));
    }

    public boolean Find(int target, int[][] array) {
        int high = array.length;
        if (high < 1) return false;
        int width = array[high - 1].length;
        if (width < 1) return false;

        if (array[0][0] > target || array[high - 1][width - 1] < target) {
            return false;
        }

        int j = 0;
        int i = width - 1;
        while (array[j][i] != target && j<high-1 && i>0){
            if (array[j][i] < target){
                j++;
            } else {
                i--;
            }
            if (array[j][i] == target) return true;
        }

        return false;
    }
}
