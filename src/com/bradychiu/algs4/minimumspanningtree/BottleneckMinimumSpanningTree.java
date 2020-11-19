package com.bradychiu.algs4.minimumspanningtree;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.UF;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Bottleneck minimum spanning tree. Given a connected edge-weighted graph,
 * design an efficient algorithm to find a minimum bottleneck spanning tree.
 * The bottleneck capacity of a spanning tree is the weights of its largest edge.
 * A minimum bottleneck spanning tree is a spanning tree of minimum bottleneck capacity.
 * <p>
 * Camerini's Algorithm
 * https://en.wikipedia.org/wiki/Minimum_bottleneck_spanning_tree#Camerini's_algorithm_for_undirected_graphs
 */

public class BottleneckMinimumSpanningTree {

    public BottleneckMinimumSpanningTree() {
    }

    public List<Edge> find(EdgeWeightedGraph G) {
        ArrayList<Edge> edges = new ArrayList<>();
        G.edges().forEach(edges::add);
        sort(edges);

        if (edges.size() == 1)
            return edges;

        ArrayList<Edge> result = new ArrayList<>();
        EdgeWeightedGraph smallGraph = new EdgeWeightedGraph(G.V());
        EdgeWeightedGraph bigGraph = new EdgeWeightedGraph(G.V());

        UF uf = new UF(G.V());
        Edge medianEdge = median(edges);

        for (Edge e : edges) {
            if (compare(e, medianEdge) <= 0) {
                smallGraph.addEdge(e);
                if (uf.find(e.either()) != uf.find(e.other(e.either()))) {
                    uf.union(e.either(), e.other(e.either()));
                    result.add(e);
                }
            } else {
                bigGraph.addEdge(e);
            }
        }

        /**
         * If a spanning tree exists in subgraph composed solely with edges in smaller edges set,
         * it then computes a MBST in the subgraph, a MBST of the subgraph is exactly a MBST of the original graph
         */
        if (uf.count() == 1)
            return find(smallGraph);

        /**
         * If a spanning tree does not exist, it combines each disconnected component into a new super vertex,
         * then computes a MBST in the graph formed by these super vertices and edges in the larger edges set.
         */

        EdgeWeightedGraph superVertexGraph = new EdgeWeightedGraph(G.V());
        int superVertexId = 0;
        Map<Integer, Integer> vertexId2SuperVertexId = new HashMap<>();

        for (Edge e : bigGraph.edges()) {
            int v = e.either();
            int w = e.other(e.either());

            int parentV = uf.find(v);
            int parentW = uf.find(w);

            if (!vertexId2SuperVertexId.containsKey(parentV))
                vertexId2SuperVertexId.put(parentV, superVertexId++);

            if (!vertexId2SuperVertexId.containsKey(parentW))
                vertexId2SuperVertexId.put(parentW, superVertexId++);

            if (vertexId2SuperVertexId.get(parentV) != vertexId2SuperVertexId.get(parentW))
                superVertexGraph.addEdge(new Edge(
                        vertexId2SuperVertexId.get(parentV),
                        vertexId2SuperVertexId.get(parentW),
                        e.weight()));
        }

        result.addAll(find(superVertexGraph));
        return result;
    }

    public void sort(List<Edge> edges) {
        Collections.sort(edges, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return compare(o1, o2);
            }
        });
    }

    private int compare(Edge o1, Edge o2) {
        if (o1.weight() == o2.weight()) {
            if (o1.either() == o2.either()) {
                return Integer.compare(o1.other(o1.either()), o2.other(o2.either()));
            }
            return Integer.compare(o1.either(), o2.either());
        }
        return Double.compare(o1.weight(), o2.weight());
    }

    private Edge median(List<Edge> edges) {
        int k = (edges.size() - 1) / 2;
        sort(edges);
        return edges.toArray(new Edge[0])[k];
    }

    public void printEdge(Edge e) {
        System.out.println(e.either() + "->" + e.other(e.either()) + " " + e.weight());
    }
}
