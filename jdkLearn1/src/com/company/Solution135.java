package com.company;


public class Solution135 {

    public int candy(int[] ratings) {
        // at least get 1 candy for everyone --> thus init
        int[] candys = new int[ratings.length];
        for (int i = 0; i < candys.length; i++) {
            candys[i] = 1;
        }
        // add extra by condition provided from left to right
        for (int i = 1; i < candys.length; i++) {
            if (ratings[i] > ratings[i-1]) candys[i] = candys[i-1]+1;
        }
        // add extra by condition provided from right to left
        // & sum
        int sum = candys[candys.length-1];
        for (int i = candys.length-2; i >= 0; i--) {
            if (ratings[i] > ratings[i+1] && candys[i] <= candys[i+1]) candys[i] = candys[i+1]+1;
            sum += candys[i];
        }
        return sum;
    }


    public static void main(String[] args) {
        //  [1,0,2]
        int[] rantings = new int[]{1,0,2};
        System.out.println(new Solution135().candy(rantings));


    }
}