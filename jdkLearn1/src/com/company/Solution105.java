package com.company;


import com.sun.source.tree.Tree;

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
        if (prevI > prevJ || inI > inJ) return null;
        int rootVal = preorder[prevI];
        TreeNode root = new TreeNode(rootVal);
        int pIndex = hashMap.get(rootVal);
        root.left = createTree2(preorder,inorder,hashMap,prevI+1,pIndex-inI+prevI,inI,pIndex-1);
        root.right = createTree2(preorder,inorder,hashMap,pIndex-inI+prevI+1,prevJ,pIndex+1,inJ);
        return root;
    }


    public static void main(String[] args) {

    }
}