package com.bradychiu.algs4.wordnet;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;

import java.util.List;

public class SAP {

    private static final int NOT_FOUND = -1;
    private final Digraph G;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        this.G = new Digraph(G);
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        return length(List.of(v), List.of(w));
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        return ancestor(List.of(v), List.of(w));
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        validateVertexes(v, w);

        return sap(v, w).shortestPathDist;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        validateVertexes(v, w);

        return sap(v, w).ancestor;
    }

    private Result sap(Iterable<Integer> v, Iterable<Integer> w) {
        if (!v.iterator().hasNext()|| !w.iterator().hasNext())
            return new Result(-1, -1);

        BreadthFirstDirectedPaths vBfs = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths wBfs = new BreadthFirstDirectedPaths(G, w);

        int ancestor = NOT_FOUND;
        int shortestPathDist = Integer.MAX_VALUE;
        for (int curV = 0; curV < G.V(); curV++) {
            if (vBfs.hasPathTo(curV) && wBfs.hasPathTo(curV)) {
                int pathDist = vBfs.distTo(curV) + wBfs.distTo(curV);
                if (shortestPathDist > pathDist) {
                    shortestPathDist = vBfs.distTo(curV) + wBfs.distTo(curV);
                    ancestor = curV;
                }
            }
        }

        return new Result(ancestor == NOT_FOUND ? -1 : shortestPathDist, ancestor);
    }

    private void validateVertex(Integer v) {
        if (v == null || v < 0 || v >= G.V())
            throw new IllegalArgumentException("vertex must be between 0 and " + (G.V() - 1));
    }

    private void validateVertexes(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null)
            throw new IllegalArgumentException();
        v.forEach(this::validateVertex);
        w.forEach(this::validateVertex);
    }

    private class Result {
        int shortestPathDist;
        int ancestor;

        Result(int shortestPathDist, int ancestor) {
            this.shortestPathDist = shortestPathDist;
            this.ancestor = ancestor;
        }
    }

    // do unit testing of this class
    public static void main(String[] args) {

    }
}
