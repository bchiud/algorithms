package com.bradychiu.algs4.directedgraphs;

import edu.princeton.cs.algs4.*;

import java.util.Stack;

/**
 * Hamiltonian path in a DAG.
 * Given a directed acyclic graph, design a linear-time algorithm to determine whether it has a Hamiltonian path
 * (a simple path that visits every vertex), and if so, find one.
 * <p>
 * Hint: topological sort.
 * <p>
 * 1) assert is dag
 * 2) topologically sort
 */

public class HamiltonianPathinDAG {


    private boolean[] marked;
    private boolean[] onStack;
    private int[] edgeTo;
    private Digraph G;
    private Stack<Integer> cycle;
    private Stack<Integer> reversePost = new Stack<>();

    public HamiltonianPathinDAG(Digraph G) {
        this.G = G;
        checkForCycleAndFindPostOrder(this.G);
    }

    public boolean isHamiltonianPath() {
        if (hasCycle())
            throw new IllegalArgumentException("Not a Directed Acyclic Graph");

        return this.G.V() == reversePost.size();
    }

    public Iterable<Integer> hamiltonianPath() {
        if (hasCycle())
            throw new IllegalArgumentException("Not a Directed Acyclic Graph");

        return this.G.V() == reversePost.size() ? reversePost : null;
    }

    private Iterable<Integer> reversePost() {
        return reversePost;
    }

    private void checkForCycleAndFindPostOrder(Digraph G) {
        marked = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];

        for (int v = 0; v < G.V(); v++)
            if (!marked[v] && cycle == null)
                dfs(G, v);
    }

    private boolean hasCycle() {
        return cycle != null;
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        onStack[v] = true;

        for (int w : G.adj(v)) {
            // cycle already found
            if (cycle != null)
                return;

            // new vertex found
            else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }

            // w was previously visited during current stack, so must be cycle
            else if (onStack[w]) {
                cycle = new Stack<Integer>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }

        reversePost.push(v);
        onStack[v] = false;
    }
}
