package com.algorithmsImpl.divideAndConquer;

public class TwoDigitalArrayMutipleProblem {

    public static int[][] twoDigArrayMutipleByDAC(int[][]a, int[][] b,int n){
        int[][] c=new int[n][n];

        if (n==1) {
            c[0][0]=a[0][0]*b[0][0];
            return c;
        }

        else{
            //divide
            int mid=n/2;
            //1 recode sub array of A
            int[][] A11=copySubArray(a,0,0,mid);
            int[][] A12=copySubArray(a,0,mid,mid);
            int[][] A21=copySubArray(a,mid,0,mid);
            int[][] A22=copySubArray(a,mid,mid,mid);

//            printMartins(A1);
//            printMartins(A2);
//            printMartins(A3);
//            printMartins(A4);

            //2 record sub array of B
            int[][] B11=copySubArray(a,0,0,mid);
            int[][] B12=copySubArray(a,0,mid,mid);
            int[][] B21=copySubArray(a,mid,0,mid);
            int[][] B22=copySubArray(a,mid,mid,mid);

            //3 divide dest array called c.
            int[][] C11=new int[mid][mid];
            int[][] C12=new int[mid][mid];
            int[][] C21=new int[mid][mid];
            int[][] C22=new int[mid][mid];


            //conquer
            //1. handle every sub matrix
            conquerMatrix(twoDigArrayMutipleByDAC(A11,B11,mid),twoDigArrayMutipleByDAC(A12,B21,mid),C11);
            conquerMatrix(twoDigArrayMutipleByDAC(A11,B12,mid),twoDigArrayMutipleByDAC(A12,B22,mid),C12);
            conquerMatrix(twoDigArrayMutipleByDAC(A21,B11,mid),twoDigArrayMutipleByDAC(A22,B21,mid),C21);
            conquerMatrix(twoDigArrayMutipleByDAC(A21,B12,mid),twoDigArrayMutipleByDAC(A22,B22,mid),C22);


            //2. gather all sub matrix data into main matrix
            copySubMatrixToMainMatrix(c,0,0,C11);
            copySubMatrixToMainMatrix(c,0,mid,C12);
            copySubMatrixToMainMatrix(c,mid,0,C21);
            copySubMatrixToMainMatrix(c,mid,mid,C22);

        }

        return c;
    }

    public static void copySubMatrixToMainMatrix(int destMatrix[][], int startx, int starty,
                                      int srcMatrix[][]) {
        int n = srcMatrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                destMatrix[startx+i][starty+j] = srcMatrix[i][j];
            }
        }
    }


    private static void conquerMatrix(int[][] a,int [][]b,int[][] dest){
        for (int i = 0; i < dest.length; i++) {
            for (int j = 0; j < dest.length; j++) {
                dest[i][j]=a[i][j]+b[i][j];
            }
        }
    }


    private static void printMartins(int[][] test){
        for (int i = 0; i < test.length; i++) {
            for (int j = 0; j < test.length; j++) {
                System.out.print(test[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static int[][] copySubArray(int[][] ori,int startx,int starty,int n){
        int[][] dest=new int[n][n];
        for(int i=0;i<dest.length;i++){
            for(int j=0;j<dest.length;j++){
                dest[i][j]=ori[i+startx][j+starty];
            }
        }

        return dest;
    }
}
