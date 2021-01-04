package com.dataStructure.stackAndQueue;

import java.util.EmptyStackException;

public class Queue01 {
    private int size;
    private Object[] array = null;
    private int head = -1;
    private int tail = -1;

    public Queue01(int size) {
        this.size = size;
        array = new Object[size];
    }

    public void enQueue(int key) {
        //check tail node is last node in queue
        if (tail == (size-1)) {
            tail = -1;
        }
        tail++;
        //queue has been full
        if (tail == head || array[tail]!=null) {
            throw new StackOverflowError();
        }
        array[tail] = key;
    }

    public Object deQueue() {
        //check head node is last node in queue
        if (head == (size-1)) {
            head = -1;
        }
        //array has been empty
        head++;
        if (head == (tail+1)) {
            throw new EmptyStackException();
        }
        Object obj=array[head];
        array[head]=null;
        return obj;
    }

    public Object[] getArray() {
        return array;
    }
}
