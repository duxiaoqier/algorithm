package com.duxiaoqier.algorithm.reorder.array;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        int[] array = {12, 2, 3, 4, 5, 6, 7, 8, 9};
        new Solution().reOrderArray(array);
        System.out.println(Arrays.toString(array));
        new Solution().reOrderArray2(array);
        System.out.println(Arrays.toString(array));
    }

    public void reOrderArray(int[] array) {
        // 标记找到的最后一个奇数后一个位置
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            int current = array[i];

            // 如果找到了奇数，就放到标记的位置去
            // 并把中间部分出现的偶数，依次后移一个位置
            if ((current & 1) != 0) {
                for (int j = i; j > index; j--) {
                    array[j] = array[j - 1];
                }
                array[index] = current;
                index++;
            }
        }
    }

    public void reOrderArray2(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if ((array[j] & 1) == 0 && (array[j + 1] & 1) != 0) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}