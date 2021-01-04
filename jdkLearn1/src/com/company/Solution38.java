package com.company;


public class Solution38 {

    public String countAndSay(int n) {
        if (n == 0) return "";
        StringBuilder prevCount = new StringBuilder();
        prevCount.append(1);
        StringBuilder curCount = prevCount;
        for (int i = 0; i < n-1; i++) {
            curCount = new StringBuilder();
            char[] chars = prevCount.toString().toCharArray();
            int j, k;
            for (j = 0; j < chars.length; j++) {
                char curVal = chars[j];
                k = (j+1) < chars.length? (j+1): -1;
                int rec = 1;
                while (k != -1 && chars[k] == curVal) {
                    rec++;
                    k = (k+1) < chars.length? (k+1): -1;
                }
                if (rec == 1) {
                    curCount.append('1');
                    curCount.append(chars[j]);
                }else {
                    curCount.append(rec);
                    curCount.append(chars[j]);
                    j = j + rec -1;
                }
            }
            prevCount = curCount;
        }
        return curCount.toString();
    }

    public static void main(String[] args) {
        //[1,3,5,6]
        //2
        System.out.println(new Solution38().countAndSay(4));
    }
}