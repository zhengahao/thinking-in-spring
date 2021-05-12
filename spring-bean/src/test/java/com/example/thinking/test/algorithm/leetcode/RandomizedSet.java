package com.example.thinking.test.algorithm.leetcode;

import java.util.*;

/**
 * 380. 常数时间插入、删除和获取随机元素
 * https://leetcode-cn.com/problems/insert-delete-getrandom-o1/
 * <p>
 * 题解
 * https://leetcode-cn.com/problems/insert-delete-getrandom-o1/solution/chang-shu-shi-jian-cha-ru-shan-chu-he-huo-qu-sui-j/
 */
public class RandomizedSet {

    private List<Integer> list;

    private Map<Integer, Integer> map;


    /**
     * Initialize your data structure here.
     */
    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (map.containsKey(val)) return false;

        map.put(val, list.size());
        list.add(list.size(), val);
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        // 把最后的元素移动到删除的位置，然后在删除最后一个元素
        int last = list.get(list.size() - 1);
        list.set(map.get(val), last);
        map.put(last, map.get(val));
        // delete the last element
        list.remove(list.size() - 1);
        map.remove(val);
        return true;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        Random random = new Random();
        int val = list.get(random.nextInt(list.size()));
        return val;
    }

    public static void main(String[] args) {
        // 初始化一个空的集合。
        RandomizedSet randomSet = new RandomizedSet();

        // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
        randomSet.insert(0);

        randomSet.insert(1);

        // 返回 false ，表示集合中不存在 2 。
        randomSet.remove(0);

        // 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
        randomSet.insert(2);

        // getRandom 应随机返回 1 或 2 。
        int result = randomSet.getRandom();
        System.out.println("getRandom: " + result);


    }
}
