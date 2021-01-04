package com.company;

import java.rmi.dgc.VMID;

public class Solution14 {

    /**
     * 1. quick sort (nlogn) + 纵向比较
     * time: 1ms, 88.40%, space: 36.3mb, 99.92%
     * @param strs
     * @return
     */
    public String longestCommonPrefix1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        // quick sort by increasing of string length
        quickSortRecursive(strs,0,strs.length-1);
        for (int i = 0; i < strs[0].length(); i++) {
            char track = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].charAt(i) != track) {
                    return sb.toString();
                }
            }
            sb.append(track);
        }
        return sb.toString();
    }

    private void quickSortRecursive(String[] strs, int f, int l) {
        if (f >= l) {
            return;
        }
        int q = partition(strs, f, l);
        quickSortRecursive(strs,f,q-1);
        quickSortRecursive(strs,q+1,l);
    }

    private int partition(String[] strs, int f, int l) {
        int pilot = strs[l].length();
        // start
        int i = f-1;
        for (int j = f; j < l; j++) {
            if (strs[j].length() <= pilot) {
                i = i + 1;
                swap(strs,i,j);
            }
        }
        // swap with pilot
        i = i + 1;
        swap(strs,i,l);
        return i;
    }

    private void swap(String[] strs, int i, int j) {
        String temp = strs[i];
        strs[i] = strs[j];
        strs[j] = temp;
    }

    /**
     * 二分查找
     * 思路: 对于最小String长度 二分查找 找到mid point, 比较所有string的mid-point的字符，不相同则最小前缀在[0-mid], 相同则在[mid-string.length]
     * @param strs
     * @return
     */
    public String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        // check --> ["","abc",NULL,"abcdefg"]
        for (int i = 0; i < strs.length; i++) {
            if (strs[i] == null || strs[i].length() == 0) {
                return "";
            }
        }
        char firstChar = strs[0].charAt(0);
        // check char[0] if no equal return false;
        for (int i = 1; i < strs.length; i++) {
            if (strs[1].charAt(0) != firstChar) {
                return "";
            }
        }

        // position of min point
        int minPoint = 0;
        int minLength = strs[minPoint].length();
        for (int i = 1; i < strs.length; i++) {
            if (strs[i].length() < minLength) {
                minPoint = i;
                minLength = strs[minPoint].length();
            }
        }
        int res = binarySearch(strs, minPoint, 0, minLength);
        return strs[minPoint].substring(0,res+1);
    }

    public int binarySearch(String[] strs, int min, int p, int r) {
        // mid point
        if (p+1 == r) {
            return p;
        }
        int midPoint = (p+r)/2;
        char midChar = strs[min].charAt(midPoint);
        for (int i = 1; i < strs.length; i++) {
            if (strs[i].charAt(midPoint) != midChar) {
                // in left side
                return binarySearch(strs,min,p,midPoint);
            }
        }
        // in right side
        return binarySearch(strs,min,midPoint,r);
    }


    public static void main(String[] args) {
        String[] strs = {""};
        System.out.print(new Solution14().longestCommonPrefix2(strs));
    }
}