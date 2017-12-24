package com.duxiaoqier.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * "<"符号表示删除，获取打印出来的字符串
 */
public class StringWithDelete {
    public static void main(String[] args) {
        String string = "<<sg<sngn<<";
        System.out.println(fun1(string));
        System.out.println(fun2(string));
    }


    private static String fun1(String input) {
        char[] arr = input.toCharArray();
        List<Character> charList = new ArrayList<>();
        for (char c : arr) {
            if (c == '<') {
                if (charList.size() != 0){
                    charList.remove(charList.size() - 1);
                }
            } else {
                charList.add(c);
            }
        }
        return charList.toString();
    }

    private static String fun2(String input) {
        int saveIndex = 0;
        char[] arr = input.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if (c == '<') {
                if (saveIndex != 0){
                    saveIndex--;
                }
            } else {
                arr[saveIndex] = arr[i];
                saveIndex++;
            }
        }

        return new String(arr).substring(0, saveIndex);
    }
}
