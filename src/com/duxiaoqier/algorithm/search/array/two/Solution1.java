package com.duxiaoqier.algorithm.search.array.two;

/**
 * https://www.nowcoder.com/practice/abc3fe2ce8e146608e868a70efebf62e?tpId=13&tqId=11154&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 运行时间：199ms

 占用内存：17112k
 */
public class Solution1 {

    public static void main(String[] args) {
        int arr[][] = {{2,4,5,8,9,12,15,18,19,21},
                {5,7,8,10,13,16,18,20,21,24},
                {7,9,11,12,14,19,22,24,25,28},
                {8,10,14,17,19,22,23,27,30,32},
                {10,12,16,20,22,25,27,30,32,35},
                {11,13,17,23,25,28,31,32,35,38}};
        System.out.println(new Solution1().Find(5, arr));
    }

    public boolean Find(int target, int[][] array) {
        int high = array.length;
        if (high < 1) return false;
        int width = array[high - 1].length;
        if (width < 1) return false;

        if (array[0][0] > target || array[high - 1][width - 1] < target) {
            return false;
        }
//        if (array[0][0] == target) return true;

        int index = 0;
        while (array[index][index] <= target && index < high && index < width) {
            if (high - index < 2 || width - index < 2) {
                for (int i = index; i < high; i++) {
                    for (int j = index; j < width; j++) {
                        if (array[i][j] == target) {
                            return true;
                        }
                    }
                }
            } else {
                if (array[index][index] == target) {
                    return true;
                }

                for (int[] anArray : array) {
                    if (anArray[index] == target) {
                        return true;
                    }
                }
                for (int j = index; j < width; j++) {
                    if (array[index][j] == target) {
                        return true;
                    }
                }
            }
            index++;
        }

        return false;
    }

}