package com.example.thinking.test.algorithm.linkedlist;

public class ListNode {

    int val;
    ListNode next;

    public ListNode(int x) {
        this.val = x;
        next = null;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
