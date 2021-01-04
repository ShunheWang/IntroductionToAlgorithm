package com.dataStructure.graph;

import java.lang.reflect.Array;
import java.util.*;

public class BFSDemo {
    public enum COLOR{WHITE,BLACK,GREY};

    private static void bfsTraversal(GraphByLinkedList graphByLinkedList, Vertex s){
        //1. init vertex parameter;
        for (int i = 0; i < graphByLinkedList.vertexes.size(); i++) {
            Vertex vertex = graphByLinkedList.vertexes.get(i);
            vertex.color=COLOR.WHITE;
            vertex.d=Integer.MAX_VALUE;
            vertex.p=null;
        }
        //2. init first node
        s.color=COLOR.GREY;
        s.d=0;
//        s.p=null;
        //3. new Queue for computing
        Deque<Vertex> mDeque = new ArrayDeque<Vertex>();
        //add s into queue
        mDeque.add(s);
        while (!mDeque.isEmpty()){
            Vertex vertex = mDeque.pop();
            int vertexItem=vertex.value;
            ArrayList<Vertex> vertexEdgeList=graphByLinkedList.edges.get(vertexItem);
            for (int i = 0; i < vertexEdgeList.size(); i++) {
                Vertex temp = vertexEdgeList.get(i);
                if (temp.color==COLOR.WHITE){
                    temp.color=COLOR.GREY;
                    temp.d=vertex.d+1;
                    temp.p=vertex;
                    mDeque.add(temp);
                }
            }
            vertex.color=COLOR.BLACK;
        }
    }

    private static void printPath(GraphByLinkedList graphByLinkedList,Vertex root, Vertex curVertex){
        if (curVertex==root){
            System.out.print("["+curVertex.value+"]");
        }else if (curVertex.p==null){
            System.out.print("no path");
        }else{
            printPath(graphByLinkedList,root,curVertex.p);
            System.out.print(" --> ["+curVertex.value+"]");
        }
    }

    public static void main(String[] args) {
        Vertex[] vList=new Vertex[8];
        for (int i = 0; i < vList.length; i++) {
            vList[i]=new Vertex(i);
        }
        GraphByLinkedList graphByLinkedList=new GraphByLinkedList(vList.length);
        for (int i = 0; i < vList.length; i++) {
            graphByLinkedList.vertexes.add(vList[i]);
        }
        //add edges
        graphByLinkedList.addEdge(vList[0], vList[4]);
        graphByLinkedList.addEdge(vList[0], vList[1]);
        graphByLinkedList.addEdge(vList[1], vList[0]);
        graphByLinkedList.addEdge(vList[1], vList[5]);
        graphByLinkedList.addEdge(vList[2], vList[5]);
        graphByLinkedList.addEdge(vList[2], vList[6]);
        graphByLinkedList.addEdge(vList[2], vList[3]);
        graphByLinkedList.addEdge(vList[3], vList[2]);
        graphByLinkedList.addEdge(vList[3], vList[6]);
        graphByLinkedList.addEdge(vList[3], vList[7]);
        graphByLinkedList.addEdge(vList[4], vList[0]);
        graphByLinkedList.addEdge(vList[5], vList[1]);
        graphByLinkedList.addEdge(vList[5], vList[2]);
        graphByLinkedList.addEdge(vList[5], vList[6]);
        graphByLinkedList.addEdge(vList[6], vList[5]);
        graphByLinkedList.addEdge(vList[6], vList[2]);
        graphByLinkedList.addEdge(vList[6], vList[3]);
        graphByLinkedList.addEdge(vList[6], vList[7]);
        graphByLinkedList.addEdge(vList[7], vList[6]);
        graphByLinkedList.addEdge(vList[7], vList[3]);
        System.out.println("Before bst --> ");
        graphByLinkedList.printGraph();
        bfsTraversal(graphByLinkedList,vList[1]);
        System.out.println("After bst --> ");
        graphByLinkedList.printNode();
        printPath(graphByLinkedList,vList[1], vList[0]);
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

        /**
         * remove edge
         * @param vertex
         * @param edge
         */
        public void removeEdge(Vertex vertex, int edge){
            edges.get(vertex.value).remove(edge);
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


