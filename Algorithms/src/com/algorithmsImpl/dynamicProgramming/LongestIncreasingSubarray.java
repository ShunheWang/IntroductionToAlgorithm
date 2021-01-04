package com.algorithmsImpl.dynamicProgramming;

/**
 * 15.4-5 & 15.4-6 p226 in introduction to algorithm
 */
public class LongestIncreasingSubarray {

    //15.4-5 On2
    private void solution1(int[] arr){
        int n=arr.length;
        //1. create new array to store result
        int[] c=new int[n];
        int[] path=new int[n];

        //3. init first result
        c[0]=1;
        //2. computing
        for (int i = 1; i < n; i++) {
            int j;
            for (j = 0; j < i; j++) {
                if (arr[i]>arr[j]&&c[i]<c[j]+1) {
                    c[i]=c[j]+1;
                    path[i]=j;
                }
            }
        }

        //print max counts
        int max_i=getMaxCount(c);
        //print all elements of largest subarray
        getPath(arr,path,max_i);
    }

    private void getPath(int[]a,int[] path, int para){
        if (para==0){
            System.out.print(a[para]+"-->");
            return;
        }else{
            getPath(a,path,path[para]);
            System.out.print(a[para]+"-->");
        }
    }

    private int getMaxCount(int[] c){
        int max=c[0];
        int max_i=0;
        for (int i = 1; i < c.length; i++) {
            if (max<c[i]) {
                max=c[i];
                max_i=i;
            }
        }
        System.out.println("max count --> "+ max);
        return max_i;
    }

    //15.4-6 Ologn
    private void solution2(int[] arr){

    }

    public static void main(String[] args) {
        int arr[] = {2, 4, 5, 6, 1, 2, 3, 8, 1};
        LongestIncreasingSubarray longestIncreasingSubarray=new LongestIncreasingSubarray();
        longestIncreasingSubarray.solution1(arr);
    }
}
