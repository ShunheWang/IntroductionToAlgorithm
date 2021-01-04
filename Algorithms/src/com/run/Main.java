package com.run;

import com.algorithmsImpl.InsertionSort.InsertionSortImpl;
import com.algorithmsImpl.addBinaryDigital.AddBinaryWithSameLong;
import com.algorithmsImpl.divideAndConquer.TwoDigitalArrayMutipleProblem;
import com.algorithmsImpl.heapSort.HeapSort;
import com.algorithmsImpl.heapSort.PriorityQueue;
import com.algorithmsImpl.mergeSort.MergeSortImpl;
import com.algorithmsImpl.quickSort.QuickSort;
import com.algorithmsImpl.sumSubarray.SumSubarray;
import com.dataStructure.BinarySearchTree.BST;
import com.dataStructure.linkedList.DoubleLinkedList;
import com.dataStructure.linkedList.OrderedSingleLinkedList;
import com.dataStructure.linkedList.SingleLinkedList;
import com.dataStructure.redBlackTree.RedBlackTree;
import com.dataStructure.stackAndQueue.Queue01;
import com.dataStructure.stackAndQueue.Stack01;

import java.util.Arrays;

public class Main {

    public int index = 0;
    public int a;
    public void f() {
        int a = 1;
    }

    int x = 1;
    public void a() {
        Person person = new Person();
        person.name = "aaa";
        System.out.println(person.name);
        b(person);
        System.out.println(person.name);

    }

    public void b(Person p) {
        p = new Person();
        p.name = "bbb";
    }

    class Person{
        String name;
    }

