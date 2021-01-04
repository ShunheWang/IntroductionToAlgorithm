package com.company;


import com.sun.source.tree.Tree;

import java.util.*;

public class Solution108 {

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

    /**
     * recursive
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        int leftIndex = 0, rightIndex = nums.length - 1;
        TreeNode root = recursive(nums,leftIndex,rightIndex);
        return root;
    }

    private TreeNode recursive(int[] nums, int lIndex, int rIndex) {
        if (lIndex>rIndex) return null;
        // mid point
        int mIndex = (rIndex-lIndex)/2+lIndex;
        int mVal = nums[mIndex];
        TreeNode curNode = new TreeNode(mVal);
        curNode.left = recursive(nums,lIndex,mIndex-1);
        curNode.right = recursive(nums,mIndex+1,rIndex);
        return curNode;
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


    }
}