package com.example.thinking.test.algorithm.array;

import java.util.PriorityQueue;

public class findKthLargestNum3 {

    public static void main(String[] args) {

        int k = findKthLargest(new int[]{1, 2, 3, 4, 5, 6}, 2);

        System.out.println("第K大的数字为：" + k);
    }

    public static int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        // 使用一个含有 len 个元素的最小堆，默认是最小堆，可以不写 lambda 表达式：(a, b) -> a - b
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(len, (a, b) -> a - b);
        for (int i = 0; i < nums.length; i++) {
            minHeap.add(nums[i]);
        }

        for (int i = 0; i < len - k; i++) {
            minHeap.poll();
        }

        return minHeap.peek();
    }

}
