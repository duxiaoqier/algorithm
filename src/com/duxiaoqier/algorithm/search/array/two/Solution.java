package com.duxiaoqier.algorithm.search.array.two;

public class Solution {
    public boolean Find(int target, int [][] array) {
        int high = array.length;
        int width = array[high].length;

        if (array[0][0] > target || array[high - 1][width - 1] < target){
            return false;
        }

        for (int i = 0; i < width; i++){
            for (int j = 0; j < high; j ++){
                if (array[j][i] < target){

                }
            }
        }

        return false;
    }
}