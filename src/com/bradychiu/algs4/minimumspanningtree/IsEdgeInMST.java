package com.bradychiu.algs4.minimumspanningtree;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.UF;

import java.util.ArrayList;
import java.util.List;

/**
 * Is an edge in a MST. Given an edge-weighted graph G and an edge e,
 * design a linear-time algorithm to determine whether ee appears in some MST of G.
 * <p>
 * If all edges with weight smaller than that of edge e do not create an MST, then edge e must be part of the MST
 */

public class IsEdgeInMST {

    private EdgeWeightedGraph G;
    private boolean[] marked;
    private double weight;
    private int target;

    public IsEdgeInMST(EdgeWeightedGraph G) {
        this.G = G;
    }

    public boolean unionfind(Edge e) {
        UF uf = new UF(G.V());
        for (Edge cur : G.edges())
            if (cur.compareTo(e) < 0)
                uf.union(cur.either(), cur.other(cur.either()));

        return uf.find(e.either()) != uf.find(e.other(e.either()));
    }

    public boolean dfs(Edge e) {
        marked = new boolean[G.E()];
        weight = e.weight();
        target = e.other(e.either());
        return dfs(e, e.either());
    }

    private boolean dfs(Edge e, int v) {
        marked[v] = true;
        for (Edge cur : G.adj(v)) {
            int w = cur.other(v);
            if (Double.compare(cur.weight(), weight) >= 0 || marked[w]) // skip condition
                continue;
            if (w == target || !dfs(e, w)) // fail condition - confirmed cycle
                return false;
        }
        return true;
    }
}
