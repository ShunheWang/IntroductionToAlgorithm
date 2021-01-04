package com.company;


import java.util.HashMap;

public class Solution106 {

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

    int startPoint = 0;

    public TreeNode buildTree1(int[] inorder, int[] postorder) {
        if (postorder == null && inorder == null) return null;
        int len1 = inorder.length;
        int len2 = postorder.length;
        if (len1 != len2) return null;
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            hashMap.put(inorder[i],i);
        }
        startPoint = len2 - 1;
        TreeNode root = createTree1(inorder,postorder,hashMap,0,len1-1);
        return root;
    }

    public TreeNode createTree1(int[] inorder, int[] postorder, HashMap<Integer,Integer> hashMap, int inOrderLeft, int inOrderRight) {
        if (inOrderLeft > inOrderRight) {
            return null;
        }
        int pVal = postorder[startPoint];
        int pIndex = hashMap.get(pVal);
        TreeNode root = new TreeNode(pVal);
        startPoint--;
        root.right = createTree1(inorder,postorder,hashMap,pIndex+1,inOrderRight);
        root.left = createTree1(inorder,postorder,hashMap,inOrderLeft,pIndex-1);
        return root;
    }

    public static void main(String[] args) {
        //前序遍历 preorder = [3,9,20,15,7]
        //中序遍历 inorder = [9,3,15,20,7]
        //[1,2,3]
        //[3,2,1]

        //[1,2,3]
        //[1,2,3]

        //[7,-10,-4,3,-1,2,-8,11]
        //[-4,-10,3,-1,7,11,-8,2]
        int[] preorder = {7,-10,-4,3,-1,2,-8,11};
        int[] inorder = {-4,-10,3,-1,7,11,-8,2};
        System.out.println(new Solution106().buildTree1(preorder,inorder));
    }
}