package com.example.thinking.test.algorithm.array;

import java.util.Arrays;

public class findKthLargestNum2 {


    public static void main(String[] args) {

        int[] arr = {5, 8, 9, 6, 9, 4, 3, 2, 1, 3, 28, 40, 28, 61, 84, 999, 0};

        quickSort(arr, 0, arr.length - 1);

        System.out.println(Arrays.toString(arr));
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
        int pivotIndex = partition(arr, startIndex, endIndex);

        // 根据基准元素，分成两部分进行递归排序
        quickSort(arr, startIndex, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, endIndex);

    }

    public static int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        int j = left;
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] < pivot) {
                // 小于 pivot 的元素都被交换到前面
                j++;
                swap(nums, j, i);
            }
        }
        // 在之前遍历的过程中，满足 [left + 1, j] < pivot，并且 (j, i] >= pivot
        swap(nums, j, left);
        // 交换以后 [left, j - 1] < pivot, nums[j] = pivot, [j + 1, right] >= pivot
        return j;
    }

    private static void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

}
