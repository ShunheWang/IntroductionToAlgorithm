package com.company;

public class Solution58 {

    public int lengthOfLastWord(String s) {
        if (s == null) return 0;
        if (s.length() == 0) return 0;
        String s1 = s.trim();
        for (int i = s1.length()-2; i >= 0; i--) {
            if (s1.charAt(i) == ' ') return (s1.length()-1-i);
        }
        return s1.length();
    }

    public static void main(String[] args) {
        System.out.println(new Solution58().lengthOfLastWord("a"));
    }
}