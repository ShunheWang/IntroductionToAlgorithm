package com.algorithmsImpl.graph;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class KruskalDemo {

    class Vertex{
        private int item;
        private char key;
        private Vertex p;   //parent
        private boolean isRoot;   //mst root
        public Vertex(char key,int i){
            this.key=key;
            this.item=i;
        }

        @Override
        public String toString() {
            return "key=" + key;
        }
    }

    class Edge implements Comparable<Edge>{
        private Vertex start;
        private Vertex end;
        private int weight;
        public Edge(Vertex start,Vertex end,int weight){
            this.start=start;
            this.end=end;
            this.weight=weight;
        }

        @Override
        public String toString() {
            return "[" +
                    "start=" + start +
                    ", end=" + end +
                    ", weight=" + weight +
                    ']';
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight-o.weight;
        }
    }

    private ArrayList<Vertex> vertexes;
    private ArrayList<Edge> edges;
    public KruskalDemo(){
        vertexes=new ArrayList<>();
        edges=new ArrayList<>();
    }

    private void setVertexes(char c,int i){
        vertexes.add(new Vertex(c,i));
    }

    private void setEdges(Vertex start,Vertex end,int weight){
        edges.add(new Edge(start,end,weight));
    }

    private void printVertex(){
        for (int i = 0; i < vertexes.size(); i++) {
            System.out.println(vertexes.get(i).toString());
        }
    }

    private void printEdge(){
        for (int i = 0; i < edges.size(); i++) {
            System.out.println(edges.get(i).toString());
        }
    }

    /**
     * make set
     * @param vertex
     */
    private void makeSet(Vertex vertex){
        vertex.isRoot=true;
        vertex.p=null;
    }

    private int findSet(Vertex vertex){
        int item;
        if (vertex.isRoot==false){
            vertex=vertex.p;
            item=findSet(vertex);
        }else{
            item=vertex.item;
        }
        return item;
    }

    private void union(Vertex start, Vertex end){
        if (start.item>=end.item){
            start.isRoot=false;
            start.p=end;
        }else{
            end.isRoot=false;
            end.p=start;
        }
    }


    private void mst_kruskal(){
        //1. initial tree array and n vertex means n trees within it when starting
        ArrayList<Edge> trees=new ArrayList<Edge>();
        for (int i = 0; i < vertexes.size(); i++) {
            makeSet(vertexes.get(i));
        }
        //2. edges order by decreasing weight
        PriorityQueue<Edge> priorityQueue=new PriorityQueue<>();
        for (Edge edge :
                edges) {
            priorityQueue.add(edge);
        }
        //3. loop to select first smallest edge every round
        while (!priorityQueue.isEmpty()) {
            Edge curEdge = priorityQueue.poll();
            Vertex start = curEdge.start;
            Vertex end = curEdge.end;
            //start to check cyclic
            if (findSet(start)!=findSet(end)){
                //no cyclic then add edge and union operation
                trees.add(curEdge);
                union(start,end);
            }
        }
        //5. print
        int minTotalWeight=0;
        System.out.println("mst edges --> ");
        for (Edge edge :
                trees) {
            minTotalWeight+=edge.weight;
            System.out.println(edge.toString());
        }
        System.out.println("min total weight --> "+ minTotalWeight);
    }

    /**
     * initial data according to the graph of p367
     */
    private void initData(){
        char[] keys={'a','b','c','d','e','f','g','h','i'};
        //init vertex
        for (int i = 0; i < keys.length; i++) {
            setVertexes(keys[i],i);
        }
        //init edge
        setEdges(vertexes.get(0),vertexes.get(1),4);setEdges(vertexes.get(1),vertexes.get(0),4);
        setEdges(vertexes.get(1),vertexes.get(2),8);setEdges(vertexes.get(2),vertexes.get(1),8);
        setEdges(vertexes.get(2),vertexes.get(3),7);setEdges(vertexes.get(3),vertexes.get(2),7);
        setEdges(vertexes.get(3),vertexes.get(4),9);setEdges(vertexes.get(4),vertexes.get(3),9);
        setEdges(vertexes.get(4),vertexes.get(5),10);setEdges(vertexes.get(5),vertexes.get(4),10);
        setEdges(vertexes.get(3),vertexes.get(5),14);setEdges(vertexes.get(5),vertexes.get(3),14);
        setEdges(vertexes.get(2),vertexes.get(5),4);setEdges(vertexes.get(5),vertexes.get(2),4);
        setEdges(vertexes.get(5),vertexes.get(6),2);setEdges(vertexes.get(6),vertexes.get(5),2);
        setEdges(vertexes.get(2),vertexes.get(8),2);setEdges(vertexes.get(8),vertexes.get(2),2);
        setEdges(vertexes.get(6),vertexes.get(8),6);setEdges(vertexes.get(8),vertexes.get(6),6);
        setEdges(vertexes.get(7),vertexes.get(8),7);setEdges(vertexes.get(8),vertexes.get(7),7);
        setEdges(vertexes.get(6),vertexes.get(7),1);setEdges(vertexes.get(7),vertexes.get(6),1);
        setEdges(vertexes.get(1),vertexes.get(7),11);setEdges(vertexes.get(7),vertexes.get(1),11);
        setEdges(vertexes.get(0),vertexes.get(7),8);setEdges(vertexes.get(7),vertexes.get(0),8);
    }

    public static void main(String[] args) {
        KruskalDemo kruskalDemo=new KruskalDemo();
        kruskalDemo.initData();
//        kruskalDemo.printVertex();
//        kruskalDemo.printEdge();
//        System.out.println();
        kruskalDemo.mst_kruskal();
    }
}
