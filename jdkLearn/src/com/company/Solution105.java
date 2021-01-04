package com.company;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class Solution105 {

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

    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        if (preorder == null && inorder == null) return null;
        int len1 = preorder.length;
        int len2 = preorder.length;
        if (len1 != len2) return null;
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            hashMap.put(inorder[i],i);
        }
        TreeNode root = createTree(preorder,inorder,hashMap,0,len1-1);
        return root;
    }

    public TreeNode createTree(int[] preorder, int[] inorder, HashMap<Integer,Integer> hashMap, int inOrderI, int inOrderJ) {
        if (inOrderI>inOrderJ) {
            return null;
        }
        //if (inOrderI == inOrderJ) return new TreeNode(inorder[inOrderI]);
        int rootIndex = hashMap.get(preorder[startPoint]);
        TreeNode root = new TreeNode(preorder[startPoint]);
        startPoint++;
        root.left = createTree(preorder,inorder,hashMap,inOrderI, rootIndex-1);
        root.right = createTree(preorder,inorder,hashMap,rootIndex+1,inOrderJ);
        return root;
    }


    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        if (preorder == null && inorder == null) return null;
        int len1 = preorder.length;
        int len2 = preorder.length;
        if (len1 != len2) return null;
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            hashMap.put(inorder[i],i);
        }
        TreeNode root = createTree2(preorder,inorder,hashMap,0,len1-1,0,len2-1);
        return root;
    }

    public TreeNode createTree2(int[] preorder, int[] inorder, HashMap<Integer,Integer> hashMap, int prevI, int prevJ, int inI, int inJ) {
        //todo
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
        System.out.println(new Solution105().buildTree2(preorder,inorder));
    }
}