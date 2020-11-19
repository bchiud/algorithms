package com.bradychiu.algs4.shortestpath;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.MinPQ;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Monotonic shortest path. Given an edge-weighted digraph G, design an E log E algorithm to find a monotonic shortest
 * path from s to every other vertex. A path is monotonic if the sequence of edge weights along the path are either
 * strictly increasing or strictly decreasing.
 * <p>
 * Hint: relax edges in ascending order to find a best monotonically increasing path;
 * relax edges in descending order to find a best monotonically decreasing path.
 * <p>
 * 1)  We would order the edges in ascending order, then relax them in that order.
 * "Relax" just means update the weight of the node at the endpoint of an edge to be the weight of the edge plus the
 * weight of its start point node if that sum is less than the current value. This is the same as relaxing an edge in Dijkstra's.
 * This will always yield the shortest ascending path (and it can be made strictly ascending
 * if we update the node weights at the same time for all equally valued edges).
 * <p>
 * 1. Sort edges of each vertex, ascending or descending
 * 2. relax edges using Dijkstra algorithm, check monotonic condition before check distTo
 */

public class MonotonicShortestPath {

    private double distTo[];
    private DirectedEdge edgeTo[];
    private Map<Integer, PriorityQueue<DirectedEdge>> vertexEdges;

    public MonotonicShortestPath(EdgeWeightedDigraph G, int source) {
        this(G, source, true);
    }

    public MonotonicShortestPath(EdgeWeightedDigraph G, int source, boolean ascending) {

        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        vertexEdges = new HashMap<>();

        for (int v = 0; v < distTo.length; v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[source] = 0.0;

        Comparator<DirectedEdge> comparator = new Comparator<DirectedEdge>() {
            @Override
            public int compare(DirectedEdge o1, DirectedEdge o2) {
                if (ascending) {
                    if (o1.weight() > o2.weight()) {
                        return -1;
                    } else if (o1.weight() < o2.weight()) {
                        return 1;
                    } else {
                        return 0;
                    }
                } else {
                    if (o1.weight() < o2.weight()) {
                        return -1;
                    } else if (o1.weight() > o2.weight()) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            }
        };


        for (int v = 0; v < G.V(); v++) {
            PriorityQueue<DirectedEdge> pq = new PriorityQueue<>(comparator);
            for (DirectedEdge e : G.adj(v))
                pq.add(e);
            vertexEdges.put(v, pq);
        }

        int v = source;
        for (DirectedEdge e : )


    }
}
