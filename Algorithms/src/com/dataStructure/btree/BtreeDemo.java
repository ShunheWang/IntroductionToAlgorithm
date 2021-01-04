package com.dataStructure.btree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * implement b-tree with t=3, thus key range(t-1, 2t-1) --> (2, 5) && child range(t, 2t) --> (3, 6).
 */
public class BtreeDemo {
    private Node root;
    private int height;
    private int t;  // fix parameter for computing key and child degree
    private int key_min;
    private int key_max;
    private int child_min;
    private int child_max;

    public BtreeDemo(int t){
        this.t = t;
        this.key_min=t-1;
        this.key_max=2*t-1;
        this.child_min=t;
        this.child_max=2*t;
        root = new Node();
        root.isLeaf=true;
    }
    /**
     * node in b-tree
     * key should order by decreasing
     * and split children by key
     */
    private class Node{
        private int keyNum;     // key number in current node
        private String[] key;   // store key value
        private Node[] children;      // store children
        private boolean isLeaf;     // true: current node is leaf node, otherwise, its internal node
        public Node(){
            keyNum=0;
            key=new String[0];
            children=new Node[0];
        }

    }

    private Node allocateNode(){
        return new Node();
    }

    private void insert(String k){
        if (root.keyNum == key_max){
            Node newNode = allocateNode();
            newNode.isLeaf = false;
            newNode.keyNum = 0;
            newNode.children=new Node[1];
            newNode.children[0] = root;
            root = newNode;
            splitChild(newNode,0);
            insertNotFull(newNode,k);
        }else{
            insertNotFull(root,k);
        }
    }

    private void splitChild(Node p, int i) {
        // new node to store half value
        Node child2Node=new Node();
        Node child1Node=p.children[i];
        // init child2Node's child array size --> (t-1)
        child2Node.key=new String[(t-1)];
        // allocate right half size key in child1Node into child2Node
        for (int j = 0; j < t - 1; j++) {
            child2Node.key[j] = child1Node.key[j+t];
        }
        // transfer leaf
        child2Node.isLeaf=child1Node.isLeaf;
        // allocate relative children
        if (!child2Node.isLeaf){
            // is not leaf means that it has children then allocate children from child1Node to child2Node
            child2Node.children = new Node[t];
            for (int j = 0; j < t; j++) {
                child2Node.children[j] = child1Node.children[j+t];
            }
        }
        // modify child2Node key num
        child2Node.keyNum = t-1;

        // insert key(t) from child1Node to p
        // use temp array
        String[] temp = new String[p.keyNum+1];
        for (int j = 0; j < i; j++) {
            temp[j] = p.key[j];
        }
        for (int j = i+1; j < temp.length; j++) {
            temp[j] = p.key[j-1];
        }
        temp[i] = child1Node.key[t-1];
        p.key = temp;
        p.keyNum = p.keyNum + 1;

        // insert child 1 & 2 into p
        // use temp array
        Node[] tempC = new Node[p.keyNum+1];
        for (int j = 0; j < i + 1; j++) {
            tempC[j] = p.children[j];
        }
        for (int j = i + 2; j < tempC.length; j++) {
            tempC[j] = p.children[j-1];
        }
        tempC[i+1] = child2Node;
        p.children = tempC;

        // delete child1Node.key[t]
        String[] tempK = new String[child1Node.keyNum-t];
        for (int j = 0; j < tempK.length; j++) {
            tempK[j] = child1Node.key[j];
        }
        child1Node.keyNum=child1Node.keyNum-t;
        child1Node.key=tempK;

    }

    private void insertNotFull(Node node, String k) {
        int i = node.keyNum-1;
        if (node.isLeaf){
            String[] temp = new String[node.keyNum+1];
            while (i>=0 && k.compareTo(node.key[i])<0){
                temp[i+1] = node.key[i];
                i--;
            }
            temp[i+1] = k;
            for (int j = 0; j < i+1; j++) {
                temp[j] = node.key[j];
            }
            node.key = temp;
            node.keyNum = node.keyNum + 1;
        }else {
            while (i>=0 && k.compareTo(node.key[i])<0){
                i--;
            }
            i++;
            if (node.children[i].keyNum == key_max){
                splitChild(node,i);
                if (k.compareTo(node.key[i])>0){
                    i++;
                }
            }
            insertNotFull(node.children[i],k);
        }

    }

    private void print_bst(){
        Deque<Node> deque = new ArrayDeque<Node>();
        Deque<Integer> index = new ArrayDeque<Integer>();
        if (root!=null) {
            deque.add(root);
        }
        int level = 0;
        while (!deque.isEmpty()){
            Node curNode = deque.pop();
            level++;
            for (int i = 0; i < curNode.key.length; i++) {
                System.out.println("["+level+","+curNode.key[i]+"]");
            }
            if (curNode.children!=null) {
                for (int i = 0; i < curNode.children.length; i++) {
                    deque.add(curNode.children[i]);
                }
            }
        }
    }

    public static void main(String[] args) {
        BtreeDemo btreeDemo = new BtreeDemo(2);
        btreeDemo.insert("F");
        btreeDemo.insert("S");
        btreeDemo.insert("Q");
        btreeDemo.insert("K");
        btreeDemo.insert("C");
        btreeDemo.insert("L");
        btreeDemo.insert("H");
        btreeDemo.insert("T");
        btreeDemo.insert("V");
        btreeDemo.insert("W");
        btreeDemo.insert("M");
        btreeDemo.insert("R");
        btreeDemo.insert("N");
        btreeDemo.insert("P");
        btreeDemo.insert("A");
        btreeDemo.insert("B");
        btreeDemo.insert("X");
        btreeDemo.insert("Y");
        btreeDemo.insert("D");
        btreeDemo.insert("Z");
        btreeDemo.insert("E");

        btreeDemo.print_bst();

    }
}
