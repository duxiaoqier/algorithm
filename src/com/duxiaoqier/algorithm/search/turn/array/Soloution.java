package com.duxiaoqier.algorithm.search.turn.array;

public class Soloution {
    public static void main(String[] args) {
        int[] array = {3, 4, 5, 1, 2};
        System.out.println(new Soloution().minNumberInRotateArray(array));
    }

    public int minNumberInRotateArray(int[] array) {
        int length = array.length;
        if (length == 0) return 0;
        for (int i=0; i<length-1; i++){
            if (array[i+1] < array[i]){
                return array[i + 1];
            }
        }

        return array[0];
    }
}
