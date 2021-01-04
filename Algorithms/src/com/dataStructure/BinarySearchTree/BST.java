package com.dataStructure.BinarySearchTree;

import java.util.*;

/**
 * binary search tree
 * Functions:1.insert -completed
 *              1.1 recursive -completed
 *              1.2 non-recursive -completed
 *           2.search -completed
 *              2.1 recursive -completed
 *              2.2 non-recursive -completed
 *           3.minimum point -completed
 *           4.maximum point -completed
 *           5.successor -completed
 *           6.predecessor -completed
 *           7.delete -completed
 *           8.print
 *              8.1 preorder
 *                  8.1.1 recursive -completed
 *                  8.1.2 non-recursive -completed
 *              8.2 inorder (can sort)
 *                  8.2.1 recursive -completed
 *                  8.2.2 non-recursive -completed
 *              8.3 postorder
 *                  8.3.1 recursive -completed
 *                  8.3.2 non-recursive -completed
 */
public class BST {
    private Node root;
    private int size;

    public Node getRoot() {
        return root;
    }

    public Node instanceNewNode(int value){
        return new Node(value);
    }

    //1.1 recursive
    public void recursiveInsert1(Node curNode, int value){
        Node node=new Node(value);
        if(root==null){
            root=node;
            size++;
            return;
        }
        //left child
        if(value<=curNode.value){
            Node leftChild=curNode.leftChild;
            if (leftChild==null) {
                curNode.leftChild=node;
                node.parent=curNode;
                size++;
            }else{
                recursiveInsert1(leftChild,value);
            }
        }else{
            //right child
            Node rightChild=curNode.rightChild;
            if (rightChild==null) {
                curNode.rightChild=node;
                node.parent=curNode;
                size++;
            }else{
                recursiveInsert1(rightChild,value);
            }
        }
    }

    public void recursiveInsert2(int value){
        if(root==null){
            Node node=new Node(value);
            root=node;
            size++;
            return;
        }
        recursiveInsert2(root,value,-1,null);
    }

    //1.1 recursive
    private void recursiveInsert2(Node curNode,int value,int flag,Node lastCheckedNode){
        //flag: record which child should be added (0 --> left, 1 --> right)
        //lastCheckedNode: record last checked node for add new node into correct position

        //stop point
        if (curNode==null) {
            Node node=new Node(value);
            if (flag==0) {
                lastCheckedNode.leftChild=node;
            }else if(flag==1){
                lastCheckedNode.rightChild=node;
            }
            node.parent=lastCheckedNode;
            size++;
            return;
        }

        //left child
        if(value<=curNode.value){
            flag=0;
            recursiveInsert2(curNode.leftChild,value,flag,curNode);
        }else{
            flag=1;
            recursiveInsert2(curNode.rightChild,value,flag,curNode);
        }
    }

    //1.2 non-recursive
    public void nonRecursiveInsert1(int value){
        Node node=new Node(value);
        if (root==null) {
            root=node;
            size++;
            return;
        }
        Node temp=root;
        while (temp != null) {
            if(value<=temp.value){
                if(temp.leftChild==null){
                    temp.leftChild=node;
                    node.parent=temp;
                    size++;
                    break;
                }
                temp=temp.leftChild;
            }else{
                if(temp.rightChild==null){
                    temp.rightChild=node;
                    node.parent=temp;
                    size++;
                    break;
                }
                temp=temp.rightChild;
            }
        }
    }

    /**
     * important； method of ITA book.
     * @param value
     */
    //1.2 non-recursive method 2 based on p166 Introduction To Algorithms, 3rd
    public void nonRecursiveInsert2(int value){
        Node node=new Node(value);
        Node temp1=root;
        Node temp2=null;

        while (temp1 != null) {
            temp2=temp1; //record last checked node
            if(value<=temp1.value){
                temp1=temp1.leftChild;
            }else{
                temp1=temp1.rightChild;
            }
        }

        //root is empty
        if (temp2==null) {
            root=node;
            size++;
            return;
        }else if(value<=temp2.value){
            temp2.leftChild=node;
        }else{
            temp2.rightChild=node;
        }
        node.parent=temp2;
    }

