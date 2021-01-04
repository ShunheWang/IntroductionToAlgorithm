package com.dataStructure.stackAndQueue;


import java.util.EmptyStackException;

/**
 * impl by array
 */
public class Stack01 {
    private int size;
    private int[] array=null;
    private int point=-1;
    public Stack01(int size){
        this.size=size;
        array=new int[size];
    }

    public void push(int key){
        //array is full
        if((array.length-1)==point){
            int[] newArray=new int[size*2];
            System.arraycopy(array,0,newArray,0,array.length);
            array=newArray;
        }
        array[++point]=key;
    }

    public int pop(){
        //array is empty
        if(point<0){
            throw new EmptyStackException();
        }
        int key=array[point--];
        return key;
    }

    public int getSize() {
        return array.length;
    }

    public int[] getArray() {
        return array;
    }
}
