package com.example.thinking.test.algorithm.leetcode;


import com.example.thinking.test.algorithm.linkedlist.HasCycle;

public class ReverseLinkList {

    static class ListNode {

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

    public static ListNode reverseList(ListNode head) {
        // 递归终止条件
        if (head == null || head.next == null) {
            return head;
        }
        // 递归调用
        // 1-递的过程
        ListNode p = reverseList(head.next);
        // 2，归的过程
        head.next.next = head;
        head.next = null;

        return p;
    }


    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;


        ListNode listNode = reverseList(node1);

        System.out.println(listNode);


    }
}
