package com.company;


import com.sun.source.tree.Tree;

import java.util.*;

public class Solution107 {

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

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> ans = new LinkedList<List<Integer>>();
        if (root == null) return ans;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int level = queue.size();   // size = 1;
            while (level > 0) {
                TreeNode currTreeNode = queue.poll();
                list.add(currTreeNode.val);
                if (currTreeNode.left != null) queue.offer(currTreeNode.left);
                if (currTreeNode.right != null) queue.offer(currTreeNode.right);
                level--;
            }
            ans.addFirst(list);
        }
        return ans;
    }


    public static void main(String[] args) {
        // [3,9,20,null,null,15,7]
        TreeNode root = new TreeNode(3);
        TreeNode treeNode1 = new TreeNode(9);
        TreeNode treeNode2 = new TreeNode(20);
        TreeNode treeNode3 = new TreeNode(15);
        TreeNode treeNode4 = new TreeNode(7);

        root.left = treeNode1;
        root.right = treeNode2;

        treeNode2.left = treeNode3;
        treeNode2.right = treeNode4;

        System.out.println(new Solution107().levelOrderBottom(root));
    }
}