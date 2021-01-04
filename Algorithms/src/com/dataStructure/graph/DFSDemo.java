package com.dataStructure.graph;

import java.util.ArrayList;
import java.util.List;

public class DFSDemo {
    private static int time;
    private enum COLOR{
        WHITE,GREY,BLACK
    }

    private static void dfs_recursive(ListGraph listGraph){
        for (Vertex vertex:
             listGraph.vertexes) {
            vertex.p=null;
            vertex.color=COLOR.WHITE;
            vertex.d=Integer.MAX_VALUE;
            vertex.f=Integer.MAX_VALUE;
        }
        time = 0;
        for (Vertex vertex:
                listGraph.vertexes) {
            if (vertex.color==COLOR.WHITE){
                dfs_visit(listGraph,vertex);
            }
        }
    }

    private static void dfs_visit(ListGraph listGraph, Vertex vertex){
        vertex.color=COLOR.GREY;
        time++;
        vertex.d=time;
        for (Vertex subVertex:
                listGraph.edges.get(vertex.value)) {
            if (subVertex.color==COLOR.WHITE){
                subVertex.p=vertex;
                dfs_visit(listGraph,subVertex);
            }
        }
        vertex.color=COLOR.BLACK;
        time++;
        vertex.f=time;
    }

    public static void main(String[] args) {
        Vertex[] list=new Vertex[6];
        char[] names=new char[]{'u','v','w','x','y','z'};
        for (int i = 0; i < 6; i++) {
            list[i]=new Vertex(i,names[i]);
        }
        ListGraph listGraph = new ListGraph(list.length);
        //add vertex
        for (int i = 0; i < list.length; i++) {
            listGraph.addNode(list[i]);
        }
        //add edge
        listGraph.addEdge(list[0],list[1]);
        listGraph.addEdge(list[0],list[3]);
        listGraph.addEdge(list[1],list[4]);
        listGraph.addEdge(list[2],list[4]);
        listGraph.addEdge(list[2],list[5]);
        listGraph.addEdge(list[3],list[1]);
        listGraph.addEdge(list[4],list[3]);
        listGraph.addEdge(list[5],list[5]);
        listGraph.printEdge();
        dfs_recursive(listGraph);
        listGraph.printNode();
    }

    private static class Vertex{
        private char key;
        private int value;
        private COLOR color;
        private Vertex p;
        private int d; // degree or start time
        private int f;  //finish time
        public Vertex(int value, char key){
            this.value=value;
            this.key=key;
        }

        @Override
        public String toString() {
            return "[" +
                    "key=" + key +
                    ", value=" + value +
                    ", color=" + color +
                    ", p=" + p +
                    ", d=" + d +
                    ", f=" + f +
                    ']';
        }

        public String toString1() {
            return "[" +
                    "key=" + key +
                    ", value=" + value +
                    ", color=" + color +
                    ", d=" + d +
                    ", f=" + f +
                    ']';
        }
    }

    private static class ListGraph{
        public ArrayList<Vertex> vertexes;
        public ArrayList<ArrayList<Vertex>> edges;
        public ListGraph(int n){
            vertexes=new ArrayList<Vertex>();
            edges=new ArrayList<>();
            for (int i = 0; i < n; i++) {
                edges.add(new ArrayList<Vertex>());
            }
        }

        public void addNode(Vertex vertex){
            vertexes.add(vertex);
        }

        public void addEdge(Vertex vertex, Vertex edge){
            edges.get(vertex.value).add(edge);
        }

        public void printEdge(){
            for (int i = 0;i < edges.size(); i++) {
                System.out.print(i);
                ArrayList<Vertex> everyList=edges.get(i);
                for (int j = 0; j < everyList.size(); j++) {
                    System.out.print(" --> "+everyList.get(j));
                }
                System.out.println();
            }
        }

        public void printNode(){
            for (int i = 0;i < vertexes.size(); i++) {
                System.out.println(vertexes.get(i).toString1());
            }
        }

    }
}
