package com.dataStructure.linkedList;

import java.util.EmptyStackException;

/**
 * unordered single linked list
 */
public class SingleLinkedList {
    private int size=0;
    private Node root=null;
    // private Node lastNode --> maybe simplify insert tail and delete

    public void insertFromHead(int value){
        Node newNode = new Node(value);
        if (root != null) {
            newNode.setNextNode(root);
        }
        root=newNode;
        size++;
    }

    public void insertFromTail(int value){
        Node newNode = new Node(value);
        if (root == null) {
            root=newNode;
        }else{
            Node temp=root;
            while(temp.getNextNode()!=null){
                temp=temp.getNextNode();
            }
            temp.setNextNode(newNode);
            size++;
        }

    }

    public void deleteFromHead(){
         if (root==null){
             return;
         }
         if(root.getNextNode()==null){
             root=null;
         }else{
             root=root.getNextNode();
         }
         size--;
    }

    public void deleteFromTail(){
        if (root==null){
            return;
        }else if(root.getNextNode()==null){
            root=null;
        }else{
            //1 2 3 --> 2 3 4 --> 4 5 6
            Node node1=root;
            Node node2=node1.getNextNode();
            while(node2.getNextNode()!=null){
                node1=node1.getNextNode();
                node2=node1.getNextNode();
            }
            node1.setNextNode(null);
        }
        size--;
    }

    public int search(int value){
        int i=-1;
        if(root==null)
            throw new EmptyStackException();
        if(root.getValue()==value){
            return ++i;
        }else{
            i++;
            Node temp=root;
            while(temp.getNextNode()!=null){
                if(temp.getNextNode().getValue()==value){
                    return ++i;
                }
                temp=temp.getNextNode();
                i++;
            }
        }
        return -1;
    }

    public void display(){
        System.out.println("size --> "+ size);
        System.out.print("[");
        if(root!=null){
            System.out.print(root.getValue() + " -->");
            Node temp=root;
            while(temp.getNextNode()!=null){
                System.out.print(temp.getNextNode().getValue() + " -->");
                temp=temp.getNextNode();
            }
        }
        System.out.println("]");
    }


    public class Node{
        private int value;
        private Node nextNode=null;
        public Node(int value){
            this.value=value;
        }

        public int getValue() {
            return value;
        }

        public Node getNextNode() {
            return nextNode;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }
    }
}
