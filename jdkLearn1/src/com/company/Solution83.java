package com.company;

import java.util.*;

public class Solution83 {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode deleteDuplicates1(ListNode head) {
        if (head == null) return null;
        List<Integer> list = new ArrayList();
        while (head != null) {
            if (!list.contains(head.val)) {
                list.add(head.val);
            }
            head = head.next;
        }

        ListNode ans = new ListNode(0);
        ListNode dummy = ans;
        ListIterator iterator = list.listIterator();
        while (iterator.hasNext()) {
            Integer curVal = (Integer) iterator.next();
            dummy.next = new ListNode(curVal);
            dummy = dummy.next;
        }
        return ans.next;
    }

    /**
     * recursive
     * @param head
     * @return
     */
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;
        if (head.val == head.next.val) {head = deleteDuplicates2(head.next);} // if two node is same, --> delete first one means head pointer point to second one
        else {head.next = deleteDuplicates2(head.next);}  // if two node is not same, --> cur_head.next = head.next means keeping cur_head node
        return head;
    }

    /**
     * iterator
     * @param head
     * @return
     */
    public ListNode deleteDuplicates3(ListNode head) {
        if (head == null) return null;
        ListNode curNode = head;
        while (curNode.next!=null) {
            if (curNode.val == curNode.next.val) {
                curNode.next = curNode.next.next;
            }else {
                curNode = curNode.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        //listNode.next.next = new ListNode(1);
        new Solution83().deleteDuplicates3(listNode);
    }
}