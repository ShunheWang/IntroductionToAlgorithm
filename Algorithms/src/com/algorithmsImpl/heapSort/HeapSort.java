package com.algorithmsImpl.heapSort;


import java.util.Arrays;

public class HeapSort {


    //method 1: recursion
    public static void maxHeapify1(int[] arr,int i,int heapSize){
        int leftChildNode=2*i+1;
        int rightChildNode=2*i+2;
        int largestIndex=i;

        //compare root node with left child node
        if (leftChildNode<heapSize && arr[leftChildNode]>arr[i]) {
            largestIndex=leftChildNode;
        }

        //compare root node with right child node
        if(rightChildNode<heapSize && arr[rightChildNode]>arr[largestIndex]){
            largestIndex=rightChildNode;
        }

        if (largestIndex!=i) {
            swap(arr,largestIndex,i);
            //ensure child tree is correct after swap
            maxHeapify1(arr,largestIndex,heapSize);
        }

    }

    //method 2: by for
    public static void maxHeapify2(int[] arr,int i,int heapSize){

        while(i<arr.length){
            int leftChildNode=2*i+1;
            int rightChildNode=2*i+2;
            int largestIndex=i;

            //compare root node with left child node
            if (leftChildNode<heapSize && arr[leftChildNode]>arr[i]) {
                largestIndex=leftChildNode;
            }

            //compare root node with right child node
            if(rightChildNode<heapSize && arr[rightChildNode]>arr[largestIndex]){
                largestIndex=rightChildNode;
            }
            if (largestIndex!=i) {
                swap(arr,largestIndex,i);
                i=largestIndex;
            }else
                break;
        }

    }

    public static void buildMaxHeap(int[] arr){
        int lastNode=arr.length-1;
        for(int i=(lastNode-1)/2;i>=0;i--){
            maxHeapify1(arr,i,arr.length);
        }
    }

    public static void heapSort(int[] arr){
        int heapSize=arr.length;
        buildMaxHeap(arr);
        for(int i=arr.length-1;i>0;i--){
            swap(arr,0,i);
            heapSize--;
            maxHeapify1(arr,0,heapSize);
        }
    }

    private static void swap(int[] arr,int largestIndex, int i) {
        int temp=arr[i];
        arr[i]=arr[largestIndex];
        arr[largestIndex]=temp;
    }

}
