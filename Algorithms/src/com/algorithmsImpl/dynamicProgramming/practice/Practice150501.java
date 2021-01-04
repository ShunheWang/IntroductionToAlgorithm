package com.algorithmsImpl.dynamicProgramming.practice;

public class Practice150501 {
    private static final int LEFT=0;
    private static final int RIGHT=1;
    private static final int ROOT=2;
    public static void main(String[] args) {
        int[][] root={{0,0,0,0,0,0},
                    {0,1,1,2,2,2},
                    {0,0,2,2,2,4},
                    {0,0,0,3,4,5},
                    {0,0,0,0,4,5},
                    {0,0,0,0,0,5}};
        System.out.println(root.length);
        Practice150501.construct_optimal_bst(root,1,root.length-1,ROOT);
    }

    private static void construct_optimal_bst(int[][] root,int i,int j,int para) {
        if(i<=j){
            if (para==ROOT){
                System.out.println("k"+root[i][j]+" is a ROOT");
            }else if (para==LEFT){
                System.out.println("k"+root[i][j]+" is k"+(j+1)+" left child");
            }else{
                //para==RIGHT
                System.out.println("k"+root[i][j]+" is k"+(i-1)+" right child");
            }
            construct_optimal_bst(root,i,root[i][j]-1,LEFT);
            construct_optimal_bst(root,root[i][j]+1,j,RIGHT);
        }else if (i==(j+1)){
            if (para==LEFT){
                System.out.println("d"+j+" is k"+i+" left child");
            }else if(para==RIGHT){
                System.out.println("d"+j+" is k"+j+" right child");
            }
        }

    }
}
