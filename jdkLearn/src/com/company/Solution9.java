package com.company;

public class Solution9 {

    /**
     * O(n) 11ms 37.8m
     * @param x
     * @return
     */
    public boolean isPalindrome1(int x) {
        char[] chars = Integer.toString(x).toCharArray();
        int i = 0, y = chars.length -1;
        while (i < y) {
            if (chars[i] != chars[y]) {
                return false;
            }
            i++;
            y--;
        }
        return true;
    }

    public boolean isPalindrome2(int x) {
        if (x<0||(x!=0&&x%10==0)) {
            return false;
        }
        int reverseNum = 0;
        while (x > reverseNum) {
            reverseNum = reverseNum*10 + x%10;
            x = x/10;
        }
        return reverseNum == x || reverseNum/10 == x;
    }



    public static void main(String[] args) {
        Solution9 solution9 = new Solution9();
        System.out.println(solution9.isPalindrome2(0));


    }
}