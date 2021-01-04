package com.company;


public class Solution35 {

    public int searchInsert(int[] nums, int target) {
        if (nums == null) return 0;
        if (nums.length == 0) return 0;
        int low = 0, highest = nums.length-1;
        while (low <= highest) { // IMPORTANT --> pay more attention '<='
            int mid = (highest-low)/2+low;
            if (nums[mid] == target) {
                return mid;
            }else if (nums[mid] < target) {
                low = mid+1;
            }else {
                highest = mid-1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        //[1,3,5,6]
        //2
        int[] nums = {1,3,5,6};
        int val = 2;
        System.out.println(new Solution35().searchInsert(nums,val));
    }
}