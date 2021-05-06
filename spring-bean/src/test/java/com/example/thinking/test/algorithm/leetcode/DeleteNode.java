package com.example.thinking.test.algorithm.leetcode;


import java.util.Arrays;

public class DeleteNode {

    static class ListNode {
        private int val;
        private ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    public static void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(4);
        ListNode node1 = new ListNode(5);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(9);

        node.next = node1;
        node1.next = node2;
        node2.next = node3;

        System.out.println(node);

        deleteNode(node1);


        System.out.println(node);


    }
}