    //2.1 recursive search
    public Node recursiveSearch(Node startNode,int value){
        if (startNode==null) {
            return null;
        }
        if(value==startNode.value){
            return startNode;
        }else if(value<startNode.value){
            return recursiveSearch(startNode.leftChild,value);
        }else if(value>startNode.value){
            return recursiveSearch(startNode.rightChild,value);
        }
        return null;
    }

    //2.2 non-recursive
    public Node nonRecursiveSearch(int value){
        if (root==null) {
            return null;
        }
        Node temp=root;
        while(temp!=null){
            if(value==temp.value){
                return temp;
            }else if(value<temp.value){
                temp=temp.leftChild;
            }else{
                temp=temp.rightChild;
            }
        }
        return null;
    }

    //3.minimum point
    public Node minPoint(){
        return minPoint(root);
    }
    //3.minimum point
    private Node minPoint(Node node){
        if (root==null) {
            return null;
        }
        while(node.leftChild!=null){
            node=node.leftChild;
        }
        return node;
    }

    public Node maxPoint(){
        return maxPoint(root);
    }

    // 4.maximum point
    private Node maxPoint(Node node){
        if (root==null) {
            return null;
        }
        while(node.rightChild!=null){
            node=node.rightChild;
        }
        return node;
    }

    //5.successor: to find minimum point which is larger than key-node
    public Node successor(int value){
        Node node = nonRecursiveSearch(value);
        if (node==null) {
            return null;
        }
        //1. if this key-node has right child thus successor of this key-node locates at the left node in lowest level of the right sub-tree of this key-node
        if(node.rightChild!=null){
            return minPoint(node.rightChild);
        }
        //2. if this key-node has no right child thus it will find the first parent node via starting this key-node to up, which satisfies key-node is its one of left child.(prob key-node has no successor)
        Node curParent=node.parent;
        while (curParent!=null&&curParent.rightChild==node){
            node=curParent;
            curParent=node.parent;
        }
        return curParent;
    }

    //6.predecessor: to find largest point which is lower than key-node
    public Node predecessor(int value){
        Node node = nonRecursiveSearch(value);
        if (node==null) {
            return null;
        }
        //1. if this key-node has left child thus predecessor of this key-node locates at the right node in lowest level of the right sub-tree of this key-node
        if(node.leftChild!=null){
            return maxPoint(node.leftChild);
        }
        //2. if this key-node has no left child thus it will find the first parent node via starting this key-node to up, which satisfies key-node is its one of right child.(prob key-node has no successor)
        Node curParent=node.parent;
        while (curParent!=null&&curParent.leftChild==node){
            node=curParent;
            curParent=node.parent;
        }
        return curParent;
    }

    //7.delete
    public void delete(int value){
        Node curNode = nonRecursiveSearch(value);
        if (curNode==null) {
            return;
        }
        Node parent=curNode.parent;
        //1.cur-node has no child --> directly delete
        if(curNode.leftChild==null&&curNode.rightChild==null){
            if(parent==null){
                root=null;
            }else if(parent.leftChild==curNode){
                parent.leftChild=null;
                curNode.parent=null;
            }else if (parent.rightChild==curNode){
                parent.rightChild=null;
                curNode.parent=null;
            }
        }else if(curNode.leftChild!=null&&curNode.rightChild==null){
            //2. cur-node has only left child --> remove to up
            if(parent==null){
                root=curNode.leftChild;
            }else if(parent.leftChild==curNode){
                parent.leftChild=curNode.leftChild;
                curNode.parent=null;
            }else if(parent.rightChild==curNode){
                parent.rightChild=curNode.leftChild;
                curNode.parent=null;
            }
        }else if(curNode.leftChild==null&&curNode.rightChild!=null){
            //3. cur-node has only right child --> remove to up
            if(parent==null){
                root=curNode.rightChild;
            }else if(parent.leftChild==curNode){
                parent.leftChild=curNode.rightChild;
                curNode.parent=null;
            }else if(parent.rightChild==curNode){
                parent.rightChild=curNode.rightChild;
                curNode.parent=null;
            }
        }else {
            //4. cur-node has two child
            //4.1: find minimum point
            Node child = minPoint(curNode.rightChild);
            //4.2: transplant value of curNode and child.
            transplantValue(curNode,child);
            //4.3: delete node means delete child node cuz it changed already, at the same time, the child might has right child or not
            //no matter it has or not, its parent will point to its child to delete.
            //get child node's parent node.
            Node p=child.parent;
            //check it is left child
            if(p.leftChild==child){
                p.leftChild=child.rightChild;
            }else if (p.rightChild==child){
                p.rightChild=child.rightChild;
            }
            //child may has no child sub-child
            if (child.rightChild!=null) {
                child.rightChild.parent=p;
                child.parent=null;
            }
        }
    }

