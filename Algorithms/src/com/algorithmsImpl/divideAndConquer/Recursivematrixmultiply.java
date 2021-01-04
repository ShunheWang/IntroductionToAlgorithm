package com.algorithmsImpl.divideAndConquer;

public class Recursivematrixmultiply {
    public static void displayMatrix(int matrix[][]) {
        int n = matrix.length;
        for (int i=0;i<n; i++) {
            for (int j=0;j<n;j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void copyToMatrix(int srcMatrix[][], int startI, int startJ,
                                    int destMatrix[][]) {
        int n = destMatrix.length;
        for (int i = startI; i < startI+n; i++) {
            for (int j = startJ; j <startJ+n ; j++) {
                destMatrix[i - startI][j - startJ] = srcMatrix[i][j];
            }
        }
    }
    public static void copyFromMatrix(int destMatrix[][], int startI, int startJ,
                                      int srcMatrix[][]) {
        int n = srcMatrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                destMatrix[startI + i][startJ + j] = srcMatrix[i][j];
            }
        }
    }
    public static void MatrixAdd(int A[][],int B[][],int C[][]){
        int n = A.length;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                C[i][j] = A[i][j] + B[i][j];
            }
        }
    }
    public static void MatrixSub(int A[][], int B[][], int C[][]) {
        int n = A.length;
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
    }


    public static int[][] squareMatrixMultiplyRecursive(int A[][], int B[][]) {
        int n = A.length;
        int C[][] = new int[n][n];
        if (n == 1) {
            C[0][0] = A[0][0] * B[0][0];
        } else {
            int A11[][], A12[][], A21[][], A22[][];
            int B11[][], B12[][], B21[][], B22[][];
            int C11[][], C12[][], C21[][], C22[][];

            A11 = new int[n/2][n/2];A12 = new int[n/2][n/2];A21 = new int[n/2][n/2];A22 = new int[n/2][n/2];
            copyToMatrix(A, 0, 0, A11);
            copyToMatrix(A, 0, n/2,  A12);
            copyToMatrix(A, n/2, 0,  A21);
            copyToMatrix(A, n/2, n/2, A22);

            B11 = new int[n/2][n/2];B12 = new int[n/2][n/2];B21 = new int[n/2][n/2];B22 = new int[n/2][n/2];
            copyToMatrix(B, 0, 0, B11);
            copyToMatrix(B, 0, n/2, B12);
            copyToMatrix(B, n/2, 0, B21);
            copyToMatrix(B, n/2, n/2, B22);

            C11 = new int[n/2][n/2];C12 = new int[n/2][n/2];C21 = new int[n/2][n/2];C22 = new int[n/2][n/2];
            MatrixAdd(squareMatrixMultiplyRecursive(A11, B11), squareMatrixMultiplyRecursive(A12, B21),
                    C11);
            MatrixAdd(squareMatrixMultiplyRecursive(A11, B12), squareMatrixMultiplyRecursive(A12, B22),
                    C12);
            MatrixAdd(squareMatrixMultiplyRecursive(A21, B11), squareMatrixMultiplyRecursive(A22, B21),
                    C21);
            MatrixAdd(squareMatrixMultiplyRecursive(A21, B12), squareMatrixMultiplyRecursive(A22, B22),
                    C22);
            copyFromMatrix(C, 0, 0, C11);
            copyFromMatrix(C, 0, n/2, C12);
            copyFromMatrix(C, n/2, 0, C21);
            copyFromMatrix(C, n/2, n/2, C22);
        }

        return C;
    }
    public static void main(String[] args) {
        int A[][] = new int[][] {
                {1, 1, 1, 1, 1, 1, 1, 1},
                {2, 2, 2, 2, 2, 2, 2, 2},
                {3, 3, 3, 3, 3, 3, 3, 3},
                {4, 4, 4, 4, 4, 4, 4, 4},
                {5, 5, 5, 5, 5, 5, 5, 5},
                {6, 6, 6, 6, 6, 6, 6, 6},
                {7, 7, 7, 7, 7, 7, 7, 7},
                {8, 8, 8, 8, 8, 8, 8, 8},
        };

        int B[][] = new int[][] {
                {8,8,8,8,8,8,8,8},
                {7,7,7,7,7,7,7,7},
                {6,6,6,6,6,6,6,6},
                {5,5,5,5,5,5,5,5},
                {4,4,4,4,4,4,4,4},
                {3,3,3,3,3,3,3,3},
                {2,2,2,2,2,2,2,2},
                {1,1,1,1,1,1,1,1},
        };
        System.out.println("递归矩阵乘法");
        int C[][] = squareMatrixMultiplyRecursive(A,B);
        displayMatrix(C);
    }
}