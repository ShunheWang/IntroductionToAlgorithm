package com.dataStructure.redBlackTree;

import java.util.Objects;

public class RedBlackTree<T extends Comparable<T>> {
    private static final String NODE_RED="red";
    private static final String NODE_BLACK="black";

    Node<T> root;
    int size;

    public Node<T> getRoot(){
        return root;
    }
    public void insert(T value){
        Node<T> newNode=new Node<T>(value);
//        if(root==null){
//            root=newNode;
//        }else{
            //1. find empty position
            Node<T> temp01=root;//for while-loop
            Node<T> temp02=null;//record lastNode
            while (temp01!=null){
                temp02=temp01;
                if (value.compareTo(temp01.value)<=0) {
                    temp01=temp01.leftChild;
                }else{
                    //value.compareTo(temp01.value)<=1
                    temp01=temp01.rightChild;
                }
            }
            //has found fitting position
            //1. RB-tree is empty
            if (temp02==null) {
                root=newNode;
            }else if (value.compareTo(temp02.value)<=0) {
                //2. newNode value <= its parent node
                temp02.leftChild=newNode;
            }else{
                //3. newNode value > its parent node
                temp02.rightChild=newNode;
            }
            // No matter newNode.parent is null or not null, it always add its parent node
            newNode.parent=temp02; //temp02 may be null cuz of root has not parent.
            //add attributes for new node
            newNode.leftChild=null;
            newNode.rightChild=null;
            newNode.color=NODE_RED;
            size++;
            //insert finished and start to fix color
            insertFixupColor(newNode);
//        }
    }

    /**
     * Consideration: operation of changing color dont consider current Node and always focus on curNode's parent and grandpa.
     * Summary(only focus on parent is left side of grandpa): 1. uncle - red
     *             1.1 current node is left side --> dont care
     *             1.2 current node is right side --> dont care
     *             Operation: change parent, uncle as well as grandpa color
     *           2. uncle - black
     *             2.1 current node is left side --> dont care
     *             2.2 current node is right side --> care
     *              --> operation: case 2: dont change color, only left rotate
     *             Operation: case 3: change color and right rotate
     * @param curNode
     */
    private void insertFixupColor(Node<T> curNode) {
        //no need to check curNode is root cuz if yes, last codes is to change root-color to black no matter root-color is black or not
        while(curNode.parent!=null&&curNode.parent.color.equals(NODE_RED)){
            //REMEMBER: change color is for curNode's parent thus it should check parent is left or right child of grandPa
            if (curNode.parent==curNode.parent.parent.leftChild) {
                //uncle node
                Node<T> uncleNode=curNode.parent.parent.rightChild;
                //focus on checking uncleNode
                //case 1: uncle node color is red, at the same time, dont care about curNode is left child or right child of its parent.
                if (uncleNode!=null&&uncleNode.color.equals(NODE_RED)){
                    curNode.parent.color=NODE_BLACK;
                    uncleNode.color=NODE_BLACK;
                    //change grandPa color
                    curNode.parent.parent.color=NODE_RED;
                    //change pointer
                    curNode=curNode.parent.parent;
                }else{
                    //case 2: if brother node is left, in other words, current node is in right side
                    if (curNode==curNode.parent.rightChild) {
                        //left rotate will transform case 2 to case 3
                        //change pointer
                        curNode=curNode.parent;
                        leftRotate(curNode);
                    }
                    //uncleNode.color==NODE_BLACK
                    //case 3: two probs: 1. uncleNode is null, default color is black, or uncleNode is not null, color is black.
                    // --> operation: 1. cuz node is left side, change current node's parent node color and grandpa node color
                    //                2. right rotate
                    curNode.parent.color=NODE_BLACK;
                    curNode.parent.parent.color=NODE_RED;
                    rightRotate(curNode.parent.parent);
                }
            }else{
                //curNode.parent==curNode.parent.parent.rightChild
                Node<T> uncleNode=curNode.parent.parent.leftChild;
                if (uncleNode!=null&&uncleNode.color.equals(NODE_RED)) {
                    //case 1:
                    curNode.parent.color=NODE_BLACK;
                    uncleNode.color=NODE_BLACK;
                    curNode.parent.parent.color=NODE_RED;
                    curNode=curNode.parent.parent;
                }else{
                    //case 2: brother node is right side, in other words, curNode is left side
                    if (curNode==curNode.parent.leftChild) {
                        curNode=curNode.parent;
                        rightRotate(curNode);
                    }
                    //case 3:
                    curNode.parent.color=NODE_BLACK;
                    curNode.parent.parent.color=NODE_RED;
                    leftRotate(curNode.parent.parent);
                }
            }
        }
        root.color=NODE_BLACK;
    }

