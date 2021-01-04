package com.algorithmsImpl.graph;

import java.sql.Struct;

public class BellmanFordDemo {

    static class Graph{
        private char[] vertexes;
        private int[][] edges;

        private int[] pIndex;
        private int[] d;

        public Graph(int n){
            vertexes=new char[n];
            edges=new int[n][n];
            initEdges(n);
            pIndex=new int[n];
            d=new int[n];
        }

        public void initEdges(int n){
            for (int i = 0; i < edges.length; i++) {
                for (int j = 0; j < edges[i].length; j++) {
                    edges[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        private void setVertexes(int vertexIndex, char vertexName){
            vertexes[vertexIndex] = vertexName;
        }

        private void setEdges(char r, char c ,int edgesLen){
            int rIndex = this.findVertexPosition(r);
            int cIndex = this.findVertexPosition(c);
            if (rIndex != -1 && cIndex != -1){
                edges[rIndex][cIndex] = edgesLen;
            }
        }

        private int findVertexPosition(char vertexName){
            for (int i = 0; i < vertexes.length; i++) {
                if (vertexes[i] == vertexName) {
                    return i;
                }
            }
            return -1;
        }

    }

    private boolean bellman_ford(Graph g, char s){
        initializeSingleSource(g,s);
        for (int i = 0; i < g.vertexes.length-1; i++) {
            for (int j = 0; j < g.edges.length; j++) {
                for (int k = 0; k < g.edges[j].length; k++) {
                    if (g.edges[j][k] != Integer.MAX_VALUE) {
                        int w = g.edges[j][k];
                        int uPosition = j;
                        int vPosition = k;
                        relax(g, uPosition, vPosition, w);
                    }
                }
            }
        }

        for (int u = 0; u < g.edges.length; u++) {
            for (int v = 0; v < g.edges[u].length; v++) {
                int w = g.edges[u][v];
                if (w != Integer.MAX_VALUE && g.d[v] > g.d[u] + w) {
                    return false;
                }
            }
        }
        return true;
    }

    private void relax(Graph g, int uPosition, int vPosition, int w) {
        if (g.d[vPosition] > g.d[uPosition] + w) {
            g.d[vPosition] = g.d[uPosition] + w;
            g.pIndex[vPosition] = uPosition;
        }
    }

    private void initializeSingleSource(Graph g, char s) {
        for (int i = 0; i < g.vertexes.length; i++) {
            g.pIndex[i] = Integer.MAX_VALUE;
            g.d[i] = Integer.MAX_VALUE;
        }
        int sPosition = g.findVertexPosition(s);
        g.d[sPosition] = 0;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.setVertexes(0,'s');
        graph.setVertexes(1,'t');
        graph.setVertexes(2,'x');
        graph.setVertexes(3,'y');
        graph.setVertexes(4,'z');

        graph.setEdges('s','t',6);
        graph.setEdges('s','y',7);
        graph.setEdges('t','y',8);
        graph.setEdges('t','z',-4);
        graph.setEdges('t','x',5);
        graph.setEdges('x','t',-2);
        graph.setEdges('y','x',-3);
        graph.setEdges('y','z',9);
        //graph.setEdges('z','x',7);
        //practice 24.1-1 p381
        //(z, x)=4 --> cycle(x->t->z)=-2 --> return false;
        graph.setEdges('z','x',4);
        graph.setEdges('z','s',2);
        BellmanFordDemo bellmanFordDemo = new BellmanFordDemo();
        boolean res = bellmanFordDemo.bellman_ford(graph, 's');
        System.out.println(res);

    }
}
