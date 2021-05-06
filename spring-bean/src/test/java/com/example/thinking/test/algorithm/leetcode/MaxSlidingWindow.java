package com.example.thinking.test.algorithm.leetcode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 239.滑动窗口最大值
 * <p>
 * 题解一：
 * 单调队列 239.滑动窗口最大值
 * <p>
 * 至此，该题目的求解思路就清晰了，具体如下：
 * <p>
 * 遍历给定数组中的元素，如果队列不为空且当前考察元素大于等于队尾元素，则将队尾元素移除。直到，队列为空或当前考察元素小于新的队尾元素；
 * 当队首元素的下标小于滑动窗口左侧边界left时，表示队首元素已经不再滑动窗口内，因此将其从队首移除。
 * 由于数组下标从0开始，因此当窗口右边界right+1大于等于窗口大小k时，意味着窗口形成。此时，队首元素就是该窗口内的最大值。
 * <p>
 * 作者：hardcore-aryabhata
 * 链接：https://leetcode-cn.com/problems/sliding-window-maximum/solution/dong-hua-yan-shi-dan-diao-dui-lie-239hua-hc5u/
 * 来源：力扣（LeetCode）
 * <p>
 * 题解二：
 * 双向队列解决滑动窗口最大值
 * 链接：https://leetcode-cn.com/problems/sliding-window-maximum/solution/shuang-xiang-dui-lie-jie-jue-hua-dong-chuang-kou-2/
 */
public class MaxSlidingWindow {


    public static void main(String[] args) {
        int[] nums = {7, 2, 4};
        int[] nums2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 8, 7, 6, 5, 3, 2, 1};

        int[] arr = maxSlidingWindow(nums, 2);

        System.out.println(Arrays.toString(arr));

    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        //  结果数组，窗口个数 len - k + 1
        int[] res = new int[len - k + 1];
        LinkedList<Integer> queue = new LinkedList<>();

        for (int i = 0; i < len; i++) {
            // 如果队列不为空，且当前元素nums[i]（考察元素）大于等于队尾元素
            // 直到，队列为空或当前考察元素小于新的队尾元素
            while (!queue.isEmpty() && nums[i] >= nums[queue.getLast()]) {
                queue.removeLast();
            }
            // 队列为空或者当前nums[i] <= （小于等于）新的队尾元素
            // 存储元素下标
            queue.addLast(i);

            // 计算窗口左侧边界
            int left = i + 1 - k;

            // 当队首元素的下标小于滑动窗口左侧边界left时
            // 表示队首元素已经不再滑动窗口内，因此将其从队首移除
            if (queue.getFirst() < left) {
                queue.removeFirst();
            }

            // 由于数组下标从0开始，因此当窗口右边界right+1大于等于窗口大小k时
            // 意味着窗口形成。此时，队首元素就是该窗口内的最大值
            if (i + 1 >= k) {
                res[left] = nums[queue.peekFirst()];
            }
        }

        return res;
    }


}
