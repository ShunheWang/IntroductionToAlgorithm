package com.company;

/**
 * romanToInt1 计算的是 IV = 4, IX = 9; IL = 40, IC = 90; ID = 400, IM = 900;
 * romanToInt2 为根据题意 --> I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 *                          X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 *                          C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。

 */

public class Solution13 {

    public int romanToInt1(String s) {
        int sum = 0;
        char[] chars = s.toCharArray();
        int track;
        char firstChar = chars[chars.length - 1];// first value
        sum += getValue(firstChar);
        track = getValue(firstChar);
        for (int j = chars.length-2; j >= 0; j--) {
            int value = getValue(chars[j]);
            if (value != 1) {
                sum += value;
            }else{
                if (track == 1) {
                    sum += value;
                }else{
                    // compute negative number e.x. ID --> 500 - 100
                    int n = 0;
                    int negative = 1;
                    while (track/10 > 1) {
                        n++;
                        track /= 10;
                    }
                    for (int k = 0; k < n; k++) {
                        negative *= 10;
                    }
                    sum -= negative;
                }
            }
            track = value;
        }
        return sum;
    }

    public int romanToInt2(String s) {
        int sum = 0;
        char[] chars = s.toCharArray();
        int track;
        char firstChar = chars[chars.length - 1];// first value
        sum += getValue(firstChar);
        track = getValue(firstChar);
        for (int j = chars.length-2; j >= 0; j--) {
            int value = getValue(chars[j]);
            sum = value < track? sum - value: sum + value;
            track = value;
        }
        return sum;
    }


    public int getValue(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
        }
        return 0;
    }


    public static void main(String[] args) {
        String string = "MCMXCIV";
        System.out.println(new Solution13().romanToInt2(string));
    }
}