    private void transplantValue(Node x,Node y){
        int temp=x.value;
        x.value=y.value;
        y.value=temp;
    }

    private void transplant(Node curNode,Node parent){
        if(parent==null){
            root=null;
        }else if(parent.leftChild==curNode){
            parent.leftChild=null;
        }else if (parent.rightChild==curNode){
            parent.rightChild=null;
        }
    }

    //8.1.1 recursive preorder
    public void recursivePreorder(Node node){
        if (node!=null) {
            System.out.print(node.value+" -->");
            recursivePreorder(node.leftChild);
            recursivePreorder(node.rightChild);
        }
    }

    //8.1.2 non-recursive preorder
    public void nonRecursivePreorder(Node node){
        if(node==null){
            return;
        }
        //use stack or deque to stored
        Deque<Node> stack=new ArrayDeque<>();
        stack.push(node);
        while (!stack.isEmpty()){
            Node outNode = stack.pop();
            System.out.print(outNode+" -->");
            //push outNode's right child first and then left child cuz of output rule is left child is first and right one is next
            if(outNode.rightChild!=null){
                stack.push(outNode.rightChild);
            }
            if(outNode.leftChild!=null){
                stack.push(outNode.leftChild);
            }
        }
    }

    //8.2.1 recursive inorder
    public void recursiveInorder(Node node){
        if (node!=null) {
            recursiveInorder(node.leftChild);
            System.out.print(node.value+" -->");
            recursiveInorder(node.rightChild);
        }
    }

    //8.2.2 non-recursive inorder
    public void nonRecursiveInorder1(Node node){
        if (node==null) {
            return;
        }
        Deque<Node> stack=new ArrayDeque<Node>();
        stack.push(node);
        while (!stack.isEmpty()){
            //push all left child in every time and firstly, it will push all left children from root to last left child.
            while (node.leftChild!=null){
                stack.push(node.leftChild);
                node=node.leftChild;
            }
            //pop the lowest left child
            Node outNode = stack.pop();
            System.out.print(outNode+"--> ");
            //check this outNode has right node or not.If it has, push it.
            //while above loop will do push operation.
            if(outNode.rightChild!=null){
                stack.push(outNode.rightChild);
                node=outNode.rightChild;
            }
        }
    }

    public void nonRecursiveInorder2(Node node){
        if (node==null) {
            return;
        }
        Deque<Node> stack=new ArrayDeque<Node>();
        while (!stack.isEmpty()||node!=null){
            //push all left child in every time and firstly, it will push all left children from root to last left child.
            while (node!=null){
                stack.push(node);
                node=node.leftChild;
            }
            //pop the lowest left child
            Node outNode = stack.pop();
            System.out.print(outNode+"--> ");
            //check this outNode has right node or not.If it has, push it.
            //while above loop will do push operation.
            node=outNode.rightChild;
        }
    }

    //8.2.1 recursive post order
    public void recursivePostorder(Node node){
        if (node!=null) {
            recursivePostorder(node.leftChild);
            recursivePostorder(node.rightChild);
            System.out.print(node.value+" -->");
        }
    }

    //8.3.2 non-recursive post order
    public void nonRecursivePostOrder(Node node){
        if (node==null) {
            return;
        }
        //flag is to record current node has checked or not.
        List<Integer> flag = new ArrayList<Integer>();
        Deque<Node> stack=new ArrayDeque<Node>();
        while (!stack.isEmpty()||node!=null){
            while (node!=null){
                stack.push(node);
                flag.add(0);
                node=node.leftChild;
            }

            if (flag.get(flag.size()-1)==0) {
                Node checkingNode = stack.getFirst();
                node=checkingNode.rightChild;
                flag.set(flag.size()-1,1);
            }else{
                Node outNode = stack.pop();
                flag.remove(flag.size()-1);
                System.out.print(outNode+"--> ");
            }


        }
    }

    private class Node{
        private int value;
        private Node leftChild=null;
        private Node rightChild=null;
        private Node parent=null;
        public Node(int value){
            this.value=value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }
}
