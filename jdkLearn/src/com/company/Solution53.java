package com.company;
import java.util.HashMap;

public class Solution53 {


    /**
     * dp 可解 极其麻烦 O(n3) + tc (for finding max value from 2d_array)
     * @param nums
     * @return
     */
    public int[][] maxSubArray1(int[] nums) {
        int n = nums.length;
        int[][] maxValues = new int[n][n];
        // init
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maxValues[i][j] = Integer.MIN_VALUE;
            }
        }

        for (int i = 0; i < n; i++) {
            maxValues[i][i] = nums[i];
        }
        // start computing
        for (int l = 1; l < n; l++) {
            for (int i = 0; i < n - l; i++) {
                int j = i + l;
                for (int k = j; k > i; k--) {
                    if ((maxValues[i][k-1] + maxValues[k][j]) > maxValues[i][j]) {
                        maxValues[i][j] = maxValues[i][k-1] + maxValues[k][j];
                    }
                }
            }
        }
        return maxValues;
    }

    /**
     * dp also
     * d-function: f(i) == max[(f(i-1)+v(i),v(i))]
     * e.x. f(8) == max[(f(7)+v(8),f(8))], v(7) == max[(f(6)+v(7),v(7))]
     * @param nums
     * @return
     */
    public int maxSubArray2(int[] nums) {
        int n = nums.length;
        int[] recMax = new int[n];
        // init first maxValue
        recMax[0] = nums[0];
        int maxValue = recMax[0]; // record current point of max value
        // start with d-function
        for (int i = 1; i < n; i++) {
            recMax[i] = Math.max((recMax[i-1]+nums[i]),nums[i]);
            maxValue = maxValue > recMax[i]?maxValue: recMax[i]; // --> track max value
        }
        return maxValue;
    }

    public static void main(String[] args) {
        // [-2,1,-3,4,-1,2,1,-5,4]
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.print(new Solution53().maxSubArray2(nums));
    }
}