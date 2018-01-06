package com.duxiaoqier.algorithm.list.reverse;

import com.duxiaoqier.algorithm.bean.ListNode;

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

        ListNode listNode = new Solution().ReverseList2(listNode1);
        new Solution().print(listNode);
    }

    public ListNode ReverseList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode preNode = head;
        ListNode currentNode = head.next;
        ListNode nextNode = currentNode.next;
        preNode.next = null;

        while (nextNode != null){
            ListNode temp = nextNode.next;
            nextNode.next = currentNode;
            currentNode.next = preNode;
            preNode = currentNode;
            currentNode = nextNode;
            nextNode = temp;
        }

        return currentNode;
    }

    public ListNode ReverseList2(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode preNode = head;
        ListNode currentNode = head.next;
        preNode.next = null;

        while (currentNode != null){
            ListNode temp = currentNode.next;
            currentNode.next = preNode;
            preNode = currentNode;
            currentNode = temp;
        }

        return preNode;
    }

    public void print(ListNode head) {
        while (head != null){
            System.out.println(head.val);
            head = head.next;
        }
    }


}