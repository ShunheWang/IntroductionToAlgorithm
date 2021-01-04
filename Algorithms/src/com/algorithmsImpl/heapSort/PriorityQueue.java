package com.algorithmsImpl.heapSort;

public class PriorityQueue {

    private int[] arr=null;

    public int[] getArr() {
        return arr;
    }

    public int heapMaximum(){
        if(arr.length<1){
            try {
                throw new Exception("Priority Queue is empty");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return arr[0];
    }

    public int heapExtractMax(){
        if(arr.length<1){
            try {
                throw new Exception("Priority Queue is empty");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        int max=arr[0];
        //swap root node with last node
        arr[0]=arr[arr.length-1];
        //size--
        arr=decreaseArray(arr);
        maxHeapify(0);
        return max;
    }

    public void heapIncreaseKey(int i,int key) {
        if(i>arr.length-1){
            try {
                throw new Exception("Out of priority queue range");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return;
            }
        }
        if(key<arr[i]){
            try {
                throw new Exception("new key is smaller than current key");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return;
            }
        }
        arr[i]=key;
        //1. swap
        while(i>=0 && arr[i]>arr[getParentNode(i)]){
            swap(i,getParentNode(i));
            i=getParentNode(i);
        }

        //2. 插入排序思想--> 每一个父节点比较比目前节点小的都往后移动直到找到合适的位置
//        while(i>=0 && arr[i]>arr[getParentNode(i)]){
//            //swap(i,getParentNode(i));
//            arr[i]=arr[getParentNode(i)];
//            i=getParentNode(i);
//        }
//        循环出来就是合适的位置在复制key
//        arr[i]=key;
    }

    public void heapInsert(int key){
        if(arr==null){
            arr=new int[1];
            arr[0]=key;
            return;
        }
        arr=increaseArray(arr);
        arr[arr.length-1]=Integer.MIN_VALUE;
        heapIncreaseKey(arr.length-1,key);

    }

    private int[] increaseArray(int[] arr){
        int[] newArray = new int [arr.length+1];
        System.arraycopy(arr,0,newArray,0,arr.length);
        return newArray;
    }

    private int[] decreaseArray(int[] arr){
        int[] newArray = new int [arr.length-1];
        System.arraycopy(arr,0,newArray,0,arr.length-1);
        return newArray;
    }

    private void maxHeapify(int i) {
        int largestIndex=i;

        //compare root node with left child node
        if (getLeftChildNode(i)<arr.length && arr[getLeftChildNode(i)]>arr[i]) {
            largestIndex=getLeftChildNode(i);
        }

        //compare root node with right child node
        if(getRightChildNode(i)<arr.length && arr[getRightChildNode(i)]>arr[largestIndex]){
            largestIndex=getRightChildNode(i);
        }

        if (largestIndex!=i) {
            swap(largestIndex,i);
            //ensure child tree is correct after swap
            maxHeapify(largestIndex);
        }
    }

    private void swap(int largestIndex, int i) {
        int temp=arr[i];
        arr[i]=arr[largestIndex];
        arr[largestIndex]=temp;
    }

    private int getLeftChildNode(int i){
        return (2*i+1);
    }

    private int getRightChildNode(int i){
        return (2*i+2);
    }

    private int getParentNode(int i){
        return ((i-1)/2);
    }
}
