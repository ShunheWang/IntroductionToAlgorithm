package com.dataStructure.graph;

public class GraphByMatrix {
    public int[][] edges;
    public int n;
    public GraphByMatrix(int v){
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

    public static void main(String[] args) {
        int[] vertexes={0,1,2,3,4,5};
        int n=vertexes.length;
        GraphByMatrix graphByMatrix=new GraphByMatrix(n);
        graphByMatrix.addEdge(1,2);
        graphByMatrix.addEdge(1,5);
        graphByMatrix.addEdge(2,1);
        graphByMatrix.addEdge(2,5);
        graphByMatrix.addEdge(2,4);
        graphByMatrix.addEdge(2,3);
        graphByMatrix.addEdge(3,2);
        graphByMatrix.addEdge(3,4);
        graphByMatrix.addEdge(4,2);
        graphByMatrix.addEdge(4,3);
        graphByMatrix.addEdge(4,5);
        graphByMatrix.addEdge(5,1);
        graphByMatrix.addEdge(5,2);
        graphByMatrix.addEdge(5,4);
        graphByMatrix.printGraph();
    }
}
