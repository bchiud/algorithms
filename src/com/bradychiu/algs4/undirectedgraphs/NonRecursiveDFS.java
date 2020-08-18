package com.bradychiu.algs4.undirectedgraphs;

import edu.princeton.cs.algs4.DepthFirstSearch;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Stack;

import java.util.Iterator;

/**
 * "Nonrecursive depth-first search. Implement depth-first search in an undirected graph without using recursion."
 */

public class NonRecursiveDFS {

    private boolean[] marked;
    private Stack<Integer> stack;

    public NonRecursiveDFS(Graph G, int start) {
        marked = new boolean[G.V()];
        stack = new Stack<>();

        Iterator<Integer>[] adj = new Iterator[G.V()];

        for (int v = 0; v < G.V(); v++) {
            adj[v] = G.adj(v).iterator();
        }

        stack.push(start);
        while(!stack.isEmpty()) {
            int current = stack.peek();

            if (adj[current].hasNext()) {
                int neighbor = adj[current].next();
                if (!marked[neighbor]) {
                    marked[neighbor] = true;
                    stack.push(neighbor);
                }
            } else {
                stack.pop();
            }
        }
    }

    protected boolean marked(int v) {
        return marked[v];
    }
}