    /**
     * maybe parameter called node in fixup-color function  is null which color is black thus we should make a new fake node to replace null
     * otherwise,node(null).parent is error --> NullPointerException i image
     * @param value
     */
    public void delete(T value){
        Node<T> curNode=search(value);
        if (curNode==null) {
            //has no searched node
            return;
        }
        String originalColor=curNode.color;
        Node<T> childNode=null;//record deleted node's child or replaced node's child
        Node<T> fakeNode=null;
        if (curNode.leftChild==null) {
            childNode=curNode.rightChild;
            if (childNode==null) {
                fakeNode= new Node<T>(NODE_BLACK);
                childNode=fakeNode;
            }
            transplant(curNode,childNode);

        }else if (curNode.rightChild==null){
            childNode=curNode.leftChild;
            if (childNode==null) {
                fakeNode= new Node<T>(NODE_BLACK);
                childNode=fakeNode;
            }
            transplant(curNode,childNode);
        }else{
            //find successor of current node
            Node<T> successor = minimum(curNode);
            //record successor color cuz deleting operation originally is to replace curNode's value by minimum value and keep original color
            originalColor=successor.color;
            //childNode only is in right side cuz of successor
            childNode=successor.rightChild;
            if (childNode==null) {
                fakeNode= new Node<T>(NODE_BLACK);
                childNode=fakeNode;
            }
            //2. if successor doesnt direct connect with curNode which means it must be lower than curNode.child level
            if(successor.parent!=curNode){
                //2.1. successor's child connects with successor's parent
                transplant(successor,childNode);
                //2.2. successor connects with curNode's right child
                successor.rightChild=curNode.rightChild;
                curNode.rightChild.parent=successor;
                //then operation below is to connect successor with curNode.parent
            }
            //1. if successor connects directly with curNode
            //it means we only connect curNode.parent with successor
            transplant(curNode,successor);
            //connect curNode.leftChild with successor
            successor.leftChild=curNode.leftChild;
            curNode.leftChild.parent=successor;
            successor.color=curNode.color;
        }

        //only deleting black node needs to fixup color cuz deleting red node has not effects on BH-counts of every path
        if (originalColor.compareTo(NODE_BLACK)==0){
            deleteFixupColor(childNode);
        }

        if (fakeNode!=null) {
            if (fakeNode.parent==null) {
                root=null;
            }else if (fakeNode.parent.leftChild==fakeNode) {
                fakeNode.parent.leftChild=null;
            }else{
                fakeNode.parent.rightChild=null;
            }
        }
    }

    private void deleteFixupColor(Node<T> node){
        while (node != root && node.color.equals(NODE_BLACK)) {
            if (node.parent.leftChild==null||node==node.parent.leftChild) {
                //brother node must exist cuz node color is black, and it must be satisfies with rule 4
                Node<T> brotherNode=node.parent.rightChild;
                //case 1: brother node color is red
                if(brotherNode.color.equals(NODE_RED)){
                    //and transform to case 2, 3 or 4
                    brotherNode.color=NODE_BLACK;
                    node.parent.color=NODE_RED;
                    //left-rotate
                    leftRotate(node.parent);
                    //new brother node
                    brotherNode=node.parent.rightChild;
                }
                //case 2: brother node color is Black, at the same time, its left and right child color is Black
                //Need to check children is null cuz default color is black
                if((brotherNode.leftChild==null&&brotherNode.rightChild==null)||(brotherNode.leftChild.equals(NODE_BLACK)&&brotherNode.rightChild.equals(NODE_BLACK))){
                    //change brother color to red
                    brotherNode.color=NODE_RED;
                    //pointer goes up
                    node=node.parent;
                }else{
                    // case 3: left child is red
                    if (brotherNode.rightChild.color.equals(NODE_BLACK)){
                        brotherNode.leftChild.color=NODE_BLACK;
                        brotherNode.color=NODE_RED;
                        rightRotate(brotherNode);
                        // new brother node
                        brotherNode=node.parent.rightChild;
                    }
                    //case 4: right child is red and dont care what color of left child
                    brotherNode.color=node.parent.color;
                    node.parent.color=NODE_BLACK;
                    brotherNode.rightChild.color=NODE_BLACK;
                    leftRotate(node.parent);
                    node=root;
                }
            }else{
                //node==node.parent.rightChild
                //brother node must exist
                Node<T> brotherNode=node.parent.leftChild;
                //case 1: brother node color is red
                if (brotherNode.color.equals(NODE_RED)) {
                    brotherNode.color=NODE_BLACK;
                    node.parent.color=NODE_RED;
                    //right-rotate
                    rightRotate(node.parent);
                    //new brother node
                    brotherNode=node.parent.leftChild;
                }
                //case 2:brother node color is black and children is black also.
                if ((brotherNode.leftChild==null&&brotherNode.rightChild==null)||(brotherNode.leftChild.equals(NODE_BLACK)&&brotherNode.rightChild.equals(NODE_BLACK))) {
                    //change brother color to red
                    brotherNode.color=NODE_RED;
                    //pointer goes up
                    node=node.parent;
                }else{
                    //case 3: right child is red
                    if (brotherNode.leftChild.color.equals(NODE_BLACK)){
                       brotherNode.rightChild.color=NODE_BLACK;
                       brotherNode.color=NODE_RED;
                       leftRotate(brotherNode);
                       //pointer goes up
                       brotherNode=node.parent.leftChild;
                    }
                    //case 4:
                    brotherNode.color=node.parent.color;
                    node.parent.color=NODE_BLACK;
                    brotherNode.leftChild.color=NODE_BLACK;
                    rightRotate(node.parent);
                    node=root;
                }
            }
        }
        node.color=NODE_BLACK;
    }

