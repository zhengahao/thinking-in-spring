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
        if (minStack.isEmpty() || minStack.peek() < val) {
            minStack.push(val);
        }
    }

    public void pop() {
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
        MinStack obj = new MinStack();
        obj.push(1);
        obj.pop();
        int param_3 = obj.top();
        int param_4 = obj.getMin();

        System.out.println(param_3 + "," + param_4);
    }
}
