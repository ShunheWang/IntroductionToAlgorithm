package com.company;

public class Solution66 {

    public int[] plusOne1(int[] digits) {
        if (digits == null) return new int[]{0};
        StringBuilder sb = new StringBuilder();
        int incr = 1;
        for (int i = digits.length-1; i >= 0; i--) {
            int curDigital = (digits[i] + incr) % 10;
            incr = (digits[i] + incr)/10;
            sb.append(curDigital);
        }
        // final digital
        if (incr == 1) sb.append(1);
        StringBuilder reverseSb = sb.reverse();
        int n = reverseSb.length();
        int[] results = new int[n];
        for (int i = 0; i < n; i++) {
            results[i] = Integer.parseInt(String.valueOf(reverseSb.charAt(i)));
        }
        return results;
    }


    public int[] plusOne2(int[] digits) {
        if (digits == null) return new int[]{0};
        int i;
        for (i = digits.length-1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i]%10;
            if (digits[i]!=0) return digits;
        }
        if (digits[i+1] ==0){ // e.x. [9,9,9] --> [1,0,0,0]
            digits = new int[digits.length+1];
            digits[0] = 1;
        }
        return digits;
    }

    public static void main(String[] args) {
        //[1,2,3]
        int[] inputs = new int[]{9,9,9};
        int[] outputs = (new Solution66()).plusOne2(inputs);
        for (int i = 0; i < outputs.length; i++) {
            System.out.println(outputs[i]);
        }

    }
}