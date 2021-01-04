package com.algorithmsImpl.quickSort;

import java.util.Random;

public class QuickSort {

    public static void quickSort(int[] arr,int p, int r){
        if (p>=r) {
            return;
        }
        int q=partition(arr,p,r);
        quickSort(arr,p,q-1);
        quickSort(arr,q+1,r);
    }

    public static int partition(int[] arr,int p,int r){
        //1. 选择主元pilot element
        Random random=new Random();
        int pilotElement=random.nextInt((r-p+1))+p;
        //2. swap pilot to last index
        swap(arr,pilotElement,r);
        //3. core part of quick sort
        int pilotValue=arr[r];
        int i=p-1;
        for (int j = p; j < r; j++) {
            if(arr[j]<=pilotValue){
                i++;
                swap(arr,i,j);
            }
        }
        swap(arr,(i+1),r);
        return i+1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
}
