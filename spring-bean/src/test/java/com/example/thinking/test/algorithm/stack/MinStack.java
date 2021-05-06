package com.example.thinking.test.algorithm.stack;

import java.util.EmptyStackException;
import java.util.Stack;

public class MinStack {

    private Stack<Integer> mainStack;

    private Stack<Integer> minStack;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        mainStack = new Stack();
        minStack = new Stack();
    }

    public void push(int val) {
        mainStack.push(val);
        // 如果辅助栈为空或者小于等于辅助栈的栈顶元素，辅助栈进行入栈操作
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }

    public void pop() {
        // 如果出栈元素等于辅助栈栈顶元素，进行出栈操作
        if (minStack.peek().equals(mainStack.peek())) {
            minStack.pop();
        }
        mainStack.pop();
    }

    public int top() {
        if (mainStack.isEmpty())
            throw new EmptyStackException();
        return mainStack.peek();
    }

    public int getMin() {
        if (minStack.isEmpty())
            throw new EmptyStackException();
        return minStack.peek();
    }

    public static void main(String[] args) {
        // ["MinStack","push","push","push","getMin","pop","top","getMin"]
        // [[],[-2],[0],[-3],[],[],[],[]]

        MinStack obj = new MinStack();
        obj.push(-2);
        obj.push(0);
        obj.push(-3);
        obj.getMin();
        obj.pop();

        obj.pop();
        obj.pop();
        int param_3 = obj.top();
        int param_4 = obj.getMin();

        //int param_3 = obj.top();
        //int param_4 = obj.getMin();

        System.out.println(param_3 + "," + param_4);
    }
}
