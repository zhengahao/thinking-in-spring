package com.example.thinking.test.algorithm;

/**
 * @Author：zzh
 * @Description: 寻找缺失的数
 * @Date: 2021/3/10 11:03 上午
 */
public class LookingForMissingNumbers {

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 6, 7, 8};
        //lookingForMissingNumbers(a);
    }

    private static void lookingForMissingNumbers(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if (i != a[i - 1]) {
                System.out.println(i);
                break;
            }
        }
    }




}
