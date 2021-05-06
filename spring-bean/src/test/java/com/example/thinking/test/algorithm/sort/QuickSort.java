package com.example.thinking.test.algorithm.sort;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

public class QuickSort {

    private static AtomicLong automicLong = new AtomicLong(0);

    private static AtomicLong automicLong2 = new AtomicLong(0);


    public static void main(String[] args) {
        int[] arr = {5, 8, 9, 6, 9, 4, 3, 2, 1, 3, 28, 40, 28, 61, 84, 999, 0};


        System.out.println("============双边循环发之快速排序=========");

        quickSort(arr, 0, arr.length - 1);

        System.out.println("" + Arrays.toString(arr));
        System.out.println(automicLong.get());


    }

    /**
     * 快速排序（双边循环发）
     *
     * @param arr        待排序数组
     * @param startIndex 起始位置
     * @param endIndex   结束位置
     */
    public static void quickSort(int[] arr, int startIndex, int endIndex) {
        //  递归结束条件
        if (startIndex >= endIndex) {
            return;
        }

        //得到基准元素位置
        int pivotIndex = partitionByUnilateral(arr, startIndex, endIndex);

        // 根据基准元素，分成两部分进行递归排序
        quickSort(arr, startIndex, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, endIndex);

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
            if (arr[i] < pivot) {
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

    /**
     * 分治（双边循环发）
     *
     * @param arr        待排序数组
     * @param startIndex 起始位置
     * @param endIndex   结束位置
     * @return
     */
    public static int partition(int[] arr, int startIndex, int endIndex) {

        // 取第一个位置的元素作为基准元素（也可以随机选择）
        int pivot = arr[startIndex];
        int left = startIndex;
        int right = endIndex;

        while (left != right) {

            // 控制right指针比较并左移动
            while (right > left && arr[right] > pivot) {
                right--;
            }
            // 控制left指针比较并右移动
            while (right > left && arr[left] <= pivot) {
                left++;
            }

            // 交换left指针和right指针所指向的元素
            if (left != right) {
                int tmp = arr[left];
                arr[left] = arr[right];
                arr[right] = tmp;
            }
        }

        //pivot和指针重合点交换
        arr[startIndex] = arr[left];
        arr[left] = pivot;

        return left;
    }
}
