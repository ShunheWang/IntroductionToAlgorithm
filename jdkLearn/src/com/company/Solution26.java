package com.company;


public class Solution26 {

    public int removeDuplicates(int[] nums) {
        if (nums == null) return 0;
        if (nums.length == 0) return 0;
        int p = 0;
        for (int q = 1; q < nums.length; q++) {
            if (nums[p] != nums[q]) {
                p = p + 1;
                nums[p] = nums[q];
            }
        }
        return p+1;
    }

    public static void main(String[] args) {


    }
}