package com.example.thinking.test.algorithm.sort;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

public class QuickSort2 {

    private static AtomicLong automicLong = new AtomicLong(0);


    public static void main(String[] args) {
        int[] arr = {5, 8, 9, 6, 9, 4, 3, 2, 1, 89, 3, 28, 40, 28, 499, 61, 84, 999, 0};

        quickSort(arr, 0, arr.length - 1);

        System.out.println(Arrays.toString(arr));
        System.out.println(automicLong.get());

    }

    public static void quickSort(int[] arr, int startIndex, int endIndex) {
        //1. 排序终止条件
        if (startIndex >= endIndex) {
            return;
        }

        //2. 获取基准点
        int pivot = partition(arr, startIndex, endIndex);

        //3. 根据基准点，分成两部分进行递归排序
        quickSort(arr, startIndex, pivot - 1);
        quickSort(arr, pivot + 1, endIndex);
        automicLong.incrementAndGet();
    }

    /**
     * 分治（单边循环发）
     *
     * @param arr        待排序数组
     * @param startIndex 起始位置
     * @param endIndex   结束位置
     * @return
     */
    public static int partitionByUnilateral(int[] arr, int startIndex, int endIndex) {

        // 取第一个位置的元素作为基准元素（也可以随机选择）
        int pivot = arr[startIndex];
        int mark = startIndex;

        for (int i = startIndex + 1; i <= endIndex; i++) {
            if (arr[i] < arr[mark]) {
                mark++;
                int tmp = arr[mark];
                arr[mark] = arr[i];
                arr[i] = tmp;
            }
        }

        //pivot和指针重合点交换
        arr[startIndex] = arr[mark];
        arr[mark] = pivot;

        return mark;
    }

    public static int partition(int[] arr, int startIndex, int endIndex) {
        // 获取基准点元素，默认第一个（也可以随机获取）
        int pivot = arr[startIndex];
        int left = startIndex;
        int right = endIndex;

        // 移动指针并交换元素
        while (left != right) {

            // 右指针向左移动
            while (left < right & arr[right] > pivot) {
                right--;
            }
            // 左指针向右移动
            while (left < right & arr[left] <= pivot) {
                left++;
            }

            if (left != right) {
                int t = arr[left];
                arr[left] = arr[right];
                arr[right] = t;
            }
        }

        // 基准元素交换位置
        arr[startIndex] = arr[left];
        arr[left] = pivot;

        return left;
    }
}
