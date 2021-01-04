package com.company;

import java.util.List;

public class Solution2 {

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

    public int handleAddWithTwoNode(ListNode l1, ListNode l2, ListNode l3, int i) {
        if ((l1.val + l2.val + i) >= 10) {
            l3.val = l1.val + l2.val + i - 10;
            return 1;
        }
        l3.val = l1.val + l2.val + i;
        return 0;
    }

    public int handleAddWithOneNode(ListNode l, ListNode l3, int i) {
        if ((l.val + i) >= 10) {
            l3.val = l.val + i - 10;
            return 1;
        }
        l3.val = l.val + i;
        return 0;
    }

    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode l3 = new ListNode();
        int increment = 0;
        ListNode lastNodeL3 = l3;
        if (l1 == null) {
            return l2;
        }else if (l2 == null) {
            return l1;
        }else {
            // l1 !== null && l2 != null
            increment = handleAddWithTwoNode(l1, l2, l3, increment);   // hand root node
            ListNode curL1Node = l1;
            ListNode curL2Node = l2;
            while (curL1Node.next != null || curL2Node.next != null) {
                ListNode l3NextNode = new ListNode();
                if (curL1Node.next == null) {
                    // handle l2 only
                    increment = handleAddWithOneNode(curL2Node.next, l3NextNode, increment);
                }else if (curL2Node.next == null) {
                    // handle l1 only
                    increment = handleAddWithOneNode(curL1Node.next, l3NextNode, increment);
                }else {
                    //handle l1 & l2
                    increment = handleAddWithTwoNode(curL1Node.next, curL2Node.next, l3NextNode, increment);
                }
                // connect with new node and update last node of l3
                lastNodeL3.next = l3NextNode;
                lastNodeL3 = lastNodeL3.next;
                // update l1 & l2
                if (curL1Node.next != null) {
                    curL1Node = curL1Node.next;
                }
                if (curL2Node.next != null) {
                    curL2Node = curL2Node.next;
                }
            }
            if (increment == 1) {
                ListNode l3NextNode = new ListNode();
                l3NextNode.val = increment;
                lastNodeL3.next = l3NextNode;
            }
        }
        return l3;
    }



    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode l3 = new ListNode();
        ListNode dummy = l3;
        int incr = 0;
        while (l1 != null || l2!= null || incr != 0) {
            // compute l1 & l2
            int l1Val = l1 != null? l1.val : 0;
            int l2Val = l2 != null? l2.val : 0;
            // sum
            int sum = l1Val + l2Val + incr;
            incr = sum / 10;
            ListNode l3NewNode = new ListNode(sum % 10);
            dummy.next = l3NewNode;
            dummy = dummy.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        return l3.next;
    }




    public static void main(String[] args) {
//        [2,4,3]
//        [5,6,4]

        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);

        ListNode l2 = new ListNode(5);

        Solution2 solution2 = new Solution2();
        ListNode listNode = solution2.addTwoNumbers2(l1, l2);
        while (listNode != null) {
            System.out.print(listNode.val+" ");
            listNode = listNode.next;
        }
    }
}