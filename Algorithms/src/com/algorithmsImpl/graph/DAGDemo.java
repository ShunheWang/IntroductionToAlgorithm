package com.algorithmsImpl.graph;

import java.util.ArrayDeque;
import java.util.Deque;

public class DAGDemo {

    static class Graph {
        private int n;
        private char[] vertexes;
        private int[][] edges;
        private int[] inputDegree;
        private int[] d;    // degree
        private int[] p;    // parent
        private int[] pathNum;  // path to number in practice 24.2-4, p383

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
    private Deque<Integer> topologicallySort1(Graph g){
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
            System.out.println("Deque --> "+ resQueue);
            return resQueue;
        }else {
            System.out.println("Graph has cyclic");
            return null;
        }
    }

    private void initializeSingleSource(Graph g, int s) {
        g.d = new int[g.n];
        g.p = new int[g.n];
        for (int i = 0; i < g.n; i++) {
            g.d[i] = Integer.MAX_VALUE;
            g.p[i] = Integer.MAX_VALUE;
        }
        g.d[s] = 0;
    }

    private void initializePathNum(Graph g, int s) {
        g.pathNum = new int[g.n];
        for (int i = 0; i < g.n; i++) {
            g.pathNum[i] = 0;
        }
        g.pathNum[s] = 1;
    }

    private void DdgSort(Graph g, int s) {
        Deque<Integer> deque = topologicallySort1(g);
        if (deque == null) {
            return;
        }else {
            initializeSingleSource(g, s);
            initializePathNum(g, deque.peek()); // practice 24.2-4
            while (!deque.isEmpty()) {
                Integer uIndex = deque.pop();
                for (int vIndex = 0; vIndex < g.n; vIndex++) {
                    if (g.edges[uIndex][vIndex] != Integer.MAX_VALUE) {
                        int w = g.edges[uIndex][vIndex];
                        relax(g,uIndex,vIndex,w);
                        // practice 24.2-4
                        g.pathNum[vIndex] += g.pathNum[uIndex];
                    }// if u --> v has edge
                }
            }
        }

        for (int i = 0; i < g.n; i++) {
            char v = g.vertexes[i];
            int d = g.d[i];
            char p;
            if (g.p[i] != Integer.MAX_VALUE) {
                p = g.vertexes[g.p[i]];
            }else {
                p = ' ';
            }
            // practice 24.2-4
            int pathNum = g.pathNum[i];
            System.out.println("[v,d,p,path] -- > "+v+" ,"+d+" ,"+p+" ,"+pathNum);
        }

    }

    private void relax(Graph g,Integer uIndex, int vIndex, int w) {
        if (g.d[uIndex] == Integer.MAX_VALUE){
            return;
        }else {
            if (g.d[vIndex] > g.d[uIndex] + w) {
                g.d[vIndex] = g.d[uIndex] + w;
                g.p[vIndex] = uIndex;
            }
        }
    }

    public static void main(String[] args) {
        DAGDemo dagDemo = new DAGDemo();
        Graph graph = new Graph(6);
        // add vertex
        graph.setVertexes(0,'r');
        graph.setVertexes(1,'s');
        graph.setVertexes(2,'t');
        graph.setVertexes(3,'x');
        graph.setVertexes(4,'y');
        graph.setVertexes(5,'z');


        // add edge
        graph.setEdges('r','s',5);
        graph.setEdges('r','t',3);
        graph.setEdges('s','t',2);
        graph.setEdges('s','x',6);
        graph.setEdges('t','x',7);
        graph.setEdges('t','y',4);
        graph.setEdges('t','z',2);
        graph.setEdges('x','y',-1);
        graph.setEdges('x','z',1);
        graph.setEdges('y','z',-2);

        dagDemo.DdgSort(graph, 1);
    }

}
