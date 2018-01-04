package com.duxiaoqier.algorithm.reorder.array;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        int[] array = {1,2,3,4,5,6,7,8,9};
        new Solution().reOrderArray(array);
        System.out.println(Arrays.toString(array));
    }
    public void reOrderArray(int [] array) {
        int index = 0;
        for (int i=0; i< array.length; i++){
            if ((array[i]  & 1) == 1){
                int temp = array[i];
                array[i] = array[index];
                array[index] = temp;
                index = i;
            }
        }
    }
}