package com.example.thinking.test.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 350. 两个数组的交集 II
 * <p>
 * 题解
 * <p>
 * <p>
 * 方法一：哈希表
 * <p>
 * 由于同一个数字在两个数组中都可能出现多次，因此需要用哈希表存储每个数字出现的次数。对于一个数字，其在交集中出现的次数等于该数字在两个数组中出现次数的最小值。
 * <p>
 * 首先遍历第一个数组，并在哈希表中记录第一个数组中的每个数字以及对应出现的次数，然后遍历第二个数组，对于第二个数组中的每个数字，如果在哈希表中存在这个数字，则将该数字添加到答案，并减少哈希表中该数字出现的次数。
 * <p>
 * 为了降低空间复杂度，首先遍历较短的数组并在哈希表中记录每个数字以及对应出现的次数，然后遍历较长的数组得到交集。
 * <p>
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/solution/liang-ge-shu-zu-de-jiao-ji-ii-by-leetcode-solution/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class Intersect {

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 1, 0, 999, 4, 5, 6, 6, 6, 6, 6, 6, 6, 9, 0};
        int[] nums2 = {3, 8, 61, 7, 6, 8, 94, 3, 999};
        Intersect ele = new Intersect();
        int[] result = ele.intersect(nums1, nums2);

        System.out.println(Arrays.toString(nums1));
        System.out.println(Arrays.toString(nums2));
        System.out.println("result: " + Arrays.toString(result));
    }


    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            int val = map.getOrDefault(nums1[i], 0);
            map.put(nums1[i], val + 1);
        }

        int[] intersection = new int[nums1.length];
        int index = 0;
        for (int j = 0; j < nums2.length; j++) {
            int val = map.getOrDefault(nums2[j], 0);
            if (val > 0) {
                intersection[index++] = nums2[j];
                val--;
                if (val > 0) {
                    map.put(nums2[j], val);
                } else {
                    map.remove(nums2[j]);
                }
            }
        }

        return Arrays.copyOfRange(intersection, 0, index);
    }


}
