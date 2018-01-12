package com.duxiaoqier.algorithm.list.merge;

import com.duxiaoqier.algorithm.list.ListNode;

public class Solution {

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(new int[]{1, 3, 5});
        ListNode listNode2 = new ListNode(new int[]{2, 4, 6});

        new Solution().Merge(listNode1, listNode2).print();
        new Solution().Merge2(listNode1, listNode2).print();
    }
    public ListNode Merge(ListNode list1, ListNode list2) {
        if(list1 == null){
            return list2;
        }
        if (list2 == null){
            return  list1;
        }
        ListNode min = list1.val < list2.val ? list1: list2;
        ListNode max = list1.val >= list2.val ? list1: list2;

        ListNode result = min;
        ListNode temp = min;

        while (min.next != null){
            ListNode minNext = min.next;
            if (minNext.val <= max.val){
                temp.next = minNext;
                min = min.next;
            } else {
                min = max;
                temp.next = max;
                max = minNext;
            }
            temp = temp.next;
        }
        temp.next = max;
        return result;
    }

    public ListNode Merge2(ListNode list1, ListNode list2) {
        if(list1 == null){
            return list2;
        }
        if (list2 == null){
            return  list1;
        }

        ListNode head;
        if (list1.val < list2.val){
            head = list1;
            head.next = Merge2(list1.next, list2);
        } else {
            head = list2;
            head.next = Merge2(list1, list2.next);
        }
        return head;
    }

}