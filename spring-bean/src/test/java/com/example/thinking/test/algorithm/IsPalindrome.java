package com.example.thinking.test.algorithm;

/**
 * 验证字符串回文算法
 * { @link https://leetcode-cn.com/leetbook/read/top-interview-questions/xah8k6/ }
 * <p>
 *
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 *
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 * 示例 1:
 *
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 *
 * 示例 2:
 *
 * 输入: "race a car"
 * 输出: false
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xah8k6/
 * </p>
 * <p>
 * 首先，理解什么是回文字符串，简单的一句话概括就是关于中心左右对称的字符串。
 * 例如：ABCBA或者AACCAA是回文字符串；ABCCA或者AABBCC不是回文字符串。
 * </p>
 */
public class IsPalindrome {

    public static void main(String[] args) {

        String s = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(s));
        System.out.println(isPalindrome2(s));

    }

    /**
     * 双指针
     *
     * @param s
     * @return
     */
    public static boolean isPalindrome2(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        int left = 0;
        int right = s.length() - 1;

        s = s.toLowerCase();
        while (left < right) {
            // 字母和数字的先过滤掉
            while (left < right & !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right & !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 正则匹配
     * 正则：https://www.runoob.com/regexp/regexp-syntax.html
     *
     * @param s
     * @return
     */
    public static boolean isPalindrome(String s) {
        String actual = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        String rev = new StringBuffer(actual).reverse().toString();
        return actual.equals(rev);
    }
}
