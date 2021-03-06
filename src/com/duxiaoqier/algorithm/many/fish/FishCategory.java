package com.duxiaoqier.algorithm.many.fish;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FishCategory {
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
            }
        }
        System.out.println(sum);
    }
}
