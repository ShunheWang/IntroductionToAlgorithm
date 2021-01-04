package com.dataStructure.graph;

import java.util.ArrayDeque;
import java.util.Deque;

public class TopologicallySortDemo {

    static class Graph {
        private int n;
        private char[] vertexes;
        private int[][] edges;
        private int[] inputDegree;

        public Graph(int n) {
            this.n = n;
            vertexes = new char[n];
            edges = new int[n][n];
            initEdges(n);
            inputDegree = new int[n];
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
                // add input degree
                inputDegree[cIndex] = inputDegree[cIndex] + 1;
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

    /**
     * typologicallySort by dfs
     * @param g
     * @return
     */
    public Deque<Integer> topologicallySort2(Graph g){
        boolean[] isVisited = new boolean[g.n];
        boolean[] isLoopVisited = new boolean[g.n];
        Deque<Integer> stack = new ArrayDeque<>();
        // init isVisited & is LoopVisited
        for (int i = 0; i < g.n; i++) {
            isVisited[i] = false;
            isLoopVisited[i] = false;
        }
        boolean res = true;
        for (int i = 0; i < g.n; i++) {
            if (g.inputDegree[i] == 0 || isVisited[i] == false) {
                res = dfs(g, i, isVisited, isLoopVisited,stack);
                if (!res) {
                    break;
                }
            }
        }

        if (!res) {
            System.out.println("Has cyclic");
            return null;
        }else {
            System.out.println("Has no cyclic");
            return stack;
        }
    }

    private boolean dfs(Graph g, int index, boolean[] isVisited, boolean[] isLoopVisited, Deque<Integer> stack) {
        isVisited[index] = true;
        for (int i = 0; i < g.n; i++) {
            if (g.edges[index][i] != Integer.MAX_VALUE) {
                if (!isLoopVisited[i] && isVisited[i]) {
                    return false;
                }
                if (!isVisited[i]) {
                    boolean res = dfs(g,i,isVisited,isLoopVisited, stack);
                    if (!res) {
                        return false;
                    }   // once found out cycle --> return false from bottom to up
                }
            }
        }
        stack.push(index);
        isLoopVisited[index] = true;
        return true;
    }

    /**
     * typologicallySort by greedy
     * @return
     */
    private char[] topologicallySort1(Graph g){
        Deque<Integer> deque = new ArrayDeque<Integer>();
        // 1. dont break input-degree and edges --> then copy these two
        int[] copyInputDegree = new int[g.n];
        for (int i = 0; i < g.n; i++) {
            copyInputDegree[i] = g.inputDegree[i];
        }

        // 2. if input-degree of any node is 0, put node in to deque
        for (int i = 0; i < copyInputDegree.length; i++) {
            if (copyInputDegree[i] == 0) {
                deque.add(i);
            }
        }

        // 3. start find the node with input-degree is 0
        Deque<Integer> resQueue = new ArrayDeque<>();  // store typologically sort result
        while (!deque.isEmpty()) {
            Integer curIndex = deque.poll();
            resQueue.add(curIndex);
            for (int j = 0; j < g.edges.length; j++) {
                if (g.edges[curIndex][j] != Integer.MAX_VALUE) {
                    copyInputDegree[j] = copyInputDegree[j] - 1;
                    if (copyInputDegree[j] == 0) {
                        deque.add(j);
                    }
                }
            }
        }

        // 4. check whether graph has cyclic
        if (deque.isEmpty()) {
            System.out.println("Graph has no cyclic");
            System.out.println(resQueue);
        }else {
            System.out.println("Graph has cyclic");
        }

        return null;
    }

    public static void main(String[] args) {
        TopologicallySortDemo dagDemo = new TopologicallySortDemo();
        Graph graph = new Graph(14);
        // add vertex
        graph.setVertexes(0,'m');
        graph.setVertexes(1,'n');
        graph.setVertexes(2,'o');
        graph.setVertexes(3,'p');
        graph.setVertexes(4,'q');
        graph.setVertexes(5,'r');
        graph.setVertexes(6,'s');
        graph.setVertexes(7,'t');
        graph.setVertexes(8,'u');
        graph.setVertexes(9,'v');
        graph.setVertexes(10,'w');
        graph.setVertexes(11,'x');
        graph.setVertexes(12,'y');
        graph.setVertexes(13,'z');

        // add edge
        graph.setEdges('m','r',1);
        graph.setEdges('m','q',1);
        graph.setEdges('m','x',1);
        graph.setEdges('n','q',1);
        graph.setEdges('n','u',1);
        graph.setEdges('n','o',1);
        graph.setEdges('o','r',1);
        graph.setEdges('o','s',1);
        graph.setEdges('p','o',1);
        graph.setEdges('p','s',1);
        graph.setEdges('p','z',1);
        graph.setEdges('q','t',1);
        graph.setEdges('r','u',1);
        graph.setEdges('r','y',1);
        graph.setEdges('s','r',1);
        graph.setEdges('u','t',1);
        graph.setEdges('v','x',1);
        graph.setEdges('v','w',1);
        graph.setEdges('w','z',1);
        graph.setEdges('y','v',1);
        // graph.setEdges('t','n',1); // add cyclic

        //dagDemo.topologicallySort1(graph);
        // [0, 1, 3, 4, 2, 6, 5, 8, 12, 7, 9, 10, 11, 13]
        Deque<Integer> stack = dagDemo.topologicallySort2(graph);
        System.out.println(stack);
        // [3, 1, 2, 6, 0, 5, 12, 9, 11, 10, 13, 8, 4, 7]
    }
}
