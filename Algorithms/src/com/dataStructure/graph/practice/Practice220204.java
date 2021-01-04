package com.dataStructure.graph.practice;

public class Practice220204 {

    static class Vertex {
        public int value;
        public Practice220201.COLOR color;
        public int d;   //degree
        public Practice220201.Vertex p;   //parent
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

    class GraphByMatrix {
        public Vertex[] vertexes;
        public int[][] edges;
        public int n;

        public GraphByMatrix(int v) {
            this.n = v;
            vertexes=new Vertex[n];
            for (int i = 0; i < n; i++) {
                vertexes[i]=new Vertex(i);
            }
            edges = new int[n][n];
        }

        public void addEdge(int start, int end) {
            if (start >= n || end >= n) {
                return;
            }
            edges[start][end] = 1;
        }

        public void removeEdge(int start, int end) {
            if (start >= n || end >= n) {
                return;
            }
            edges[start][end] = 0;
        }

        public void printGraph() {
            for (int i = 0; i < edges.length; i++) {
                for (int j = 0; j < edges.length; j++) {
                    System.out.print(edges[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}
