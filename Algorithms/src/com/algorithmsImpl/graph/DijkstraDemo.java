package com.algorithmsImpl.graph;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class DijkstraDemo {
    
    static class Graph {
        private int n;
        private Vertex[] v;
        private ArrayList<ArrayList<Edge>> e;
        
        public Graph(int n) {
            this.n = n;
            this.v = new Vertex[n];
            this.e = new ArrayList<>();
            initEdge(n);
        }

        private void initEdge(int n) {
            for (int i = 0; i < n; i++) {
                ArrayList<Edge> arrayList = new ArrayList<>();
                this.e.add(arrayList);
            }
        }

        static class Vertex implements Comparable<Vertex>{
            private int i;
            private char name;
            private int p;  // parent
            private int d;  // degree

            public Vertex(int i, char name) {
                this.i = i;
                this.name = name;
            }

            @Override
            public int compareTo(Vertex o) {
                return this.d-o.d;
            }
        }
        
        static class Edge {
            private int v;    // vertex
            private int w;    // weight
            public Edge(int v, int w) {
                this.v = v;
                this.w = w;
            }
        }

        private void setVertex(int i, char name) {
            Vertex vertex = new Vertex(i, name);
            this.v[i] = vertex;
        }

        private void setEdge(int i, Edge edge) {
            ArrayList<Edge> curEdgeList = this.e.get(i);
            curEdgeList.add(edge);
        }
    }
    
    private void dijkstraShortPath(Graph g, int s) {
        initializeSingleSource(g, s);
        Graph.Vertex[] S = new Graph.Vertex[g.n];
        PriorityQueue<Graph.Vertex> priorityQueue = new PriorityQueue();
        // add all node into priorityQueue
        for (Graph.Vertex vertex :
                g.v) {
            priorityQueue.add(vertex);
        }
        // start
        int i = -1;
        while (!priorityQueue.isEmpty()) {
            Graph.Vertex u = priorityQueue.poll();
            S[++i] = u;
            // get all edges with current node
            ArrayList<Graph.Edge> curEdgeList = g.e.get(u.i);
            for (Graph.Edge edgeObj :
                    curEdgeList) {
                Graph.Vertex v = g.v[edgeObj.v];
                // check whether current v node is in queue
                if (priorityQueue.contains(v)) {
                    int w = edgeObj.w;
                    if (v.d > u.d + w) {
                        v.d = u.d + w;
                        v.p = u.i;
                    }
                    //implicitly sort in priorityQueue
                    priorityQueue.remove(v);
                    priorityQueue.add(v);
                }
            }
        }
        printS(S,g.v);
    }

    private void printS(Graph.Vertex[] res, Graph.Vertex[] vertices) {
        for (Graph.Vertex v:
             res) {
            if (v.p != -1) {
                System.out.println("[v,d,p] -- > " + v.name+","+v.d+","+ vertices[v.p].name);
            }else {
                System.out.println("[v,d,p] -- > " + v.name+","+v.d+",");
            }
        }
    }

    private void initializeSingleSource(Graph g, int s) {
        for (int i = 0; i < g.n; i++) {
            g.v[i].p = -1;
            g.v[i].d = Integer.MAX_VALUE;
        }
        g.v[s].d = 0;
    }

    public static void main(String[] args) {
        DijkstraDemo dDemo = new DijkstraDemo();
        Graph graph = new Graph(5);
        graph.setVertex(0,'s');
        graph.setVertex(1,'t');
        graph.setVertex(2,'x');
        graph.setVertex(3,'y');
        graph.setVertex(4,'z');

        graph.setEdge(0,new Graph.Edge(1,10));
        graph.setEdge(0,new Graph.Edge(3,5));
        graph.setEdge(1,new Graph.Edge(2,1));
        graph.setEdge(1,new Graph.Edge(3,2));
        graph.setEdge(2,new Graph.Edge(4,4));
        graph.setEdge(3,new Graph.Edge(1,3));
        graph.setEdge(3,new Graph.Edge(2,9));
        graph.setEdge(3,new Graph.Edge(4,2));
        graph.setEdge(4,new Graph.Edge(0,7));
        graph.setEdge(4,new Graph.Edge(2,6));

        dDemo.dijkstraShortPath(graph,0);
    }

}
