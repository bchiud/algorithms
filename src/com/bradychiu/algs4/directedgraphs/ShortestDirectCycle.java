package com.bradychiu.algs4.directedgraphs;

import edu.princeton.cs.algs4.Digraph;

import java.util.Arrays;
import java.util.Stack;

/**
 * Shortest directed cycle.
 *
 * Given a digraph G, design an efficient algorithm to find a directed cycle with the minimum number of edges
 * (or report that the graph is acyclic). The running time of your algorithm should be at most proportional to V(E + V)
 * and use space proportional to E + V, where V is the number of vertices and E is the number of edges.
 *
 * Hint: run BFS from each vertex.
 */

// TODO: understand this
public class ShortestDirectCycle {

    private boolean[] marked;   // marked[v] = has vertex v been marked?
    private boolean[] onStack;  // onStack[v] = is vertex on the stack?
    private int[] edgeTo;       // edgeTo[v] = previous vertex on path to v
    Stack<Integer> cycle;       // directed cycle (or null if no such cycle)

    public ShortestDirectCycle(Digraph G) {
        marked = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];

        for (int v = 0; v < G.V(); v++)
            if (!marked[v])
                dfs(G, v);
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        onStack[v] = true;

        for (int a : G.adj(v)) {
            if (!marked[a]) {
                edgeTo[a] = v;
                dfs(G, a);
            } else if (onStack[a]) {
                cycle = new Stack<>();
                for (int x = v; x != a; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(a);
                cycle.push(v);
            }
        }

        onStack[v] = false;

        System.out.println("marked: " + Arrays.toString(marked));
        System.out.println("edgeTo: " + Arrays.toString(edgeTo));
        System.out.println("onStack: " + Arrays.toString(onStack));
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    public boolean hasCycle() {
        return cycle != null;
    }
}
