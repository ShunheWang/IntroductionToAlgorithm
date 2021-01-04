package com.algorithmsImpl.dynamicProgramming;

/**
 * Optimal Binary Search Tree
 */

/**
 * p array: high searching frequency node
 * q array: low searching frequency node
 * n: length
 */
public class OptimalBST {
    private static void optimal_bst(double[] p, double[] q, int n){
        //1. create 3 2d_array to store result, sub_tree frequency and root
        double[][] e=new double[n+1][n+1];
        double[][] w=new double[n+1][n+1];
        int[][] r=new int[n+1][n+1];

        //2. only has non-key words' nodes
        for (int i = 0; i < n+1; i++) {
            e[i][i]=q[i];
            w[i][i]=q[i];
        }

        //3. start to compute frequency when j>=i which means has key words
        for (int l = 1; l <= n; l++) {   //computing times
            for (int i = 0; i <= n - l; i++) {   //node numbers in every computing time
                int j=l+i;
                //computing w node
                w[i][j]=w[i][j-1]+q[j]+p[j-1];
                //init e node
                e[i][j]=Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    double t=e[i][k]+e[k+1][j]+w[i][j];
                    if (t<e[i][j]){
                        e[i][j]=t;
                        r[i][j]=k;
                    }
                }
            }
        }

        printArray(e);
        printArray(w);
        printArray(r);

        //4. print bst construct
        //construct_optimal_bst(r,0,5,2);
    }

    /**
     *
     * @param root
     * @param i
     * @param j
     * @param direction 0: left, 1: right, 2: root
     */
    private static void construct_optimal_bst(int[][] root, int i, int j, int direction) {
        if (i==j){
            if (direction==0){
                System.out.println("d"+root[i][j]+" is k"+j+" left child");
            }else{
                System.out.println("d"+root[i][j]+" is k"+j+" right child");
            }
            return;
        }else if(i<=j){
            if (direction==2){
                System.out.println("k"+(root[i][j])+" is root");
                construct_optimal_bst(root,i,root[i][j]-1,1);
                construct_optimal_bst(root,root[i][j],j,1);
            }else if (direction==1){
                System.out.println("k"+root[i][j]+" is k"+(j+1)+" left child");
                construct_optimal_bst(root,i,root[i][j]-1,0);
                construct_optimal_bst(root,root[i][j],j,1);
            }else{
                //direction==1
                System.out.println("k"+root[i][j]+" is k"+j+" right child");
            }
        }
    }

    private static void printArray(double[][] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(String.format("%.2f", arr[i][j]) + " ");
            }
            System.out.println();
        }
    }

    private static void printArray(int[][] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        double[] p=new double[]{0.15,0.10,0.05,0.10,0.20};
        double[] q=new double[]{0.05,0.10,0.05,0.05,0.05,0.10};
        OptimalBST optimalBST=new OptimalBST();
        OptimalBST.optimal_bst(p,q,p.length);

    }
}
