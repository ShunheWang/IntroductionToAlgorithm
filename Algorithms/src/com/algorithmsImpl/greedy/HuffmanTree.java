package com.algorithmsImpl.greedy;

public class HuffmanTree {
    private static HeapNode root;
    private static HeapNode[] heapNodes;

    private static class HeapNode{
        private char character;
        private int freq;
        private HeapNode leftNode;
        private HeapNode rightNode;

        public HeapNode(int freq,HeapNode leftNode,HeapNode rightNode){
            this.freq=freq;
            this.leftNode=leftNode;
            this.rightNode=rightNode;
        }
        public HeapNode(char character, int freq){
            this.character=character;
            this.freq=freq;
        }
    }

    /**
     * build min heap by heapNode's frequency
     * @param heaps
     */
    private static void buildMinHeap(HeapNode[] heaps){
        int n=(heaps.length-1)/2;
        for (int i = n; i >= 0; i--) {
            minHeapify(heaps,i);
        }
    }

    private static void minHeapify(HeapNode[] heaps, int i){
        int leftIndex=2*i+1;
        int rightIndex=2*i+2;

        int largestIndex=i;
        if (leftIndex<heaps.length&&heaps[largestIndex].freq>heaps[leftIndex].freq) {
            largestIndex=leftIndex;
        }
        if (rightIndex<heaps.length&&heaps[largestIndex].freq>heaps[rightIndex].freq) {
            largestIndex=rightIndex;
        }

        if (largestIndex!=i){
            swap(heaps,largestIndex,i);
            minHeapify(heaps,largestIndex);
        }
    }


    private static HeapNode getMinNode(){
        swap(heapNodes,0,heapNodes.length-1);
        HeapNode curMinNode=heapNodes[heapNodes.length-1];
        //size--
        HeapNode[] dest=new HeapNode[heapNodes.length-1];
        System.arraycopy(heapNodes,0,dest,0,heapNodes.length-1);
        heapNodes=dest;
        minHeapify(heapNodes,0);
        return curMinNode;
    }

    private static void setNewNode(HeapNode newNode){
        HeapNode[] dest=new HeapNode[heapNodes.length+1];
        System.arraycopy(heapNodes,0,dest,0,heapNodes.length);
        int curIndex=dest.length-1;
        dest[curIndex]=newNode;
        heapNodes=dest;
        //parent node
        int pIndex=(curIndex-1)/2;
        while (pIndex>=0&&heapNodes[curIndex].freq<heapNodes[pIndex].freq){
            swap(heapNodes,curIndex,pIndex);
            curIndex=pIndex;
            pIndex=(curIndex-1)/2;
        }
    }

    private static void swap(HeapNode[] heaps, int i, int j){
        HeapNode tempNode=heaps[i];
        heaps[i]=heaps[j];
        heaps[j]=tempNode;
    }

    private static void buildHuffmanTree(char[] characters, int[] frequencies, int n){
        //1. create and init heaps array
        heapNodes=new HeapNode[n];
        for (int i = 0; i < heapNodes.length; i++) {
            heapNodes[i]=new HeapNode(characters[i],frequencies[i]);
        }

        //2. init --> build min heap
        buildMinHeap(heapNodes);

        //3. generate huffman tree
        for (int i = 0; i < n-1; i++) {
            HeapNode leftNode=getMinNode();
            HeapNode rightNode=getMinNode();
            //new parent node
            HeapNode pHeapNode=new HeapNode((leftNode.freq+rightNode.freq),leftNode,rightNode);
            setNewNode(pHeapNode);
        }

        //4. return huffman tree
        root=getMinNode();
        printHuffmanTree(root);
    }

    private static void printHuffmanTree(HeapNode node){
        if (node!=null){
            printHuffmanTree(node.leftNode);
            System.out.println("["+node.character+","+node.freq+"]");
            printHuffmanTree(node.rightNode);
        }
    }

    public static void main(String[] args) {
        char[] characters=new char[]{'a','b','c','d','e','f'};
        int[] frequencies=new int[]{45,13,12,16,9,5};
        buildHuffmanTree(characters,frequencies,characters.length);
        System.out.println(root.freq);

    }
}
