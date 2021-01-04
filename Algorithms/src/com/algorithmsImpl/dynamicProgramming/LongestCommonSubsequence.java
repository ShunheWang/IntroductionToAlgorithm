package com.algorithmsImpl.dynamicProgramming;

/**
 * longest common subsequence problem
 */
public class LongestCommonSubsequence {
    private final int TOP=0;
    private final int LEFT=1;
    private final int TOP_LEFT=2;

    private void lcsLength(char[] a,char[] b){
        int row=a.length+1;
        int col=b.length+1;
        //1. create new array to store length
        int[][] lens=new int[row][col];
        int[][] c=new int[row][col];
        //2. init branch
        for (int i = 1; i < row; i++) {
            lens[i][0]=0;
        }
        for (int j = 0; j < col; j++) {
            lens[0][j]=0;
        }
        //3. computing lcs
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (a[i-1]==b[j-1]) {
                    lens[i][j]=lens[i-1][j-1]+1;
                    c[i][j]=TOP_LEFT;
                }else if(lens[i-1][j]>=lens[i][j-1]){
                    lens[i][j]=lens[i-1][j];
                    c[i][j]=TOP;
                }else{
                    lens[i][j]=lens[i][j-1];
                    c[i][j]=LEFT;
                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(lens[i][j]+" ");
            }
            System.out.println();
        }
        //4. print lcs counts
        lcs_counts(lens[row-1][col-1]);
        //5. print path by recursive method
        print_lcs1(c,a,row-1,col-1);
        System.out.println();
        print_lcs2(lens,a,row-1,col-1);
    }

    //print out with nothing
    private void print_lcs2(int[][] lens,char[] x,int i,int j){
        if (i==0||j==0) {
            return;
        }




        if (lens[i][j]==lens[i-1][j]){
            print_lcs2(lens,x,i-1,j);
        }else if (lens[i][j]==lens[i][j-1]){
            print_lcs2(lens,x,i,j-1);
        }else{
            print_lcs2(lens,x,i-1,j-1);
            System.out.print(x[i-1]);
        }
    }


    //print out with extra array(c[])
    private void print_lcs1(int[][] c,char[] x,int i,int j){
        if (i==0||j==0) {
            return;
        }
        if (c[i][j]==TOP_LEFT) {
            print_lcs1(c,x,i-1,j-1);
            System.out.print(x[i-1]+" ");
        }else if(c[i][j]==TOP){
            print_lcs1(c,x,i-1,j);
        }else{
            print_lcs1(c,x,i,j-1);
        }
    }

    private void lcs_counts(int count){
        System.out.println("lcs_counts --> "+count);
    }

    public static void main(String[] args) {
        String x= "ABCBDAB";
        String y = "BDCABA";
//        String x= "ACCGGTCGAGTGCGCGGAAGCCGGCCGAA";
//        String y = "GTCGTTCGGAATGCCGTTGCTCTGTAAA";
        char[] a=x.toCharArray();
        char[] b=y.toCharArray();
        LongestCommonSubsequence longestCommonSubsequence=new LongestCommonSubsequence();
        longestCommonSubsequence.lcsLength(a,b);
    }
}
