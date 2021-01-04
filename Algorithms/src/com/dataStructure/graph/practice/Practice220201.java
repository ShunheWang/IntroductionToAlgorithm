package com.dataStructure.graph.practice;

import com.dataStructure.graph.BFSDemo;

import java.time.temporal.ValueRange;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * practice 22.2-1 in p348, book
 */
public class Practice220201 {
    public enum COLOR{WHITE,GREY,BLACK};

    private static void bfsTraversal(GraphByLinkedList graphByLinkedList, Vertex s){
        for (int i = 0; i < graphByLinkedList.vertexes.size(); i++) {
            Vertex vertex=graphByLinkedList.vertexes.get(i);
            vertex.color= COLOR.WHITE;
            vertex.d=Integer.MAX_VALUE;
            vertex.p=null;
        }
        Deque<Vertex> deque = new ArrayDeque<Vertex>();
        s.color=COLOR.GREY;
        s.d=0;
        s.p=null;
        deque.add(s);
        while (!deque.isEmpty()) {
            Vertex vertex = deque.pop();
            int value = vertex.value;
            for (Vertex vertexItem :
                    graphByLinkedList.edges.get(value)) {
                if (vertexItem.color == COLOR.WHITE) {
                    vertexItem.color=COLOR.GREY;
                    vertexItem.d=vertex.d+1;
                    vertexItem.p=vertex;
                    //add queue
                    deque.add(vertexItem);
                }
            }
            vertex.color=COLOR.BLACK;
        }

    }

    public static void main(String[] args) {
        Vertex[] vList=new Vertex[7];
        for (int i = 0; i < vList.length; i++) {
            vList[i]=new Vertex(i);
        }
        GraphByLinkedList graphByLinkedList=new GraphByLinkedList(vList.length);
        for (int i = 0; i < vList.length; i++) {
            graphByLinkedList.vertexes.add(vList[i]);
        }
        //add edges
        graphByLinkedList.addEdge(vList[1],vList[2]);
        graphByLinkedList.addEdge(vList[1],vList[4]);
        graphByLinkedList.addEdge(vList[2],vList[5]);
        graphByLinkedList.addEdge(vList[3],vList[6]);
        graphByLinkedList.addEdge(vList[3],vList[5]);
        graphByLinkedList.addEdge(vList[4],vList[2]);
        graphByLinkedList.addEdge(vList[5],vList[4]);
        graphByLinkedList.addEdge(vList[6],vList[6]);
        System.out.println("before bst --> ");
        graphByLinkedList.printGraph();
        System.out.println("after bst --> ");
        bfsTraversal(graphByLinkedList,vList[3]);
        graphByLinkedList.printNode();
    }

    static class Vertex {
        public int value;
        public COLOR color;
        public int d;   //degree
        public Vertex p;   //parent
        public Vertex(int value){
            this.value=value;
        }

        @Override
        public String toString() {
            return "[" +
                    "value=" + value +
                    ", color=" + color +
                    ", d=" + d +
                    ", p=" + p +
                    ']';
        }

        public String toString1() {
            return "[" +
                    "value=" + value +
                    ", color=" + color +
                    ", d=" + d +
                    ']';
        }
    }

    static class GraphByLinkedList {
        public ArrayList<Vertex> vertexes;
        public ArrayList<ArrayList<Vertex>> edges;
        public GraphByLinkedList(int v){
            vertexes =new ArrayList<>();
            edges=new ArrayList<>();
            for (int i = 0; i < v; i++) {
                edges.add(new ArrayList<Vertex>()); //add edges
            }
        }

        /**
         * add edge
         * @param vertex
         * @param edge
         */
        public void addEdge(Vertex vertex, Vertex edge){
            edges.get(vertex.value).add(edge);
        }


        public void printGraph(){
            for (int i = 0; i < edges.size(); i++) {
                ArrayList<Vertex> temp=edges.get(i);
                System.out.print(i+" --> ");
                for (int j = 0; j < temp.size(); j++) {
                    Vertex v=temp.get(j);
                    System.out.print(v.toString()+" --> ");
                }
                System.out.println();
            }
        }

        public void printNode(){
            for (int i = 0; i < vertexes.size(); i++) {
                System.out.println(vertexes.get(i).toString1());
            }
        }
    }
}


