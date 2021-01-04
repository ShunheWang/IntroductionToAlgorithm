package com.company;

import com.sun.source.tree.Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class Solution101 {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * recursive
     * @param root
     * @return
     */
    public boolean isSymmetric1(TreeNode root) {
        if (root == null) return true;
        else return recursive(root.left,root.right);
    }

    public boolean recursive(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        else if (left == null && right != null) return false;
        else if (left != null && right == null) return false;
        else if (left.val != right.val) return false;
        else return recursive(left.left,right.right) && recursive(left.right,right.left);
    }

    /**
     * iterator1 (single-queue)
     * 效率慢 因为相当于每组数据offer 进去两遍
     * 而且注意！！！！！！！！！！！！！！！！！！ --> LinkedList.offer(null) 不会出现 nullPointerException 但是ArrayQueue.offer(null) 会!!!
     * 改进办法 --> double single-queue (LinkedList) 这样只用检查一遍即可
     * @param root
     * @return
     */
    public boolean isSymmetric2(TreeNode root) {
        if (root == null) return true;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(root); // add root into queue 2 times
        while (!queue.isEmpty()) {
            TreeNode u = queue.poll();
            TreeNode v = queue.poll();
            if (u == null && v == null) continue;
            else if (u == null || v == null) return false;
            else if (u.val != v.val) return false;
            queue.offer(u.left);
            queue.offer(v.right);
            queue.offer(u.right);
            queue.offer(v.left);
        }
        return true;
    }

    public static void main(String[] args) {
        //[1,2,2]
        TreeNode t1 = new TreeNode(1);
        //t1.left = new TreeNode(2);
        t1.right = new TreeNode(2);
        System.out.println(new Solution101().isSymmetric2(t1));
    }
}