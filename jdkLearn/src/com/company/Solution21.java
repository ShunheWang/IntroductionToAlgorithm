package com.company;


import java.util.List;

public class Solution21 {


    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */

     public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode l3 = new ListNode();
        ListNode dummy = l3;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                dummy.next = l2;
                break;
            }else if (l2 == null) {
                dummy.next = l1;
                break;
            }else{
                if (l1.val <= l2.val) {
                    dummy.next = l1;
                    l1 = l1.next;
                }else {
                    dummy.next = l2;
                    l2 = l2.next;
                }
            }
            dummy = dummy.next;
        }
        return l3.next;
    }


    public static void main(String[] args) {
         //[1,2,4]
         //[1,3,4]
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode listNode = new Solution21().mergeTwoLists(l1, l2);
        while (listNode != null) {
            System.out.print(listNode.val + "");
            listNode = listNode.next;
        }

    }
}