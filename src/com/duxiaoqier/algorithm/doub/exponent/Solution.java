package com.duxiaoqier.algorithm.doub.exponent;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().Power3(2, 3));
    }

    public double Power(double base, int exponent) {
        if (exponent < 0) return (double) 1 / Power(base, -exponent);
        double temp = base;
        base = 1;
        String binaryString = Integer.toBinaryString(exponent);
        for (int i = 0; i < binaryString.length(); i++) {
            if (binaryString.charAt(binaryString.length() - i - 1) == '1') {
                base = base * temp;
            }
            temp = temp * temp;
        }

        return base;
    }

    public double Power2(double base, int exponent){
        if (exponent < 0) return (double) 1 / Power2(base, -exponent);
        double temp = base;
        base = 1;
        for (int i=0; i<exponent; i++){
            base = base * temp;
        }
        return base;
    }

    public double Power3(double base, int exponent) {
        if (exponent < 0) return (double) 1 / Power3(base, -exponent);
        double temp = base;
        base = 1;

        while (exponent !=0){
            if ((exponent & 1) != 0) {
                base = base * temp;
            }
            temp = temp * temp;
            exponent >>= 1;
        }

        return base;
    }
}