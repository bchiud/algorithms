package com.bradychiu.algs4.minimumspanningtree;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.PrimMST;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MinWeightFeedbackEdgeSetTest {

    @Test
    void test() {
        // Graph: https://graphonline.ru/en/?graph=irsNKYZxLkDqMbRg
        EdgeWeightedGraph graph = new EdgeWeightedGraph(7);
        graph.addEdge(new Edge(0, 1, 7));
        graph.addEdge(new Edge(1, 2, 8));
        graph.addEdge(new Edge(1, 4, 7));
        graph.addEdge(new Edge(4, 6, 7));
        graph.addEdge(new Edge(4, 5, 8));
        graph.addEdge(new Edge(1, 3, 9));
        graph.addEdge(new Edge(5, 6, 11));
        graph.addEdge(new Edge(3, 4, 15));
        graph.addEdge(new Edge(0, 3, 5));
        graph.addEdge(new Edge(2, 4, 5));
        graph.addEdge(new Edge(3, 5, 6));

        Set<Edge> test = new MinWeightFeedbackEdgeSet(graph).mwfes();
        EdgeWeightedGraph reverseWeight = new EdgeWeightedGraph(graph.V());
        Map<Edge, Edge> map = new HashMap<>();
        for (Edge e : graph.edges()) {
            Edge rev = new Edge(e.either(), e.other(e.either()), -e.weight());
            reverseWeight.addEdge(rev);
            map.put(rev, e);
        }
        PrimMST mst = new PrimMST(reverseWeight);
        Set<Edge> expect = new HashSet<>();
        mst.edges().forEach(expect::add);
        for (Map.Entry<Edge, Edge> entry : map.entrySet()) {
            assertEquals(!expect.contains(entry.getKey()), test.contains(entry.getValue()));
        }
    }

}