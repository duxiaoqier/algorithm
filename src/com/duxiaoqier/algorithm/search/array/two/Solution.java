package com.duxiaoqier.algorithm.search.array.two;

public class Solution {

    public static void main(String[] args) {
        int arr[][] = {{1,2,8,9},{4,7,10,13}};
        System.out.println(new Solution().Find(7, arr));
    }
    public boolean Find(int target, int [][] array) {
        int high = array.length;
        if (high < 1) return false;
        int width = array[high -1].length;
        if (width < 1) return false;

        if (array[0][0] > target || array[high - 1][width - 1] < target){
            return false;
        }

        if (high < 2 || width < 2){
            for (int i=0; i< high; i++){
                for (int j=0; j< width; j++){
                    if (array[i][j] == target) {
                        return true;
                    }
                }
            }
        } else {
            if (array[1][1] == target){
                return true;
            }
            if (array[1][1] < target){
                return Find(target, subArray(array));
            } else {
                for (int[] anArray : array) {
                    if (anArray[0] == target) {
                        return true;
                    }
                }
                for (int j=0; j< width; j++){
                    if (array[0][j] == target){
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private int[][] subArray(int[][] array) {
        int high = array.length -1;
        int width = array[high].length -1;
        int[][] subArray = new int[high][width];

        for (int i=0; i< high; i++){
            for (int j=0; j< width; j++){
                subArray[i][j] = array[i+1][j+1];
            }
        }

        return subArray;
    }
}