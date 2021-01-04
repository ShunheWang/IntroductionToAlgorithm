package com.company;

public class Solution67 {

    public String addBinary1(String a, String b) {
        if (a == null || a.length() == 0) return b;
        if (b == null || b.length() == 0) return a;
        //start
        char[] aChar = a.toCharArray();
        char[] bChar = b.toCharArray();
        int incr = 0;
        if (aChar.length < bChar.length) {
            char[] t = new char[bChar.length];
            for (int i = 0; i < t.length; i++) {
                t[i] = '0';
            }
            System.arraycopy(aChar,0,t,bChar.length-aChar.length,aChar.length);
            aChar = t;
        }else if (aChar.length > bChar.length) {
            char[] t = new char[aChar.length];
            for (int i = 0; i < t.length; i++) {
                t[i] = '0';
            }
            System.arraycopy(bChar,0,t,aChar.length-bChar.length,bChar.length);
            bChar = t;
        }
        for (int i = aChar.length-1; i >= 0; i--) {
            int aVal = Integer.parseInt(String.valueOf(aChar[i]));
            int bVal = Integer.parseInt(String.valueOf(bChar[i]));
            aChar[i] = (char) (((aVal + incr + bVal)%2)+'0');
            incr = ((aVal+bVal+incr)/2);
        }
        String res = String.valueOf(aChar);
        return incr == 1?"1"+res:res;
    }

    public String addBinary2(String a, String b) {
        StringBuffer ans = new StringBuffer();

        int n = Math.max(a.length(), b.length()), carry = 0;
        for (int i = 0; i < n; ++i) {
            carry += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0;
            carry += i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;
            ans.append((char) (carry % 2 + '0'));
            carry /= 2;
        }

        if (carry > 0) {
            ans.append('1');
        }
        ans.reverse();

        return ans.toString();
    }

    public String addBinary3(String a, String b) {
        char[] aChars = a.toCharArray();
        char[] bChars = b.toCharArray();
        int n = Math.max(a.length(), b.length()), carry = 0;
        char[] maxChars = aChars.length<bChars.length?bChars:aChars;
        for (int i = 0; i < n; ++i) {
            int aVal = i < aChars.length ? (aChars[a.length() - 1 - i] - '0') : 0;
            int bVal = i < bChars.length ? (bChars[b.length() - 1 - i] - '0') : 0;
            maxChars[n - 1 - i] = (char) ((aVal + bVal + carry) % 2+ '0');
            /**  (aVal + bVal + carry) % 2 + '0'
             *    --> 0 + '0' = 48 --> char --> '0'
             *    --> 1 + '0' = 1 + 48 = 49 --> char --> '1'
             */
            carry = (aVal + bVal + carry) / 2;
        }
        return carry == 1?"1"+String.valueOf(maxChars):String.valueOf(maxChars);
    }

    public static void main(String[] args) {
        //a = "11", b = "1"
        String a = "11";
        String b = "1";
        System.out.println(new Solution67().addBinary3(a,b));

        char ascii0 = '0'; // 48
        System.out.println(ascii0 + 0); // 48

        char ascii1 = '1'; //49
        System.out.println(ascii1 + 0); // 48

        System.out.println(1+'1'); // 50
        System.out.println(1-'1'); // -48

        System.out.println(1+'0'); // 49
        System.out.println(1-'0'); // -47

    }
}