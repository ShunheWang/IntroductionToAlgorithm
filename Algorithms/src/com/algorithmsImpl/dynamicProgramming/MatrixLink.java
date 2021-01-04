package com.algorithmsImpl.dynamicProgramming;

public class MatrixLink {

    //method 1: normal multiply
    private int[][] matrix_multiply1(int[][] matrixA,int[][] matrixB){
        //rows and columns handle
        int rowsA=matrixA.length;
        int columnsA=matrixA[0].length;

        int rowsB=matrixB.length;
        int columnsB=matrixB[0].length;

        //1. columnsA != rowsB --> return error
        if (columnsA!=rowsB) {
            return null;
        }

        //2. create new 2d-array to store result
        int[][] matrixC=new int[rowsA][columnsB];

        //3. computing multiply of matrix A & B
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < columnsB; j++) {
                matrixC[i][j]=0;
                for (int k = 0; k < columnsB; k++) {
                    matrixC[i][j]=matrixC[i][j]+matrixA[i][k]*matrixB[k][j];
                }
            }
        }

        return matrixC;
    }

    //method 2: multiply by dp
    private void matrix_multiply_by_DP(int[][][] sMatrix){
        int n=sMatrix.length-1;
        int[][] m=new int[n+1][n+1];
        int[][] s=new int[n+1][n+1];

        //1. only one matrix
        for (int i = 0; i < n; i++) {
            m[i][i]=0;
        }

        //2. over 2 matrix
        for (int l = 1; l <=n; l++) { //computing times
            for (int i = 0; i <=n-l; i++) { //node numbers in every computing times (first time: 5 nodes, second time: 4 nodes, ...)
                //computing column of each node
                int j=i+l;
                //create m node
                m[i][j]=Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    //0 --> rows, 1 --> columns
                    int q=m[i][k]+m[k+1][j]+getLen(i,0,sMatrix)*getLen(k+1,0,sMatrix)*getLen(j,1,sMatrix);
                    if (q<m[i][j]){
                        m[i][j]=q;
                        s[i][j]=k;
                    }
                }
            }
        }

        printMinimumComputingTimes(m);
        printOptimalSolution(s,0,5);

    }

    private void printOptimalSolution(int[][] solution,int i,int j){
        if (i==j){
            System.out.print("A"+i);
        }else{
            System.out.print("(");
            printOptimalSolution(solution,i,solution[i][j]);
            printOptimalSolution(solution,solution[i][j]+1,j);
            System.out.print(")");
        }
    }

    private void printMinimumComputingTimes(int[][] result){
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j]+" ");
            }
            System.out.println();
        }
    }

    private int getLen(int x,int para,int[][][] sMatrix){
        if (para==0){
            return sMatrix[x].length;
        }else{
            return sMatrix[x][0].length;
        }
    }

    public static void main(String[] args) {
        MatrixLink matrixLink=new MatrixLink();
        int[][] matrix1=new int[30][35];
        int[][] matrix2=new int[35][15];
        int[][] matrix3=new int[15][5];
        int[][] matrix4=new int[5][10];
        int[][] matrix5=new int[10][20];
        int[][] matrix6=new int[20][25];
        int[][][] matrixList= new int[][][]{matrix1,matrix2,matrix3,matrix4,matrix5,matrix6};
        matrixLink.matrix_multiply_by_DP(matrixList);

    }
}
