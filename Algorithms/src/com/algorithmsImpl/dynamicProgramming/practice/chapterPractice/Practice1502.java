package com.algorithmsImpl.dynamicProgramming.practice.chapterPractice;

import com.algorithmsImpl.dynamicProgramming.practice.Practice150502;

public class Practice1502 {
    /**
     * LEFT: node value is from its left node
     * DOWN: node value is from its bottom node
     * ME: node is one of palindrome center node
     */
    public enum Direction{LEFT,DOWN,ME}

    private static void longest_palindrome_sub_array(char[] arr,int n){
        int[][] results=new int[n][n];
        String[][] direction=new String[n][n];

        //1. init when it has only one char
        for (int i = 0; i < n; i++) {
            results[i][i]=1;
            direction[i][i]=String.valueOf(Direction.ME);
        }

        //2. computing
        for (int l = 1; l < n; l++) {   // round times
            for (int i = 0; i < n - l; i++) {   //node numbers in every round
                // computing j
                int j=i+l;
                if (arr[i]==arr[j]){
                    results[i][j]=results[i+1][j-1]+2;
                    direction[i][j]= String.valueOf(Direction.ME);
                }else{
                    //results[i][j]=Math.max(results[i][j-1],results[i+1][j]);
                    if (results[i][j-1]>=results[i+1][j]){
                        results[i][j]=results[i][j-1];
                        direction[i][j]= String.valueOf(Direction.LEFT);
                    }else{
                        results[i][j]=results[i+1][j];
                        direction[i][j]= String.valueOf(Direction.DOWN);
                    }
                }
            }
        }

        //3. print out longest palindrome sub_array
        print_longest_palindrome_sub_array(direction,0,n-1,arr);

//        System.out.println();
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(results[i][j]+" ");
//            }
//            System.out.println();
//        }
    }

    private static void print_longest_palindrome_sub_array(String[][] dir,int i,int j,char[] chars){
        if (i<j){
            if (dir[i][j].equals(String.valueOf(Direction.ME))){
                //print
                System.out.print(chars[i]+"-->");
                print_longest_palindrome_sub_array(dir,(i+1),(j-1),chars);
                System.out.print(chars[j]+"-->");
            }else if (dir[i][j].equals(String.valueOf(Direction.LEFT))){
                print_longest_palindrome_sub_array(dir,i,(j-1),chars);
            }else{
                print_longest_palindrome_sub_array(dir,(i+1),j,chars);
            }
        }else if (i==j){
            System.out.print(chars[i]+"-->");
        }

    }

    public static void main(String[] args) {
        String s="character";
//        String s="barac";
        char[] c=s.toCharArray();
        Practice1502.longest_palindrome_sub_array(c,c.length);
    }
}
