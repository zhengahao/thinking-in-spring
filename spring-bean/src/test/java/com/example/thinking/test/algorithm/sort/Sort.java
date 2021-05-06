package com.example.thinking.test.algorithm.sort;

import com.sun.istack.internal.NotNull;

import java.util.Arrays;

/**
 * @Author：zzh
 * @Description: TODO
 * @Date: 2021/3/23 12:39 下午
 */
public class Sort {

    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 1, 3, 2};
        //bubblingSort(arr);
        quickSort();
        chooseSort();
        insertSort(arr);
    }

    @NotNull
    private static void insertSort(int arr[]) {
        for (int i = 1; i < arr.length; i++) {

            int val = arr[i];
            int j = i - 1;

            for (; j >= 0; --j) {
                if (arr[j] > val) {
                    arr[j + 1] = arr[j];// 数据移动
                } else {
                    break;
                }
            }
            //数据插入
            arr[j+1] = val;
        }

        System.out.println(Arrays.toString(arr));

    }

    private static void chooseSort() {
    }

    private static void quickSort() {
    }

    private static void bubblingSort(int arr[]) {

        for (int i = 0; i < arr.length; i++) {
            boolean flag = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                    flag = true;// 表示有数据交换
                }
            }
            if (!flag) {
                System.out.println("没有数据交换提前推出");
                break;
            }
        }

        System.out.println(Arrays.toString(arr));
    }
}
