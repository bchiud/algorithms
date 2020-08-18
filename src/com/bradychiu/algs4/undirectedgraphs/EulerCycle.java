package com.bradychiu.algs4.undirectedgraphs;

import edu.princeton.cs.algs4.*;

/**
 * Euler cycle. An Euler cycle in a graph is a cycle (not necessarily simple) that uses every edge in the graph exactly one.
 * <p>
 * Show that a connected graph has an Euler cycle if and only if every vertex has even degree.
 * Design a linear-time algorithm to determine whether a graph has an Euler cycle, and if so, find one.
 *
 * Hint: use depth-first search and piece together the cycles you discover.
 */

public class EulerCycle {

    private Graph G;
    private Stack<Integer> cycle = new Stack<Integer>();

    private static class Edge {
        private final int v;
        private final int w;
        private boolean isUsed;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
            isUsed = false;
        }

        public int other(int vertex) {
            if (vertex == v)
                return w;
            else if (vertex == w)
                return v;
            else
                throw new IllegalArgumentException("Illegal endpoint");
        }
    }

    public EulerCycle(Graph G) {
        this.G = G;
        findEulerCycle();
    }

    public boolean isEulerCycle() {
        return cycle != null;
    }

    public Iterable<Integer> getEulerCycle() {
        return cycle;
    }

    private void findEulerCycle() {
        if (G.E() == 0)
            cycle = null;

        for (int v = 0; v < G.V(); v++)
            if (G.degree(v) % 2 != 0)
                cycle = null;

        Queue<EulerCycle.Edge>[] adj = (Queue<EulerCycle.Edge>[]) new Queue[G.V()];
        for (int v = 0; v < G.V(); v++)
            adj[v] = new Queue<EulerCycle.Edge>();

        for (int v = 0; v < G.V(); v++) {
            int selfLoops = 0;
            for (int w : G.adj(v)) {
                if (v == w) {
                    if (selfLoops % 2 == 0) {
                        EulerCycle.Edge e = new EulerCycle.Edge(v, w);
                        adj[v].enqueue(e);
                        adj[w].enqueue(e);
                    }
                    selfLoops++;
                }
                else if (v < w) {
                    EulerCycle.Edge e = new EulerCycle.Edge(v, w);
                    adj[v].enqueue(e);
                    adj[w].enqueue(e);
                }
            }
        }

        int s = nonIsolatedVertex(G);
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(s);

        cycle = new Stack<Integer>();
        while (!stack.isEmpty()) {
            int v = stack.pop();
            while (!adj[v].isEmpty()) {
                EulerCycle.Edge edge = adj[v].dequeue();
                if (edge.isUsed) continue;
                edge.isUsed = true;
                stack.push(v);
                v = edge.other(v);
            }
            cycle.push(v);
        }

        if (cycle.size() != G.E() + 1)
            cycle = null;
    }

    private int nonIsolatedVertex(Graph G) {
        for (int v = 0; v < G.V(); v++)
            if (G.degree(v) > 0)
                return v;
        return -1;
    }
}
