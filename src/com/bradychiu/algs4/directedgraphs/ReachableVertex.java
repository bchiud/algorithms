package com.bradychiu.algs4.directedgraphs;

import edu.princeton.cs.algs4.DepthFirstOrder;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.TarjanSCC;

import java.util.Stack;

/**
 * DAG: Design a linear-time algorithm to determine whether a DAG has a vertex that is reachable from every other
 * vertex, and if so, find one.
 * <p>
 * Digraph: Design a linear-time algorithm to determine whether a digraph has a vertex that is reachable from every
 * other vertex, and if so, find one.
 * <p>
 * Hint (DAG): compute the outdegree of each vertex.
 * <p>
 * Hint (digraph): compute the strong components and look at the kernel DAG (the digraph that results when you contract
 * each strong component to a single vertex).
 */
public class ReachableVertex {

    private boolean[] dagMarked;
    private boolean[] reversePostMarked;
    private boolean[] sccMarked;
    private int dagMarks;
    private int sccCount;
    private int[] scc;
    private Digraph kernalDAG;
    private Stack<Integer> reversePost;
    private Stack<Integer> reversePostStackClone;

    public ReachableVertex() {
    }

    /**
     * 1) find vertex with 0 outdegree
     * 2) dfs reverse graph from given vertex and see if every vertex is visited
     */
    public int findReachableVertexDAG(Digraph G) {
        Digraph reverseG = G.reverse();

        for (int v = 0; v < G.V(); v++) {
            if (G.outdegree(v) == 0) {
                dagMarks = 0;
                dagMarked = new boolean[reverseG.V()];
                dagDfs(reverseG, v);
                if (dagMarks == G.V())
                    return v;
            }
        }

        return -1;
    }

    private void dagDfs(Digraph G, int v) {
        dagMarked[v] = true;
        dagMarks++;

        for (int w : G.adj(v))
            if (!dagMarked[w])
                dagDfs(G, w);
    }

    /**
     * 1) compute reverse postorder
     * 2) compute SCC of
     * 2) find SCC vertex with 0 outdegree
     */
    public int findReachableVertexDigraph(Digraph G) {
        // compute reverse postorder
        reversePost = new Stack<>();
        reversePostMarked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!reversePostMarked[v])
                reversePostDfs(G.reverse(), v);

        // compute SCC in reverse postorder
        sccMarked = new boolean[G.V()];
        scc = new int[G.V()];
        sccCount = 0;
        reversePostStackClone = (Stack<Integer>) reversePost.clone();
        while (!reversePostStackClone.empty()) {
            int v = reversePostStackClone.pop();
            if (!sccMarked[v]) {
                sccDfs(G, v);
                sccCount++;
            }
        }

        // create DAG of SCC in reverse postorder
        kernalDAG = new Digraph(sccCount);
        reversePostStackClone = (Stack<Integer>) reversePost.clone();
        while (!reversePostStackClone.empty()) {
            int v = reversePostStackClone.pop();
            for (int w : G.adj(v))
                if (scc[v] != scc[w])
                    kernalDAG.addEdge(scc[v], scc[w]);
        }

        // find G vertex associated with kernelDAG's vertex
        int kernalDAGvertex = findReachableVertexDAG(kernalDAG);
        for (int v = 0; v < G.V(); v++)
            if (scc[v] == kernalDAGvertex)
                return v;

        return -1;
    }

    private void reversePostDfs(Digraph G, int v) {
        reversePostMarked[v] = true;

        for (int w : G.adj(v))
            if (!reversePostMarked[w])
                reversePostDfs(G, w);

        reversePost.push(v);
    }

    private void sccDfs(Digraph G, int v) {
        sccMarked[v] = true;
        scc[v] = sccCount;

        for (int w : G.adj(v))
            if (!sccMarked[w])
                sccDfs(G, w);
    }
}