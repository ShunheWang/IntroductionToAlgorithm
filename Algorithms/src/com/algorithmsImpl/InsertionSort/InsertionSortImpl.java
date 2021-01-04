package com.algorithmsImpl.InsertionSort;

public class InsertionSortImpl {

    public static int[] insertSort(int[] arrays){
        for(int j=1;j<arrays.length;j++){
            int key=arrays[j];  // j->1
            int i=j-1;          //i->0
            while(i>=0 && arrays[i]>key){
                arrays[i+1]=arrays[i]; //a[1]->9
                i--;            //i-->-1
            }
            arrays[i+1]=key;    //a[1]=1
        }
        return arrays;
    }
}
