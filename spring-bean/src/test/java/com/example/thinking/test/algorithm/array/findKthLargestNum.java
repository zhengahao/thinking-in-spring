package com.example.thinking.test.algorithm.array;

import java.util.Arrays;

public class findKthLargestNum {

    public static void main(String[] args) {

        findKthLargest(new int[]{3, 2, 1, 5, 6, 4, 9, 9, 3, 0, 11, 22, 5, 5, 5, 5, 3, 6, 77, 816, 0}, 2);
    }

    public static int findKthLargest(int[] nums, int k) {
        if (nums.length == 0 || k > nums.length) {
            return 0;
        }
        Arrays.sort(nums);

        System.out.println(Arrays.toString(nums));
        int findKthLargestNum = nums[nums.length - k];
        System.out.println(findKthLargestNum);

        return findKthLargestNum;
    }

}
