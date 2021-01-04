package com.algorithmsImpl.greedy;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * 16.1
 */
public class ActivityChoiceProblem {

    /**
     * it has added two fake node (node 0 and node max value)
     * solution method； dp
     * @param s
     * @param f
     * @param n
     */
    private static void max_choice_by_dp(int[] s,int[] f,int n){
        int[][] c=new int[n][n];
        int[][] ks=new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                c[i][j]=0;
            }
        }

        for (int l = 1; l < n; l++) {
            for (int i = 0; i < n-l; i++) {
                int j = i+l;
                int temp=0;
                for (int k = i+1; k < j; k++) {
                    if (s[k]>=f[i]&&f[k]<=s[j]) {
                        if(c[i][k] + c[k][j] + 1 > temp){
                            temp=c[i][k]+c[k][j]+1;
                            ks[i][j]=k;
                        }
                    }
                }
                c[i][j]=temp;
            }
        }
        print_max_activity_number_dp(c,0,c.length-1);
        print_activity_dp(ks,0,ks.length-1);
    }

    private static void print_max_activity_number_dp(int[][] c, int i, int j){
        System.out.println("max activity number --> "+c[i][j]);
    }

    private static void print_activity_dp(int[][] ks, int i, int j){
        if (i<j&&ks[i][j]!=0){
            System.out.print("[activity "+ks[i][j]+"] ");
            print_activity_dp(ks,i,ks[i][j]);
            print_activity_dp(ks,ks[i][j],j);
        }

    }

    private static int max_choice_by_greedy_recursive(ArrayList<Integer> results,int num, int[] s, int[] f, int i, int j){
        int next=i+1;
        while (next<j&&f[i]>s[next]){
            next++;
            num++;
        }//find next fitting node
        if (next<j){
            results.add(next);
            max_choice_by_greedy_recursive(results,num,s,f,next,j);
        }
        return num;
    }

    private static ArrayList<Integer> max_choice_by_greedy_iterator(int[] s, int[] f, int i, int j,int n){
        int k=1;
        ArrayList<Integer> list=new ArrayList<Integer>();
        list.add(1);
        for (int t = 2; t < n; t++) {
            if (s[t]>=f[k]){
                list.add(t);
                k=t;
            }
        }
        return list;
    }

    private static void print_results_greedy(ArrayList<Integer> list){
        for (int node:
             list) {
            System.out.print("[activity" + node + "]");
        }
    }

    public static void main(String[] args) {
        //add a(0) & a(n+1) activity. 其中s(0)=f(0)=0, s(n+1)=f(n+1)=Integer.MAX_VALUE
        int[] s = {0,1,3,0,5,3,5,6,8,8,2,12,Integer.MAX_VALUE};//start time
        int[] f = {0,4,5,6,7,8,9,10,11,12,13,14,Integer.MAX_VALUE};//finish time
        //dp
        max_choice_by_dp(s,f,s.length);
        System.out.println();
        //greedy
        ArrayList<Integer> list=new ArrayList<Integer>();
        int max_number=max_choice_by_greedy_recursive(list,0,s,f,0,s.length-1);
        System.out.println("max number(recursive) --> "+max_number);
        print_results_greedy(list);
        System.out.println();
        ArrayList<Integer> resultList=max_choice_by_greedy_iterator(s,f,0,s.length-1,s.length-1);
        System.out.println("max number(iterator) --> "+resultList.size());
        print_results_greedy(resultList);
    }



}