    public static void main(String[] args) {

        Main main1 = new Main();
        main1.a();

//        int[] originArrays1 = {9,3,1,4,6,8,7,5,2};
//        print(InsertionSortImpl.insertSort(originArrays1));


//        int[] a={1,0,1,1,1,1,0,1,0,0};
////        int[] b={0,1,0,0,1,1,0,1,1,1};
//        int[] b={1,1,0};
//        print(AddBinaryWithSameLong.addBinary(a,b));

//        int[] originArrays1 = {9,3,4,1,6,8,7,5};
//        MergeSortImpl.mergeSort(originArrays1,0,originArrays1.length-1);

//        int[] nums = {12,4,13,25,92,11,9,3,2};
//        MergeSortImpl.mergeSort(nums, 0, nums.length-1);
//        System.out.println(Arrays.toString(nums));
//        int[] nums = {-100,-2,-3,-4,-1,-2,-5,-10,-11};
//        SumSubarray.SubArray subArray = SumSubarray.sumSubarray3(nums, 0, nums.length-1);
//        System.out.println("lo --> "+subArray.getLo());
//        System.out.println("hi --> "+subArray.getHi());
//        System.out.println("sum --> "+subArray.getSubSum());

//        int A[][] = new int[][] {
//                {1, 1, 1, 1, 1, 1, 1, 1},
//                {2, 2, 2, 2, 2, 2, 2, 2},
//                {3, 3, 3, 3, 3, 3, 3, 3},
//                {4, 4, 4, 4, 4, 4, 4, 4},
//                {5, 5, 5, 5, 5, 5, 5, 5},
//                {6, 6, 6, 6, 6, 6, 6, 6},
//                {7, 7, 7, 7, 7, 7, 7, 7},
//                {8, 8, 8, 8, 8, 8, 8, 8},
//        };
//
//        int B[][] = new int[][] {
//                {8,8,8,8,8,8,8,8},
//                {7,7,7,7,7,7,7,7},
//                {6,6,6,6,6,6,6,6},
//                {5,5,5,5,5,5,5,5},
//                {4,4,4,4,4,4,4,4},
//                {3,3,3,3,3,3,3,3},
//                {2,2,2,2,2,2,2,2},
//                {1,1,1,1,1,1,1,1},
//        };
//
//        int[][] data=TwoDigitalArrayMutipleProblem.twoDigArrayMutipleByDAC(A,B,A.length);
//        print2(data);

        //Heap sort
//        int[] nums = {2,5,3,1,10,4};
//        HeapSort.heapSort(nums);
//        //HeapSort.maxHeapify1(nums,2);
//        print(nums);
//        PriorityQueue pq=new PriorityQueue();
//        pq.heapInsert(3);
//        pq.heapInsert(10);
//        pq.heapInsert(6);
//        pq.heapInsert(7);
//        pq.heapInsert(1);
//        pq.heapInsert(1);
//        pq.heapInsert(8);
//        pq.heapInsert(9);

//        pq.heapIncreaseKey(7,11);
//        System.out.println("heapMaximum node -->: " + pq.heapMaximum());
//        System.out.println("heapExtractMax node -->: " + pq.heapExtractMax());
//        print(pq.getArr());

//        int[] nums = {2,5,3,1,10,4};
//        QuickSort.quickSort(nums,0,nums.length-1);
//        print(nums);

//        Stack01 stack01=new Stack01(5);
//        stack01.push(1);
//        stack01.push(2);
//        stack01.push(3);
//        stack01.push(4);
//        stack01.push(5);
//        System.out.println("size --> "+ stack01.getSize());
//        stack01.push(6);
//        System.out.println("size --> "+ stack01.getSize());
//        System.out.println(stack01.pop());
//        System.out.println(stack01.pop());
//        System.out.println(stack01.pop());
//        System.out.println(stack01.pop());
//        System.out.println(stack01.pop());
//        System.out.println(stack01.pop());
////        System.out.println(stack01.pop());
//        print(stack01.getArray());


//        Queue01 queue01 = new Queue01(5);
//        queue01.enQueue(1);
//        queue01.enQueue(2);
//        queue01.enQueue(3);
//        queue01.enQueue(4);
//        //queue01.enQueue(5);
//        //queue01.enQueue(6);
//        System.out.println(queue01.deQueue());
//        System.out.println(queue01.deQueue());
//        System.out.println(queue01.deQueue());
//        System.out.println(queue01.deQueue());
//        System.out.println(queue01.deQueue());
//        print(queue01.getArray());

//        SingleLinkedList singleLinkedList=new SingleLinkedList();
//        singleLinkedList.display();
//        singleLinkedList.insertFromHead(1);
//        singleLinkedList.insertFromHead(2);
//        singleLinkedList.insertFromHead(3);
//        singleLinkedList.insertFromTail(4);
//        singleLinkedList.insertFromTail(5);
//        singleLinkedList.insertFromTail(6);
////        singleLinkedList.deleteFromHead();
////        singleLinkedList.deleteFromHead();
////        singleLinkedList.deleteFromTail();
////        singleLinkedList.deleteFromTail();
//        System.out.println(singleLinkedList.search(7));
//        singleLinkedList.display();

//        OrderedSingleLinkedList orderedSingleLinkedList=new OrderedSingleLinkedList();
//        orderedSingleLinkedList.insert(6);
//        orderedSingleLinkedList.insert(7);
//        orderedSingleLinkedList.insert(3);
//        orderedSingleLinkedList.insert(4);
//        orderedSingleLinkedList.insert(2);
//        orderedSingleLinkedList.insert(1);
//        orderedSingleLinkedList.deleteFromHead();
//        orderedSingleLinkedList.deleteFromHead();
//        orderedSingleLinkedList.insert(2);
//        orderedSingleLinkedList.insert(1);
//        orderedSingleLinkedList.deleteFromTail();
//        orderedSingleLinkedList.deleteFromTail();
//        orderedSingleLinkedList.insert(6);
//        orderedSingleLinkedList.insert(7);
//        System.out.println(orderedSingleLinkedList.search(1));
//        System.out.println(orderedSingleLinkedList.search(9));
//        System.out.println(orderedSingleLinkedList.search(7));
//        orderedSingleLinkedList.deleteByValue(7);
//        orderedSingleLinkedList.display();

//        DoubleLinkedList doubleLinkedList=new DoubleLinkedList();
//        doubleLinkedList.insertFromTail(3);
//        doubleLinkedList.insertFromTail(5);
//        doubleLinkedList.insertFromTail(6);
//        doubleLinkedList.insertFromHead(1);
//        doubleLinkedList.insertFromHead(2);
//        doubleLinkedList.insertFromHead(3);
////        doubleLinkedList.deleteFromHead();
////        doubleLinkedList.deleteFromHead();
////        doubleLinkedList.deleteFromTail();
////        doubleLinkedList.deleteFromTail();
////        doubleLinkedList.deleteByValue(5);
//        doubleLinkedList.deleteByValue(6);
//        doubleLinkedList.display();


        BST bst=new BST();
//        bst.recursiveInsert1(bst.getRoot(),5);
//        bst.recursiveInsert1(bst.getRoot(),7);
//        bst.recursiveInsert1(bst.getRoot(),4);
//        bst.recursiveInsert1(bst.getRoot(),8);
//        bst.recursiveInsert1(bst.getRoot(),6);
//        bst.recursiveInsert1(bst.getRoot(),2);
//        bst.recursiveInsert1(bst.getRoot(),3);
//        bst.recursiveInsert1(bst.getRoot(),9);

//        bst.recursiveInsert2(5);
//        bst.recursiveInsert2(7);
//        bst.recursiveInsert2(4);
//        bst.recursiveInsert2(8);
//        bst.recursiveInsert2(6);
//        bst.recursiveInsert2(2);
//        bst.recursiveInsert2(3);
//        bst.recursiveInsert2(9);

//        bst.nonRecursiveInsert1(5);
//        bst.nonRecursiveInsert1(7);
//        bst.nonRecursiveInsert1(4);
//        bst.nonRecursiveInsert1(8);
//        bst.nonRecursiveInsert1(6);
//        bst.nonRecursiveInsert1(2);
//        bst.nonRecursiveInsert1(3);
//        bst.nonRecursiveInsert1(9);

//        bst.nonRecursiveInsert2(5);
//        bst.nonRecursiveInsert2(7);
//        bst.nonRecursiveInsert2(4);
//        bst.nonRecursiveInsert2(8);
//        bst.nonRecursiveInsert2(6);
//        bst.nonRecursiveInsert2(2);
//        bst.nonRecursiveInsert2(3);
//        bst.nonRecursiveInsert2(9);
////        System.out.println( bst.recursiveSearch(bst.getRoot(),9));
////        System.out.println( bst.nonRecursiveSearch(100));
////        System.out.println( bst.minPoint());
////        System.out.println( bst.maxPoint());
////        System.out.println( bst.successor(3));
////        System.out.println( bst.predecessor(6));
//
//
////        bst.nonRecursiveInsert2(30);
////        bst.nonRecursiveInsert2(20);
////        bst.nonRecursiveInsert2(50);
////        bst.nonRecursiveInsert2(15);
////        bst.nonRecursiveInsert2(25);
////        bst.nonRecursiveInsert2(40);
////        bst.nonRecursiveInsert2(70);
////        bst.nonRecursiveInsert2(22);
////        bst.nonRecursiveInsert2(28);
////        bst.nonRecursiveInsert2(35);
////        bst.nonRecursiveInsert2(100);
////        bst.nonRecursiveInsert2(23);
////        bst.recursivePreorder(bst.getRoot());
////        System.out.println();
////        bst.delete(20);
//
////        bst.recursivePreorder(bst.getRoot());
////        System.out.println();
////        bst.nonRecursivePreorder(bst.getRoot());
////        System.out.println();
////        bst.recursiveInorder(bst.getRoot());
////        System.out.println();
//////        bst.nonRecursiveInorder1(bst.getRoot());
////        bst.nonRecursiveInorder2(bst.getRoot());
////        System.out.println();
//        bst.recursivePostorder(bst.getRoot());
//        System.out.println();
//        bst.nonRecursivePostOrder(bst.getRoot());
        RedBlackTree<Integer> rbt=new RedBlackTree<Integer>();
        rbt.insert(41);
        rbt.insert(38);
        rbt.insert(31);
        rbt.insert(12);
        rbt.insert(19);
        rbt.insert(8);
        rbt.delete(8);
        rbt.delete(12);
        rbt.delete(19);
        rbt.delete(31);
        rbt.delete(41);
        rbt.delete(38);
        rbt.preOrder(rbt.getRoot());
    }

    public static void print2(int[][] arrays){
        for(int i=0;i<arrays.length;i++){
            for (int j = 0; j < arrays.length; j++) {
                System.out.print(arrays[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void print(Object[] arrays){
        for(int i=0;i<arrays.length;i++){
            System.out.print(arrays[i]+" ");
        }
    }
}
