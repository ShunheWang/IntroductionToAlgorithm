package com.company;

public class Solution70 {

    /**
     * dp f(n) = f(n-1) + f(n-2)
     * O(n)
     * @return
     */
    public int climbStairs(int n) {
        if (n <= 0) return 0;
        int p = 0, q = 1, r = 0;
        for (int i = 1; i <= n; i++) {
            r = p + q;
            p = q;
            q = r;
        }
        return r;
    }


    public static void main(String[] args) {

    }
}