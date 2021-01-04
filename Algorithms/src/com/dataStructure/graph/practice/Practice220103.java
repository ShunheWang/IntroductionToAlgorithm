package com.dataStructure.graph.practice;

import java.util.ArrayList;

/**
 *
 */
public class Practice220103 {

    /**
     *  O(n2)
     * @param graphMatrix
     * @param n
     * @return
     */
    private static int[][] reverseMatrix(GraphMatrix graphMatrix, int n){
        int[][] reverseMatrix=new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                reverseMatrix[j][i]=graphMatrix.edges[i][j];
            }
        }

        return reverseMatrix;
    }

    /**
     * O(V+E)
     * @param ori
     * @param rev
     */
    private static void reverseList(GraphByLinkedList ori, GraphByLinkedList rev){
        for (int i = 0; i < ori.vertexes.size(); i++) {
            int oriVertex=i;
            for (int j = 0; j < ori.vertexes.get(i).size(); j++) {
                int oriEdge=ori.vertexes.get(i).get(j);
                rev.vertexes.get(oriEdge).add(oriVertex);
            }
        }
    }

    public static void main(String[] args) {
        int[] vertexes=new int[]{0,1,2,3,4,5,6};
        //1. matrixGraph
        GraphMatrix graphMatrix=new GraphMatrix(vertexes.length);
        graphMatrix.addEdge(1,2);
        graphMatrix.addEdge(1,4);
        graphMatrix.addEdge(2,5);
        graphMatrix.addEdge(3,5);
        graphMatrix.addEdge(3,6);
        graphMatrix.addEdge(4,2);
        graphMatrix.addEdge(5,4);
        graphMatrix.addEdge(6,6);
        System.out.println("Origin Matrix: ");
        graphMatrix.printGraph();
        System.out.println("Reverse Matrix: ");
        int[][] reverseMatrix=Practice220103.reverseMatrix(graphMatrix,vertexes.length);
        for (int i = 0; i < reverseMatrix.length; i++) {
            for (int j = 0; j < reverseMatrix.length; j++) {
                System.out.print(reverseMatrix[i][j]+" ");
            }
            System.out.println();
        }

        //2. listGraph
        GraphByLinkedList oriList=new GraphByLinkedList(vertexes.length);
        GraphByLinkedList reverseList=new GraphByLinkedList(vertexes.length);
        oriList.addEdge(1,2);
        oriList.addEdge(1,4);
        oriList.addEdge(2,5);
        oriList.addEdge(3,5);
        oriList.addEdge(3,6);
        oriList.addEdge(4,2);
        oriList.addEdge(5,4);
        oriList.addEdge(6,6);
        Practice220103.reverseList(oriList,reverseList);
        System.out.println("origin list --> ");
        oriList.printGraph();
        System.out.println("reverse list -->");
        reverseList.printGraph();
    }
}

class GraphByLinkedList {

    public ArrayList<ArrayList<Integer>> vertexes;

    public GraphByLinkedList(int v){
        vertexes=new ArrayList<>();
        for (int i = 0; i < v; i++) {
            vertexes.add(new ArrayList<Integer>()); //add edges
        }
    }

    /**
     * add edge
     * @param vertex
     * @param edge
     */
    public void addEdge(int vertex, int edge){
        vertexes.get(vertex).add(edge);
    }

    /**
     * remove edge
     * @param vertex
     * @param edge
     */
    public void removeEdge(int vertex, int edge){
        vertexes.get(vertex).remove(edge);
    }

    public void printGraph(){
        for (int i = 0; i < vertexes.size(); i++) {
            System.out.print("["+i+"] -->");
            for (int j = 0; j < vertexes.get(i).size(); j++) {
                System.out.print("["+vertexes.get(i).get(j)+"] --> ");
            }
            System.out.println();
        }
    }
}

class GraphMatrix {
    public int[][] edges;
    public int n;
    public GraphMatrix(int v){
        this.n=v;
        edges=new int[n][n];
    }

    public void addEdge(int start, int end) {
        if (start>=n||end>=n) {
            return;
        }
        edges[start][end]=1;
    }

    public void removeEdge(int start, int end){
        if (start>=n||end>=n) {
            return;
        }
        edges[start][end]=0;
    }

    public void printGraph(){
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges.length; j++) {
                System.out.print(edges[i][j]+" ");
            }
            System.out.println();
        }
    }
}