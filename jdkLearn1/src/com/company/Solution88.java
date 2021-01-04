package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Solution88 {

    /**
     * quick_sort
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        if (nums1 == null || nums2 == null) return;
        // System.arraycopy(nums1,0,nums3,0,m);
        System.arraycopy(nums2,0,nums1,m,n);
        quickSort(nums1,0,nums1.length-1);
    }

    public void quickSort(int[] nums,int p, int q) {
        if (p >= q) {
            return;
        }
        int mid = partition(nums, p, q);
        quickSort(nums,p,mid-1);
        quickSort(nums,mid+1,q);
    }

    private int partition(int[] nums, int p, int q) {
        int filot = nums[q];
        int low = p-1;
        int high = p;
        while (high < q){
            if (nums[high] < filot) {
               swap(nums,++low,high);
            }
            high++;
        }
        swap(nums,++low,q);
        return low;
    }

    public void swap(int[] nums,int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        if (nums1 == null || nums2 == null) return;
        int p = m-- + n-- + 1;
        while (m >= 0 && n>= 0) {
            nums1[p--] = nums1[m] < nums2[n]?nums2[n--]:nums1[m--]; // copy largest value in every time
        }
        while (n >= 0){
            nums1[p--] = nums2[n--];
        }
    }


    public static void main(String[] args) {
        int[] ints = new int[]{4,6,8,9,3,2,7};
        new Solution88().quickSort(ints,0,ints.length-1);
        for (int i = 0; i <ints.length; i++) {
            System.out.println(ints[i]);
        }
    }
}