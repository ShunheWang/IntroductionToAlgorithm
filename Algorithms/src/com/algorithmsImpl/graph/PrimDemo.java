package com.algorithmsImpl.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class PrimDemo {

    class Vertex implements Comparable<Vertex>{
        private int item;
        private char key;
        private Vertex p;   //parent
        private boolean isVisit;
        private int cost;
        public Vertex(char key,int i){
            this.key=key;
            this.item=i;
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "item=" + item +
                    ", key=" + key +
                    ", p=" + p +
                    ", isVisit=" + isVisit +
                    ", cost=" + cost +
                    '}';
        }

        @Override
        public int compareTo(Vertex o) {
            return this.cost-o.cost;
        }
    }

    class Edge{
        private Vertex start;
        private Vertex end;
        private int weight;
        public Edge(Vertex start, Vertex end, int weight){
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
    }

    private List<Vertex> vertexes;
    private ArrayList<ArrayList<Edge>> edges;
    private int minTotalCost;

    public PrimDemo(){
        initData();
    }

    private void setVertexes(char c,int i){
        vertexes.add(new Vertex(c,i));
    }

    private void setEdges(int i,Vertex start, Vertex end, int weight){
        edges.get(i).add(new Edge(start,end,weight));
    }

    private Vertex getRoot(int i){
        return vertexes.get(i);
    }

    private void mst_prim(Vertex vertex){
        //store choice edge
        ArrayList<Edge> choiceEdges = new ArrayList<>();
        //1. initial vertexes properties
        for (Vertex v:
             vertexes) {
            v.p=null;
            v.cost=Integer.MAX_VALUE;
        }
        //2. initial start point
        vertex.cost=0;
        vertex.p=null;
        //3. priorityQueue
        PriorityQueue<Vertex> priorityQueue=new PriorityQueue<>();
        for (int i = vertexes.size()-1; i >= 0; i--) {
            priorityQueue.add(vertexes.get(i));
        }
        //priorityQueue.addAll(vertexes);
        //4. start to compute mst
        while (!priorityQueue.isEmpty()){
            Vertex curVertex = priorityQueue.poll();
            int item = curVertex.item;
            //update vertex's cost via questing edge with curVertex
            ArrayList<Edge> curEdges=edges.get(item);
            for (int i = 0; i < curEdges.size(); i++) {
                Edge edge = curEdges.get(i);
                Vertex end = edge.end;
                int weight = edge.weight;
                if (priorityQueue.contains(end) && weight < end.cost){
                    end.cost = weight;
                    end.p = curVertex;
                    //important!!!
                    //priorityQueue is in fact to use min_heap
                    //update heap element cannot auto update heap construct
                    //thus handle min_heapify operation by hand
                    priorityQueue.remove(end);
                    priorityQueue.add(end);
                }
//                //put candidate vertex
//                if (!priorityQueue.contains(end)) {
//                    priorityQueue.add(end);
//                }
            }
        }
    }

    private void display_mst(){
        System.out.println("mst edge --> ");
        for (Vertex vertex :
                vertexes) {
            if (vertex.p!=null){
                char start = vertex.key;
                char end = vertex.p.key;
                int cost = vertex.cost;
                minTotalCost+=cost;
                System.out.println("["+start+" ,"+end+" ,"+cost+"]");
            }
        }
        System.out.println("min total cost --> "+ minTotalCost);
    }

    /**
     * initial data according to the graph
     */
    private void initData(){
        char[] keys={'a','b','c','d','e','f','g','h','i'};
        vertexes=new ArrayList<>();
        edges=new ArrayList<>();
        //init vertex
        for (int i = 0; i < keys.length; i++) {
            setVertexes(keys[i],i);
        }
        //init edge
        for (int i = 0; i < vertexes.size(); i++) {
            edges.add(new ArrayList<Edge>());
        }
        setEdges(0,vertexes.get(0),vertexes.get(1),4);setEdges(1,vertexes.get(1),vertexes.get(0),4);
        setEdges(1,vertexes.get(1),vertexes.get(2),8);setEdges(2,vertexes.get(2),vertexes.get(1),8);
        setEdges(2,vertexes.get(2),vertexes.get(3),7);setEdges(3,vertexes.get(3),vertexes.get(2),7);
        setEdges(3,vertexes.get(3),vertexes.get(4),9);setEdges(4,vertexes.get(4),vertexes.get(3),9);
        setEdges(4,vertexes.get(4),vertexes.get(5),10);setEdges(5,vertexes.get(5),vertexes.get(4),10);
        setEdges(3,vertexes.get(3),vertexes.get(5),14);setEdges(5,vertexes.get(5),vertexes.get(3),14);
        setEdges(2,vertexes.get(2),vertexes.get(5),4);setEdges(5,vertexes.get(5),vertexes.get(2),4);
        setEdges(5,vertexes.get(5),vertexes.get(6),2);setEdges(6,vertexes.get(6),vertexes.get(5),2);
        setEdges(2,vertexes.get(2),vertexes.get(8),2);setEdges(8,vertexes.get(8),vertexes.get(2),2);
        setEdges(6,vertexes.get(6),vertexes.get(8),6);setEdges(8,vertexes.get(8),vertexes.get(6),6);
        setEdges(7,vertexes.get(7),vertexes.get(8),7);setEdges(8,vertexes.get(8),vertexes.get(7),7);
        setEdges(6,vertexes.get(6),vertexes.get(7),1);setEdges(7,vertexes.get(7),vertexes.get(6),1);
        setEdges(1,vertexes.get(1),vertexes.get(7),11);setEdges(7,vertexes.get(7),vertexes.get(1),11);
        setEdges(0,vertexes.get(0),vertexes.get(7),8);setEdges(7,vertexes.get(7),vertexes.get(0),8);
    }

    public static void main(String[] args) {
        PrimDemo primDemo = new PrimDemo();
        primDemo.mst_prim(primDemo.getRoot(0));
        primDemo.display_mst();

    }
}
