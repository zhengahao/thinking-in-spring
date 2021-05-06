package com.example.thinking.test.algorithm.leetcode;

import java.util.*;

/**
 * 解法二：最小堆
 * 题解：
 * https://leetcode-cn.com/problems/top-k-frequent-elements/solution/leetcode-di-347-hao-wen-ti-qian-k-ge-gao-pin-yuan-/
 */
public class TopKFrequent {


    /**
     * 基于桶排序求解「前 K 个高频元素」
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent2(int[] nums, int k) {
        // 使用字典，统计每个元素出现的次数，元素为键，元素出现的次数为值
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int count = map.getOrDefault(num, 0);
            map.put(num, count + 1);
        }

        // 构建桶
        List<Integer>[] list = new ArrayList[nums.length + 1];
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int num = entry.getKey();
            int count = entry.getValue();
            // 获取出现的次数作为下标
            if (list[count] == null) {
                list[count] = new ArrayList();
            }
            list[count].add(num);
        }

        List<Integer> res = new ArrayList();
        for (int i = list.length - 1; i >= 0 && res.size() < k; i--) {
            if (list[i] != null) {
                res.addAll(list[i]);
            }
        }

        int[] resArr = new int[res.size()];
        int index = 0;
        for (Integer t : res) {
            resArr[index] = t;
            index++;
        }
        return resArr;
    }


    /**
     * 解法二：最小堆
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        // 使用字典，统计每个元素出现的次数，元素为键，元素出现的次数为值
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int count = map.getOrDefault(num, 0);
            map.put(num, count + 1);
        }

        // 遍历map，用最小堆保存频率最大的K个元素
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return map.get(a) - map.get(b);
            }
        });

        for (Integer key : map.keySet()) {
            if (priorityQueue.size() < k) {
                priorityQueue.add(key);
            } else if (map.get(key) > map.get(priorityQueue.peek())) {
                priorityQueue.remove();
                priorityQueue.add(key);
            }
        }

        int[] arr = new int[priorityQueue.size()];
        int count = 0;
        while (!priorityQueue.isEmpty()) {
            arr[count++] = priorityQueue.remove();
        }

        return arr;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};

        TopKFrequent frequent = new TopKFrequent();
        int[] result = frequent.topKFrequent(nums, 2);
        System.out.println(Arrays.toString(result));

        int[] result2 = frequent.topKFrequent2(nums, 2);
        System.out.println(Arrays.toString(result2));
    }
}
