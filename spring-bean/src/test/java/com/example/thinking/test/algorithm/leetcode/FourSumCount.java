package com.example.thinking.test.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 454. 四数相加 II
 * https://leetcode-cn.com/problems/4sum-ii/
 * 题目解答
 * https://leetcode-cn.com/problems/4sum-ii/solution/si-shu-xiang-jia-ii-by-leetcode-solution/
 */
public class FourSumCount {

    public static void main(String[] args) {
        int[] A = {1, 1};
        int[] B = {-2, -2};
        int[] C = {-1, 1};
        int[] D = {0, 2};

        int arr = fourSumCount(A, B, C, D);
        System.out.println(arr);
    }

    public static int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                int sumAB = A[i] + B[j];
                Integer num = map.getOrDefault(sumAB, 0);
                map.put(sumAB, num + 1);
            }
        }

        int res = 0;
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < D.length; j++) {
                int sumCD = -(C[i] + D[j]);
                if (map.containsKey(sumCD)) {
                    res += map.get(sumCD);
                }
            }
        }

        return res;
    }
}
