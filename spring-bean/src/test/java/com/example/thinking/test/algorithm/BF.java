package com.example.thinking.test.algorithm;

/**
 * @auther: 巨未
 * @DATE: 2019/2/15  9:44
 * @Description:
 */
public class BF {
    /**
     * @param str 主串
     * @param sub 子串
     * @param pos 从主串的pos位置开始找.
     * @return
     */
    public static int BF(String str, String sub, int pos) {
        int lenstr = str.length();
        int lensub = sub.length();
        //对pos的位置合法判断
        if (pos < 0 || pos > lenstr) {
            return -1;
        }
        int i = pos;  //遍历主串，
        int j = 0;    //遍历子串
        while (i < lenstr && j < lensub) {  //主串和子串都没有遍历完成的情况下，比较字符是否相等
            if (str.charAt(i) == sub.charAt(j)) { //下标所对应的字符相等时，
                i++;
                j++;
            } else {   //匹配失败
                i = i - j + 1; //
                j = 0;
            }
        }
        if (j >= sub.length()) { //子串遍历完成则返回匹配成功时 i 的起始位置。
            return i - j;
        } else {   // 没找到
            return -1;
        }
    }

    public static void main(String[] args) {
        String str = "abcdadcdacd";
        String sub = "adc";
        int index = BF(str, sub, 0);
        System.out.println("第一次出现的位置:" + index);
    }
}