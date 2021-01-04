package com.algorithmsImpl.dynamicProgramming.practice;

/**
 *practice 15.5-2 p231 in book
 */
public class Practice150502 {

    public enum Position{LEFT,RIGHT,ROOT};

    private static void optimal_bst(double[] p,double[] q,int n){
        System.out.println(n);
        double[][] e=new double[n+1][n];
        double[][] w=new double[n+1][n];
        int [][] root=new int[n][n];
        //non key word
        for (int i = 1; i < e.length; i++) {
            e[i][i-1]=q[i-1];
            w[i][i-1]=q[i-1];
        }

        //computing
        for (int l = 1; l < n; l++) { // 7 rounds
            for (int i = 1; i < n+1-l; i++) { // row number in each round
                int j=i+l-1;
                w[i][j]=w[i][j-1]+q[j]+p[j-1];
                e[i][j]=Integer.MAX_VALUE;
                for (int k = i; k < j+1; k++) {
                    double t=e[i][k-1]+e[k+1][j]+w[i][j];
                    if (t<e[i][j]){
                        e[i][j]=t;
                        root[i][j]=k;
                    }
                }
            }
        }

        for (int i = 0; i < e.length; i++) {
            for (int j = 0; j < e[i].length; j++) {
                System.out.print(String.format("%.2f", e[i][j]) + " ");
            }
            System.out.println();
        }

//        System.out.println();
//        for (int i = 0; i < w.length; i++) {
//            for (int j = 0; j < w[i].length; j++) {
//                System.out.print(String.format("%.2f", w[i][j]) + " ");
//            }
//            System.out.println();
//        }

        System.out.println();
        for (int i = 0; i < root.length; i++) {
            for (int j = 0; j < root[i].length; j++) {
                System.out.print(root[i][j] + " ");
            }
            System.out.println();
        }

        construct_optimal_bst(root,1,root.length-1,Position.ROOT);
    }

    private static void construct_optimal_bst(int[][] root,int i,int j,Position para){
        if(i<=j){
            if (para==Position.LEFT){
                System.out.println("k"+root[i][j]+" is k"+(j+1)+" left child");
//                System.out.println(i);
//                System.out.println(j);
            }else if(para==Position.RIGHT){
                System.out.println("k"+root[i][j]+" is k"+(i-1)+" right child");
            }else{
                System.out.println("k"+root[i][j]+" is a root");
            }
            construct_optimal_bst(root,i,(root[i][j]-1),Position.LEFT);
            construct_optimal_bst(root,(root[i][j]+1),j,Position.RIGHT);
        }else if (i==(j+1)){
            if (para==Position.LEFT) {
                System.out.println("d" + (i - 1) + " is k" + i + " left child");
            }else if (para==Position.RIGHT) {
                System.out.println("d" + j + " is k" + j + " right child");
            }
        }

    }

    public static void main(String[] args) {
        double[] p=new double[]{0.04,0.06,0.08,0.02,0.10,0.12,0.14};
        double[] q=new double[]{0.06,0.06,0.06,0.06,0.05,0.05,0.05,0.05};
        Practice150502.optimal_bst(p,q,q.length);
    }
}
