package com.duxiaoqier.algorithm.list;

import java.util.ArrayList;
import java.util.List;

public class ListNode {
    public int val;
    public ListNode next = null;

    public ListNode(int val) {
        this.val = val;
    }

/*    public ListNode(int[] vals){
        if (vals.length > 1){
            this.val = vals[0];
            int[] dest = Arrays.copyOfRange(vals, 1, vals.length);
            this.next = new ListNode(dest);
        } else {
            this.val = vals[0];
            this.next = null;
        }
    }*/

    public ListNode(int[] vals) {
        if (vals.length == 0) return;
        this.val = vals[0];
        ListNode temp = this;
        for (int i = 1; i < vals.length; i++) {
            temp.next = new ListNode(vals[i]);
            temp = temp.next;
        }
    }


    public void print() {
        ListNode head = this;
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    @Override
    public String toString() {
        List<Integer> list = new ArrayList<>();
        ListNode head = this;
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        return list.toString();
    }
}