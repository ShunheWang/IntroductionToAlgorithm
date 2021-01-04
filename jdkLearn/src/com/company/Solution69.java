package com.company;

import java.awt.image.BandedSampleModel;

public class Solution69 {

    /**
     * Binary search
     * O(logn)
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if (x <= 0) return 0;
        int p = 0, r = x, res = 0;
        while (p <= r) {
            int mid = (r - p) / 2 + p;
            if ((long)mid * mid <= x) {
                res = mid;
                p = mid + 1;
            }else {
                r = mid -1;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(new Solution69().mySqrt(8));
    }
}