package com.example.thinking.test.algorithm.leetcode;

/**
 * 328. 奇偶链表
 * https://leetcode-cn.com/problems/odd-even-linked-list/
 *
 * 力扣官方题解
 * <p>
 * 方法一：分离节点后合并
 * 如果链表为空，则直接返回链表
 * <p>
 * https://leetcode-cn.com/problems/odd-even-linked-list/solution/qi-ou-lian-biao-by-leetcode-solution/
 */
public class OddEvenList {

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

    /**
     * 方法一：分离节点后合并
     *
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode evenHead = head.next;
        ListNode even = evenHead;
        ListNode odd = head;

        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }

        odd.next = evenHead;
        return head;
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

        OddEvenList list = new OddEvenList();
        ListNode node = list.oddEvenList(node1);
        System.out.println(node);
    }
}
