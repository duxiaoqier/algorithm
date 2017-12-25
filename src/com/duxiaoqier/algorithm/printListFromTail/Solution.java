package com.duxiaoqier.algorithm.printListFromTail;

import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {

        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
//        node.next.next.next = new ListNode(4);

        System.out.println(new Solution().printListFromTailToHead(node));
    }

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {


        ArrayList<Integer> list = new ArrayList<>();
        if (listNode == null) return list;
        if (listNode.next == null){
            list.add(listNode.val);
            return list;
        }

        ListNode previousNode = listNode;
        ListNode currentNode = listNode.next;
        ListNode nextNode = currentNode.next;
        previousNode.next = null;
        currentNode.next = previousNode;

        while (nextNode!= null){
            ListNode temp = nextNode.next;
            currentNode.next = previousNode;
            previousNode = currentNode;
            nextNode.next = currentNode;
            currentNode = nextNode;
            nextNode = temp;

        }
        while (currentNode != null){
            list.add(currentNode.val);
            currentNode = currentNode.next;
        }

        return list;
    }

    private static void printNode(ListNode node) {
        while (node != null){
            System.out.println(node.val);
            node = node.next;
        }
    }
}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
