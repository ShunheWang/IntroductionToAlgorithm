package com.company;


import com.sun.source.tree.Tree;

import javax.lang.model.util.Elements;
import javax.swing.plaf.IconUIResource;
import java.util.ArrayDeque;
import java.util.Deque;

public class Solution104 {

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
    public int maxDepth01(TreeNode root) {
        if (root == null) return 0;
        int a = maxDepth01(root.left);
        int b = maxDepth01(root.right);
        return Math.max(a,b) + 1;
    }


    /**
     * iterator (BFS) 双指针 跟踪&记录
     * @param root
     * @return
     */
    public int maxDepth02(TreeNode root) {
        if (root == null) return 0;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        TreeNode layerPointer = root; // record final node in every layer
        TreeNode track = root; // record current node's final child
        int ans = 0;
        while (!queue.isEmpty()) {
            TreeNode curNode = queue.poll();
            if (curNode.left != null) {
                queue.add(curNode.left);
                track = curNode.left;
            }
            if (curNode.right != null) {
                queue.add(curNode.right);
                track = curNode.right;
            }
            if (layerPointer == curNode) {
                layerPointer = track;
                ans++;
            }
        }
        return ans;
    }

    /**
     * iterator (BFS) --> record node size of every layer
     * @param root
     * @return
     */
    public int maxDepth03(TreeNode root) {
        if (root == null) return 0;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int ans = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode curNode = queue.poll();
                if (curNode.left != null) queue.add(curNode.left);
                if (curNode.right != null) queue.add(curNode.right);
                size--;
            }
            ans++;
        }
        return ans;
    }

    /**
     * DFS
     * @param root
     * @return
     */
    int maxLength = 0;
    public int maxDepth04(TreeNode root) {
        if (root == null) return 0;
        dfs(root,1);
        return maxLength;
    }

    public void dfs(TreeNode curNode, int curLength) {
        if (curNode == null) return;
        if (maxLength < curLength) maxLength = curLength;
        dfs(curNode.left, curLength + 1);
        dfs(curNode.right, curLength + 1);
    }


    public static void main(String[] args) {
        //[1,2,2]
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        t1.left = t2;
        t1.right = t3;
        t3.left = t4;
        t3.right = t5;
        //t1.left.left = new TreeNode(4);
        System.out.println(new Solution104().maxDepth02(t1));
    }
}