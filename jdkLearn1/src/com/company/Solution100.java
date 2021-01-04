package com.company;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class Solution100 {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */

    public static class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode() {}
         TreeNode(int val) { this.val = val; }
         TreeNode(int val, TreeNode left, TreeNode right) {
             this.val = val;
             this.left = left;
             this.right = right;
         }
    }

    /**
     * DFS
     * @param p
     * @param q
     * @return
     */
        public boolean isSameTree1(TreeNode p, TreeNode q) {
            if (p == null && q == null) return true;
            if (p == null && q != null) return false;
            if (p != null && q == null) return false;
            Deque<TreeNode> queueP = new ArrayDeque();
            Deque<TreeNode> queueQ = new ArrayDeque();
            // init
            queueP.add(p);
            queueQ.add(q);
            while (!queueP.isEmpty() && !queueQ.isEmpty()) {
                TreeNode pNode = queueP.poll();
                TreeNode qNode = queueQ.poll();
                if (pNode.val != qNode.val) return false;
                if (pNode.left != null && qNode.left!=null) {
                    queueP.add(pNode.left);
                    queueQ.add(qNode.left);
                }else if ((pNode.left != null && qNode.left == null) || (pNode.left == null && qNode.left != null)) {
                    return false;
                }
                if (pNode.right != null && qNode.right!=null) {
                    queueP.add(pNode.right);
                    queueQ.add(qNode.right);
                }else if ((pNode.right != null && qNode.right == null) || (pNode.right == null && qNode.right != null)) {
                    return false;
                }
            }
            return true;
        }

    /**
     * recursive
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree2(TreeNode p, TreeNode q) {
       if (p == null && q == null) return true;
       else if (p == null || q == null) return false;
       else if (p.val != q.val) return false;
       else return isSameTree2(p.left, q.left) && isSameTree2(p.right, q.right);
    }

    public static void main(String[] args) {
        //[1,1]
        //[1,null,1]
//        TreeNode p1 = new TreeNode(1);
//        TreeNode p2 = new TreeNode(2);
//        TreeNode p3 = new TreeNode(3);
//        p1.right = p2;
//        p2.left  = p3;
//
//        TreeNode q1 = new TreeNode(1);
//        TreeNode q2 = new TreeNode(2);
//        TreeNode q3 = new TreeNode(3);
//        q1.right = q2;
//        q2.left = q3;
//
//        System.out.println(new Solution100().isSameTree1(p1,q1));
    }
}