package com.example.thinking.test.algorithm.linkedlist;

/**
 * 148. 排序链表
 * <p>
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表
 * https://leetcode-cn.com/problems/sort-list/
 *
 * <p>
 * 题解参考：
 * 作者：cherry-n1
 * 链接：https://leetcode-cn.com/problems/sort-list/solution/pai-xu-lian-biao-di-gui-die-dai-xiang-jie-by-cherr/
 */
public class SortList {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(7);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(9);
        ListNode node5 = new ListNode(3);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        SortList sortList = new SortList();
        ListNode node = sortList.sortList(node1);
        System.out.println(node);
    }


    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next; //慢指针走一步
            fast = fast.next.next; //快指针走两步
        }
        ListNode rightHead = slow.next; //链表第二部分的头节点
        slow.next = null; // 从中间切断链表

        ListNode left = sortList(head); //递归排序前一段列表
        ListNode right = sortList(rightHead);//递归排序后一排列表

        return merge(left, right); // 合并列表
    }

    public ListNode merge(ListNode h1, ListNode h2) {
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;

        while (h1 != null && h2 != null) {
            if (h1.val < h2.val) {
                p.next = h1;
                h1 = h1.next;
            } else {
                p.next = h2;
                h2 = h2.next;
            }
            p = p.next;
        }

        if (h1 != null) {
            p.next = h1;
        } else if (h2 != null) {
            p.next = h2;
        }

        return dummy.next;
    }
}
