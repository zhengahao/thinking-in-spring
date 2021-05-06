package com.example.thinking.test.algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * 作者：AC_OIer
 * 链接：https://leetcode-cn.com/problems/basic-calculator-ii/solution/shi-yong-shuang-zhan-jie-jue-jiu-ji-biao-c65k/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class BasicCalculator {

    // 使用 map 维护一个运算符优先级
    Map<Character, Integer> map = new HashMap() {{
        put("-", 1);
        put("+", 1);
        put("*", 2);
        put("/", 2);
        put("%", 2);
        put("^", 3);
    }};

    public int calculate(String s) {
        // 将所有的空格去掉，并将 (- 替换为 (0-，(+ 替换为 (0+
        // 当然这里也可以不预处理，而是放到循环里面去做判断
        s = s.replace(" ", "");
        s = s.replace("\\(-", "(0-");
        s = s.replace("\\(\\+", "(0+");
        char[] cs = s.toCharArray();
        int n = s.length();

        // 存放所有的数字
        Deque<Integer> nums = new ArrayDeque<>();
        // 为了防止第一个数为负数，先往nums加个0
        nums.addLast(0);
        // 存放所有非数字以外的操作
        Deque<Character> ops = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = cs[i];
            if (c == '(') {
                ops.addLast(c);
            } else if (c == ')') {
                // 计算到最近一个左括号为止
                while (!ops.isEmpty()) {
                    if (ops.peekLast() != '(') {
                        calc(nums, ops);
                    } else {
                        ops.pollLast();
                        break;
                    }
                }
            } else {
                if (isNumber(c)) {
                    int u = 0;
                    int j = i;
                    while (j < n && isNumber(cs[j])) {
                        u = u * 10 + (cs[j++] - '0');
                    }
                    nums.addLast(u);
                    i = j - 1;
                } else {
                    // 有一个新操作要入栈时，先把栈内可以算的都算了
                    // 只有满足「栈内运算符」比「当前运算符」优先级高/同等，才进行运算
                    while (!ops.isEmpty() && ops.peekLast() != '(') {
                        char prev = ops.peekLast();
                        if (map.get(prev) >= map.get(c)) {
                            calc(nums, ops);
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        //将剩余的计算完
        while (!ops.isEmpty()) calc(nums, ops);
        return nums.peekLast();

    }

    void calc(Deque<Integer> nums, Deque<Character> ops) {
        if (nums.isEmpty() || nums.size() < 2) {
            return;
        }
        if (ops.isEmpty()) return;
        int b = nums.pollLast(), a = nums.pollLast();
        char op = ops.pollFirst();
        int ans = 0;
        if (op == '+') {
            ans = a + b;
        } else if (op == '-') ans = a - b;
        else if (op == '*') ans = a * b;
        else if (op == '/') ans = a / b;
        else if (op == '^') ans = (int) Math.pow(a, b);
        else if (op == '%') ans = a % b;

        nums.addLast(ans);
    }

    boolean isNumber(char c) {
        return Character.isDigit(c);
    }

    public static void main(String[] args) {
        String s = "3+2*2";
        BasicCalculator basicCalculator = new BasicCalculator();
        int resutl = basicCalculator.calculate(s);

        System.out.println("计算公式: " + s);
        System.out.println("result: " + resutl);

    }
}
