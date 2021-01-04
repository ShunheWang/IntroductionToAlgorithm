package com.company;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * stack 处理
 */

public class Solution20 {

    public boolean isValid(String s) {
        if (s == null || s == "") {
            return false;
        }
        char[] chars = s.toCharArray();
        if (chars[0] == ')' || chars[0] == ']' || chars[0] == '}') {
            return false;
        }
        Deque deque = new ArrayDeque();
        for (char c : chars) {
            if (c == '(' || c == '[' || c == '{') {
                deque.push(c);
            }else if (deque.isEmpty()||c!= getReverseSignal((char)deque.poll())) {
                    return false;
            }
        }
        return deque.isEmpty()?true:false;
    }

    private char getReverseSignal(char c) {
        return c=='('?')':(c=='['?']':'}');
    }

    public boolean isValid1(String s) {
        Stack<Character>stack = new Stack<Character>();
        for(char c: s.toCharArray()){
            if(c=='(')stack.push(')');
            else if(c=='[')stack.push(']');
            else if(c=='{')stack.push('}');
            else if(stack.isEmpty()||c!=stack.pop())return false;
        }
        return stack.isEmpty();
    }


    public static void main(String[] args) {
        String s = "()}{";
        System.out.println(new Solution20().isValid(s));
        //System.out.println(new Solution20().isValid1(s));
    }
}