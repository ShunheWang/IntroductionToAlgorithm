package com.company;


public class Solution27 {

    public int removeElement(int[] nums, int val) {
        if (nums == null) return 0;
        if (nums.length == 0) return 0;
        int p = nums.length;
        for (int q = nums.length-1; q >= 0; q--) {
            if (nums[q] == val) {
                p = p - 1;
                // swap
                swap(nums, p, q);
            }
        }
        return p;
    }

    public void swap(int[] nums, int x, int y) {
        int t = nums[x];
        nums[x] = nums[y];
        nums[y] = t;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,2,2,3};
        int val = 3;
        System.out.println(new Solution27().removeElement(nums,3));
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }

    }
}