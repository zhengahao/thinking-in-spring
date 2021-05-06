package com.example.thinking.test.algorithm.leetcode;

import java.util.Arrays;

/**
 * 动画演示 283.移动零
 *
 * https://leetcode-cn.com/problems/move-zeroes/solution/dong-hua-yan-shi-283yi-dong-ling-by-wang_ni_ma/
 */
public class MoveZero {

    public static void main(String[] args) {
        int[] nums = {2, 1, 0, 3, 12};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));

    }

    public static void moveZeroes(int[] nums) {
        if (nums == null) {
            return;
        }
        //两个指针i和j
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            //当前元素!=0，就把其交换到左边，等于0的交换到右边
            if (nums[i] != 0) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j++] = tmp;
            }
        }
    }

}
