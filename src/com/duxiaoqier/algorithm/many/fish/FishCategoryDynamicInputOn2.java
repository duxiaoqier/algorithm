package com.duxiaoqier.algorithm.many.fish;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 此方法表示鱼动态加入的，和题意不是很相符
 */
public class FishCategoryDynamicInputOn2 {

    public static void main(String[] args) {
        //接受输入参数
        Scanner scanner = new Scanner(System.in);
        int minSize = scanner.nextInt(); //新放入鱼范围的最小值
        int maxSize = scanner.nextInt();//新放入鱼范围的最大值
        int inNumber = scanner.nextInt();//鱼缸里面已经有鱼的数量
        List<Integer> fishSizeList = new ArrayList<>();
        for (int i = 0; i < inNumber; i++) {
            fishSizeList.add(scanner.nextInt());//初始化已经有的鱼的大小inFishSize[i]
        }

        int sum = 0;
        for (int i = minSize; i <= maxSize; i++) {
            boolean canSurvive = true;
            for (int j : fishSizeList) {
                if (i < j && (i * 2 <= j && i * 10 >= j)) {
                    canSurvive = false;
                    break;
                }
                if (i > j && (j * 2 <= i && j * 10 >= i)) {
                    canSurvive = false;
                    break;
                }
            }
            if (canSurvive) {
                sum++;
                fishSizeList.add(i);
            }
        }
        System.out.println(fishSizeList);
        System.out.println(sum);
    }
}
