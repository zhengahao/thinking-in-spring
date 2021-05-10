package com.example.thinking.test.algorithm.leetcode;

/**
 * 画解算法：171. Excel表列序号
 * <p>
 * https://leetcode-cn.com/problems/excel-sheet-column-number/submissions/
 * 题解如下：
 * <p>
 * https://leetcode-cn.com/problems/excel-sheet-column-number/solution/hua-jie-suan-fa-171-excelbiao-lie-xu-hao-by-guanpe/
 */
public class TitleToNumber {

    public static int titleToNumber(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            int num = s.charAt(i) - 'A' + 1;
            ans = ans * 26 + num;
        }
        return ans;
    }


    public static void main(String[] args) {

        int s = titleToNumber("ZB");
        System.out.println(s);
    }


}
