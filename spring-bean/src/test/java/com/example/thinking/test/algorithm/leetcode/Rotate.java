package com.example.thinking.test.algorithm.leetcode;

import java.util.Arrays;

/**
 * 189. 旋转数组
 * <p>
 * 题解：
 * https://leetcode-cn.com/problems/rotate-array/solution/javadai-ma-3chong-fang-shi-tu-wen-xiang-q8lz9/
 */
public class Rotate {

    /**
     * 1，使用临时数组
     *
     * @param nums 数组
     * @param k    反转次数
     */
    public void rotate1(int[] nums, int k) {
        int len = nums.length;
        int[] newArr = new int[len];
        for (int i = 0; i < newArr.length; i++) {
            newArr[(i + k) % len] = nums[i];
            System.out.println((i + k) % len);
        }
        System.arraycopy(newArr, 0, nums, 0, len);
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
        Rotate rotate = new Rotate();
        rotate.rotate2(nums, 9);

        System.out.println(Arrays.toString(nums));
    }

    /**
     * 2，多次反转
     * 先反转全部数组，在反转前k个，最后在反转剩余的，如下所示
     *
     * @param nums
     * @param k
     */
    public void rotate2(int[] nums, int k) {
        k %= nums.length;
        System.out.println("k: " + k);

        // 先反转全部的元素
        reverse(nums, 0, nums.length - 1);
        // 反转前K个元素
        reverse(nums, 0, k - 1);
        // 反转剩余元素
        reverse(nums, k, nums.length - 1);

    }

    /**
     * 把数组中从[start，end]之间的元素两两交换,也就是反转
     *
     * @param nums
     * @param start
     * @param end
     */
    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }

}
