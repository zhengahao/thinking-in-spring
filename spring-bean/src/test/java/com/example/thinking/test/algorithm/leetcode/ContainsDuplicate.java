package com.example.thinking.test.algorithm.leetcode;

import java.util.Arrays;

/**
 * 217. 存在重复元素
 *
 * <p>
 * 给定一个整数数组，判断是否存在重复元素。
 * <p>
 * 如果存在一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
 *
 * 题解一
 *
 * https://leetcode-cn.com/problems/contains-duplicate/solution/chao-xiang-xi-kuai-lai-miao-dong-ru-he-p-sf6e/
 *
 */
public class ContainsDuplicate {

    public static boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {10, 1, 3, 4, 5, 6, 7, 1};
        System.out.println(containsDuplicate(nums));
    }
}
