package com.duxiaoqier.algorithm.list.kth.to.tail;

public class Solution {

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        System.out.println(new Solution().FindKthToTail2(listNode1, 2).val);
    }

    public ListNode FindKthToTail(ListNode head, int k) {
        int count = 0;
        ListNode temp = head;
        while (temp != null) {
            temp = temp.next;
            count++;
        }
        if (count - k >= 0) {
            for (int i = 0; i < count - k; i++) {
                head = head.next;
            }
            return head;
        }
        return null;
    }

    public ListNode FindKthToTail2(ListNode head, int k) {
        ListNode preNode = head;
        ListNode nextNode = head;

        for (int i = 0; i < k; i++) {
            if (nextNode != null) {
                nextNode = nextNode.next;
            } else {
                return null;
            }
        }

        while (nextNode != null) {
            nextNode = nextNode.next;
            preNode = preNode.next;
        }

        return preNode;
    }
}


class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}