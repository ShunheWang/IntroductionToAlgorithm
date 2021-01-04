package com.company;

/**
 * hard
 */
public class Solution4 {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null && nums2 == null) return 0;
        int m = nums1.length;
        int n = nums2.length;

        int k = (m + n + 1) / 2;
        int left1 = 0;
        int right1 = nums1.length-1;
        int left2 = 0;
        int right2 = nums2.length-1;


        while (k > 0) {
           int curMid = k/2 - 1;
           if (nums1[curMid + left1] <= nums2[curMid + left2]) {
               left1 = curMid - 1;
           }else {
               left2 = curMid - 1;
           }
            k = k - (curMid + 1);
        }
        return 2;
    }


    public static void main(String[] args) {
        //  [1,0,2]
        int[] rantings = new int[]{1,0,2};



    }
}