    private Node<T> search(T value) {
        Node<T> temp=root;
        while (temp != null) {
            if (value.compareTo(temp.value)<0) {
                temp=temp.leftChild;
            }else if (value.compareTo(temp.value)==0) {
                return temp;
            }else{
                temp=temp.rightChild;
            }
        }
        return null;
    }

    private void transplant(Node<T> node,Node<T> childNode){
        if (node.parent==null) {
            root=childNode;
        }else if (node==node.parent.leftChild) {
            node.parent.leftChild=childNode;
        }else{
            //node.parent.rightChild=childNode;
            node.parent.rightChild=childNode;
        }
        if (childNode!=null) {
            childNode.parent=node.parent;
        }
    }

    private Node<T> minimum(Node<T> curNode){
        while (curNode.leftChild != null) {
                curNode=curNode.leftChild;
        }
        return curNode;
    }

    public void leftRotate(Node<T> curNode){
        Node<T> rightNode=curNode.rightChild; //record right-child
        //1. rightNode's left child transforms to connect with curNode's as its new right child
        curNode.rightChild=rightNode.leftChild; // rightNode.leftChild!=null, connect. rightNode.leftChild==null, connect with null
        if (rightNode.leftChild!=null) {
            rightNode.leftChild.parent=curNode;
        }
        //2. curNode.parent connects with rightNode
        if (curNode.parent==null){
            root=rightNode;
        }else if (curNode==curNode.parent.leftChild){
            curNode.parent.leftChild=rightNode;
        }else{
            //curNode is its parent's rightChild
            curNode.parent.rightChild=rightNode;
        }
        rightNode.parent=curNode.parent;
        //3.curNode connects with rightNode.leftChild
        rightNode.leftChild=curNode;
        curNode.parent=rightNode;
    }

    public void rightRotate(Node<T> curNode){
        Node<T> leftNode=curNode.leftChild; //record left-child
        //1. leftNode's right child transforms to connect with curNode's as its new left child
        curNode.leftChild=leftNode.rightChild;
        if (leftNode.rightChild!=null){
            leftNode.rightChild.parent=curNode;
        }
        //2. curNode.parent connect with leftNode
        if (curNode.parent==null){
            root=leftNode;
        }else if (curNode==curNode.parent.leftChild){
            curNode.parent.leftChild=leftNode;
        }else{
            //curNode is its parent right-child
            curNode.parent.rightChild=leftNode;
        }
        leftNode.parent=curNode.parent;
        //3. curNode connects with leftNode.rightNode
        leftNode.rightChild=curNode;
        curNode.parent=leftNode;
    }

    public void preOrder(Node<T> node){
        if (node!=null){
            System.out.print("["+node.value+","+node.color+"] --> ");
            preOrder(node.leftChild);
            preOrder(node.rightChild);
        }
    }


    private class Node<T extends Comparable<T>>{
        private T value;
        private Node<T> leftChild;
        private Node<T> rightChild;
        private Node<T> parent;
        private String color;

        public Node(T value){
            this.value=value;
        }

        public Node(String color) {
            this.color=color;
        }
    }
}
