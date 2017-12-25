package com.duxiaoqier.algorithm.jump.floor;

public class Solution {
    public static void main(String[] args) {
//        System.out.println(new Solution().calculateCategory(7, 3));
        System.out.println(new Solution().JumpFloor(4));
    }
    public int JumpFloor(int target) {
        int jumpTwo = target/2;
        int sumJumpCategory = 0;
        for (int i=0; i<=jumpTwo; i++){
            int jumpOne = target - i*2;
            int sumJump = jumpOne + i;
            int smallJump = jumpOne > i? i: jumpOne;
            sumJumpCategory += calculateCategory(sumJump, smallJump);
        }
        return sumJumpCategory;
    }

    private int calculateCategory(int sumJump, int smallJump) {
        int large = 1;
        int small = 1;
        for (int i=1; i<=smallJump; i++){
            large *= sumJump - i + 1;
            small *= i;
        }
        return large/small;
    }
}