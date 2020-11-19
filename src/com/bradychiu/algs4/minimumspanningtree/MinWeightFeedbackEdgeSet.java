package com.bradychiu.algs4.minimumspanningtree;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.UF;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Minimum-weight feedback edge set.
 * A feedback edge set of a graph is a subset of edges that contains at least one edge from every cycle in the graph.
 * If the edges of a feedback edge set are removed, the resulting graph is acyclic.
 * Given an edge-weighted graph, design an efficient algorithm to find a feedback edge set of minimum weight.
 * Assume the edge weights are positive.
 *
 * Step 1: Compute MaxumumWeightSpanningForest O(E log E)
 *      Explanation: Perform Kruskal's algorithm with Negated natural comparator
 * Step 2: Remove forest edges from the graph O(E)
 */
public class MinWeightFeedbackEdgeSet {

    private EdgeWeightedGraph G;

    public MinWeightFeedbackEdgeSet(EdgeWeightedGraph G) {
        this.G = G;
    }

    public Set<Edge> mwfes() {
        Set<Edge> mwfes = new HashSet<>();
        Set<Edge> maxSpanningTree = KruskalMaxST();

        for (Edge e : G.edges()) {
            if (!maxSpanningTree.contains(e))
                mwfes.add(e);
        }

        return mwfes;
    }

    private Set<Edge> KruskalMaxST() {
        Set<Edge> maxSpanningTree = new HashSet<>();

        List<Edge> edges = new ArrayList<>();
        this.G.edges().forEach(edges::add);
        Collections.sort(edges, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return -Double.compare(o1.weight(), o2.weight());
            }
        });

        MaxPQ<Edge> maxPQ = new MaxPQ<>();
        edges.iterator().forEachRemaining(maxPQ::insert);

        UF uf = new UF(this.G.V());
        while (!maxPQ.isEmpty() && maxSpanningTree.size() < G.V() - 1) {
            Edge e = maxPQ.delMax();
            int v = e.either();
            int w = e.other(v);
            if (uf.find(v) != uf.find(w)) { // v-w does not create a cycle
                uf.union(v, w);  // merge v and w components
                maxSpanningTree.add(e);  // add edge e to mst
            }
        }

        return maxSpanningTree;
    }


}
