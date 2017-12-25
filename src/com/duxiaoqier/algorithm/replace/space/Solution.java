package com.duxiaoqier.algorithm.replace.space;

public class Solution {
    public static void main(String[] args) {
//        System.out.println(new Solution().replaceSpace(new StringBuffer("we are happy")));
        System.out.println(new Solution().replaceSpace(new StringBuffer("  ")));
    }

    public String replaceSpace(StringBuffer str) {
        for (int i=str.length()-1; i >=0; i--){
            if (str.charAt(i) == ' '){
                str.setCharAt(i, '%');
                str.insert(i + 1, "20");
            }
        }
        return str.toString();
    }
}
