package com.company;
import javax.sound.midi.SysexMessage;
import java.util.HashMap;

public class Solution7 {



    /**
     * 3ms
     * @param x
     * @return
     */
    public int reverse1(int x) {
        String xString = Integer.toString(x);
        String string = xString;
        int flag = 1;
        if (x < 0) {
            flag = -1;
            string = xString.substring(1);
        }
        try {
            return Integer.valueOf((new StringBuilder(string)).reverse().toString()) * flag;
        }catch (Exception e){
            return 0;
        }

    }

    public int reverse2(int x) {
        long res = 0;
        while (x != 0) {
            res = res*10 + x%10;
            x = x/10;
        }
        return (int)res == res ? (int)res:0;
    }

    public static void main(String[] args) {
        Solution7 solution7 = new Solution7();
        System.out.print(solution7.reverse2(1534236469));

    }
}