package com.dataStructure.linkedList;

public class DoubleLinkedList {

    private int size=0;
    private Node root=null;
    private Node lastNode=null;

    public void insertFromHead(int value){
        Node newNode = new Node(value);
        if (root != null) {
            newNode.setNextNode(root);
            root.setPrevNode(newNode);
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
            newNode.setPrevNode(temp);
        }
        size++;
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
            Node temp=root;
            while(temp.getNextNode()!=null){
                temp=temp.getNextNode();
            }
            temp.getPrevNode().setNextNode(null);
        }
        size--;
    }

    public void deleteByValue(int value){
        if (root==null) {
            return;
        }
        if(root.getNextNode()==null){
            if(value==root.getValue()){
                root=null;
            }
        }else{
            if(value==root.getValue()){
                root=root.getNextNode();
                return;
            }
            Node temp=root.getNextNode();
            while(temp!=null){
                if(temp.getValue()==value){
                    temp.getPrevNode().setNextNode(temp.getNextNode());
                    if(temp.getNextNode()!=null){
                        temp.getNextNode().setPrevNode(temp.getPrevNode());
                    }
                    break;
                }
                temp=temp.getNextNode();
            }
        }
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
        private Node prevNode;
        private Node nextNode=null;
        public Node(int value){
            this.value=value;
        }

        public Node getPrevNode() {
            return prevNode;
        }

        public void setPrevNode(Node prevNode) {
            this.prevNode = prevNode;
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
