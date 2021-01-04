package com.company;


import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * solution 1 & 2 is dp tc--> O(n2) space --> O(n)
 * f(n) = Math.max(dp(n),s(n-1)+1) if v(n) > v(n-1)
 */
public class Solution300 {

    public int lengthOfLIS1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] maxValues = new int[n];
        int[] lengthNums = new int[n];
        // init
        for (int i = 0; i < n; i++) {
            maxValues[i] = nums[i];
            lengthNums[i] = 1;
        }

        //start
        for (int i = 1; i < n; i++) {
            int curVal = nums[i];
            for (int j = i-1; j >= 0; j--) {
                int curMaxVal = maxValues[j];
                if (curVal > curMaxVal && lengthNums[i] < lengthNums[j] + 1) {
                    lengthNums[i] = lengthNums[j] + 1;
                }
            }
        }
        int maxLength = lengthNums[0];
        for (int i = 1; i < n; i++) {
            if (maxLength < lengthNums[i])
                maxLength = lengthNums[i];
        }

        return maxLength;
    }

    public int lengthOfLIS2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }

    public int lengthOfLIS3(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] maxValues = new int[n];
        int[] lengthNums = new int[n];


        return maxLength;
    }

    public static void main(String[] args) {
        // [10,9,2,5,3,7,101,18]

        int[] arrays = new int[]{10,9,2,5,3,7,101,18};
        System.out.println(new Solution300().lengthOfLIS2(arrays));

    }
}