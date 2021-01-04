package com.dataStructure.graph;

import java.util.ArrayList;

public class GraphByLinkedList {

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

    public static void main(String[] args) {
        int[] vertexes={0,1,2,3,4,5};
        int n=vertexes.length;
        GraphByLinkedList graphByLinkedList=new GraphByLinkedList(n);
        graphByLinkedList.addEdge(1,2);
        graphByLinkedList.addEdge(1,5);
        graphByLinkedList.addEdge(2,1);
        graphByLinkedList.addEdge(2,5);
        graphByLinkedList.addEdge(2,4);
        graphByLinkedList.addEdge(2,3);
        graphByLinkedList.addEdge(3,2);
        graphByLinkedList.addEdge(3,4);
        graphByLinkedList.addEdge(4,2);
        graphByLinkedList.addEdge(4,3);
        graphByLinkedList.addEdge(4,5);
        graphByLinkedList.addEdge(5,1);
        graphByLinkedList.addEdge(5,2);
        graphByLinkedList.addEdge(5,4);
        graphByLinkedList.printGraph();
    }
}
