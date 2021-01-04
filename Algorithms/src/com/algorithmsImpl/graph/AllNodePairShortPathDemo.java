package com.algorithmsImpl.graph;

/**
 * chapter 25
 */
public class AllNodePairShortPathDemo {

    static class Graph {
        private int n;
        private int[] vertexes;
        private int[][] edges;

        public Graph (int n) {
            this.n = n;
            vertexes = new int[n];
            edges = new int[n][n];
            initEdges();
        }

        private void initEdges() {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    edges[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        public void setVertexes(int i) {
            vertexes[i] = i;
        }

        public void setEdges(int i, int j, int w) {
            edges[i][j] = w;
        }
    }

    private static int[][] extendShortestPaths(int[][] curPathMatrix, int[][] edges) {
        int n = curPathMatrix.length;
        // new matrix to store result
        int[][] newMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newMatrix[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    if (curPathMatrix[i][k] != Integer.MAX_VALUE && edges[k][j] != Integer.MAX_VALUE) {
                        newMatrix[i][j] = min(newMatrix[i][j], curPathMatrix[i][k]+edges[k][j]);
                    }
                }
            }
        }
        return newMatrix;
    }

    private static int min(int path1, int path2) {
        if (path1 > path2) {
            return path2;
        }
        return path1;
    }

    /**
     * O(n4)
     * @param edges
     */
    private static void showAllShortestPaths(int[][] edges) {
        int n = edges.length;
        int[][] matrixPath = edges; // W1
        show(0,matrixPath);

        for (int i = 1; i < n-1; i++) {
            matrixPath = extendShortestPaths(matrixPath,edges);
            show(i, matrixPath);
        }
    }

    /**
     * O(n3logn)
     * @param edges
     */
    private static void FasterShowAllShortestPaths(int[][] edges) {
        int n = edges.length;
        int[][] matrixPath = edges; // W1
        show(0,matrixPath);
        int m = 1;
        while (m < n-1) {
            matrixPath = extendShortestPaths(matrixPath,matrixPath);
            show(m, matrixPath);
            m = 2*m;
        }
    }

    private static void show(int i, int[][] matrixPath) {
        System.out.println("current matrix "+i+" --> ");
        for (int j = 0; j < matrixPath.length; j++) {
            for (int k = 0; k < matrixPath.length; k++) {
                if (matrixPath[j][k] != Integer.MAX_VALUE) {
                    System.out.print(" "+matrixPath[j][k]);
                }else {
                    System.out.print(" Max");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.setVertexes(0);
        graph.setVertexes(1);
        graph.setVertexes(2);
        graph.setVertexes(3);
        graph.setVertexes(4);

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

        FasterShowAllShortestPaths(graph.edges);

    }
}
