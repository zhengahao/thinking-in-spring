package com.example.thinking.test.algorithm.leetcode;

import java.util.Arrays;
import java.util.Random;

/**
 * 384. 打乱数组
 * https://leetcode-cn.com/leetbook/read/top-interview-questions/xmchc3/
 * <p>
 * 方法二： Fisher-Yates 洗牌算法 【通过】
 * https://leetcode-cn.com/problems/shuffle-an-array/solution/da-luan-shu-zu-by-leetcode/
 */
public class ShuffleArray {

    /**
     * Your Solution object will be instantiated and called as such:
     * Solution obj = new Solution(nums);
     * int[] param_1 = obj.reset();
     * int[] param_2 = obj.shuffle();
     */

    private int[] array;

    private int[] original;

    Random random = new Random();

    public ShuffleArray(int[] nums) {
        array = nums;
        original = nums.clone();
    }

    /**
     * Resets the array to its original configuration and return it.
     */
    public int[] reset() {
        array = original;
        original = original.clone();
        return array;
    }

    /**
     * Returns a random shuffling of the array.
     */
    public int[] shuffle() {
        for (int i = 0; i < array.length; i++) {
            swap(array, i, randRange(array.length, i));
        }
        return array;
    }

    void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    int randRange(int max, int min) {
        return random.nextInt(max - min) + min;
    }

    public static void main(String[] args) {
        int[] nums = {-6, 10, 184};
        ShuffleArray shuffleArray = new ShuffleArray(nums);

        nums = shuffleArray.reset();
        System.out.println(Arrays.toString(nums));

        nums = shuffleArray.shuffle();
        System.out.println(Arrays.toString(nums));

        nums = shuffleArray.reset();
        System.out.println(Arrays.toString(nums));

        nums = shuffleArray.shuffle();
        System.out.println(Arrays.toString(nums));
    }

}
