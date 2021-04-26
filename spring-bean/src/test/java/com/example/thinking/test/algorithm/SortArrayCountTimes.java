package com.example.thinking.test.algorithm;

/**
 * @Author：zzh
 * @Description: 排序数组统计次数
 * @Date: 2021/3/10 11:40 上午
 */
public class SortArrayCountTimes {

    public static void main(String[] args) {

        int[] arr = {1, 1, 2, 3, 5, 5, 6, 7, 8, 8, 8, 9};

        //int number = search1(arr, 8);
        int left = search2(arr, 8);

        //System.out.println(number);
    }

    /**
     * 常规做法:设置一个 ret=0 变量记录 target 出现的次数， 遍历整个数组，输出 ret 空间o(1) 时间o(n)
     *
     * @param arr
     * @param target
     * @return
     */
    public static int search1(int[] arr, int target) {
        if (arr.length == 0) return 0;
        int temp = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                temp++;
            }
        }
        return temp;
    }

    /**
     * 进阶做法:使用二分法，找到左边界，右边界，差值即为所找数字个数 空间 o(1) 时间 o(2 * logn)
     *
     * @param arr
     * @param target
     * @return
     */
    public static int search2(int[] arr, int target) {
        if (arr.length == 0) return 0;
        int left = findLeft(arr, target);
        if (left != -1) {
            int right = findRight(arr, target);
            return right - left + 1;
        }

        return 0;
    }

    public static int findLeft(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < target) {
                //此时 target 在区间mid + 1, right中
                left = mid + 1;
            } else if (arr[mid] < target) {
                //此时 target 在区间mid -1 1, left中
                right = mid - 1;
            } else {
                right = mid;
            }
        }

        if (arr[left] == target) {
            return left;
        }

        return -1;
    }


    public static int findRight(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while (left < right) {
            // 进行+1 防止死循环
            int mid = (right + left + 1) / 2;
            if (arr[mid] < target) {
                //此时 target 在区间[mid + 1, right]中
                left = mid + 1;
            } else if (arr[mid] > target) {
                //此时 target 在区间[mid -1, left]中
                right = mid - 1;
            } else {
                left = mid;
            }
        }

        return left;
    }

}
