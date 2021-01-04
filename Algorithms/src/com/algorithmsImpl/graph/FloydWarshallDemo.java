package com.algorithmsImpl.graph;

public class FloydWarshallDemo {

    static class Graph {
        private static int n;
        private static int[] vertices;
        private static int[][] edges;
        private static int[][] prevNode;

        public Graph (int n){
            this.n = n;
            this.vertices = new int[n];
            this.edges = new int[n][n];
            this.prevNode = new int[n][n];
            initEdgeAndPrevNode();
        }

        private static void initEdgeAndPrevNode() {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    edges[i][j] = Integer.MAX_VALUE;
                    prevNode[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        private static void setVertices (int i) {
            vertices[i] = i;
        }

        private static void setEdges (int i, int j, int w) {
            edges[i][j] = w;
            if (edges[i][j] != 0) {
                prevNode[i][j] = i;
            }
        }
    }

    private static void floydWarshall (Graph g){
        int n = g.n;
        // D(0)
        int[][] D = g.edges;
        int[][] tempD = null;
        int[][] tempPrevNode = g.prevNode;

//        // print the previous node
//        int record= 0;
//        displayShortPathMatrix(record, D);
//        displayPrevNodeMatrix(record, tempPrevNode);

        for (int k = 0; k < n; k++) {
            tempD = new int[n][n];
            // start
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (D[i][k] != Integer.MAX_VALUE && D[k][j] != Integer.MAX_VALUE) {
                        if (min(D[i][j],D[i][k]+D[k][j])) {
                            tempD[i][j] = D[i][k]+D[k][j];
                            tempPrevNode[i][j] = k;
                        }else {
                            tempD[i][j] = D[i][j];
                        }
                    }else {
                        tempD[i][j] = D[i][j];
                    }
                }
            }
            D = tempD;
//            // print the 2d_array
//            record++;
//            displayShortPathMatrix(record, tempD);
//            // print the previous node
//            displayPrevNodeMatrix(record, tempPrevNode);
        }

        // print all short path between 2 node
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                printShortestPath(tempPrevNode,i,j);
                System.out.print(j);
                System.out.println();
            }
        }
    }


    private static void displayPrevNodeMatrix(int record, int[][] d) {
        System.out.println("PrevNode "+record+" --> ");
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d.length; j++) {
                if (d[i][j] != Integer.MAX_VALUE) {
                    System.out.print(d[i][j]+1+" ");
                }else {
                    System.out.print("NIL ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void displayShortPathMatrix(int record, int[][] d) {
        System.out.println("D "+record+" Short path --> ");
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d.length; j++) {
                if (d[i][j] != Integer.MAX_VALUE) {
                    System.out.print(d[i][j]+" ");
                }else {
                    System.out.print("Max ");
                }

            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean min(int x, int y) {
        if (x > y) {
            return true;
        }
        return false;
    }

    private static void printShortestPath(int[][] prevNodes, int startNode, int endNode) {
        int midNode = prevNodes[startNode][endNode];
        if (midNode == Integer.MAX_VALUE) {
            System.out.print(startNode+" --> "+ endNode +" has no path");
        }else if (midNode != startNode) {
            printShortestPath(prevNodes,startNode,midNode);
            printShortestPath(prevNodes,midNode,endNode);
        }else {
            System.out.print(startNode+" --> ");
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.setVertices(0);
        graph.setVertices(1);
        graph.setVertices(2);
        graph.setVertices(3);
        graph.setVertices(4);

        graph.setEdges(0,0,0);
        graph.setEdges(0,1,3);
        graph.setEdges(0,2,8);
        graph.setEdges(0,4,-4);
        graph.setEdges(1,1,0);
        graph.setEdges(1,3,1);
        graph.setEdges(1,4,7);
        graph.setEdges(2,1,4);
        graph.setEdges(2,2,0);
        graph.setEdges(3,0,2);
        graph.setEdges(3,2,-5);
        graph.setEdges(3,3,0);
        graph.setEdges(4,3,6);
        graph.setEdges(4,4,0);

        floydWarshall(graph);
    }
}
