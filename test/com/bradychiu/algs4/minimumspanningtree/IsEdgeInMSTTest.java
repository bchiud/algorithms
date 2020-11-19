package com.bradychiu.algs4.minimumspanningtree;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.PrimMST;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IsEdgeInMSTTest {

    private EdgeWeightedGraph graph;
    private List<Edge> expected;

    @BeforeEach
    void setup() {
        // Graph: https://graphonline.ru/en/?graph=irsNKYZxLkDqMbRg
        graph = new EdgeWeightedGraph(7);
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

        expected = new ArrayList<>();
        PrimMST pmst = new PrimMST(graph);
        pmst.edges().forEach(expected::add);
    }

    @Test
    void unionfind() {
        List<Edge> actual = new ArrayList<>();
        IsEdgeInMST ieim = new IsEdgeInMST(graph);
        for (Edge e : graph.edges())
            if(ieim.unionfind(e))
                actual.add(e);

        System.out.println("Expected: " + expected);
        System.out.println("Actual: " + actual);

        assertEquals(new HashSet<Edge>(expected), new HashSet<Edge>(actual));
    }

    @Test
    void dfs() {
        List<Edge> actual = new ArrayList<>();
        IsEdgeInMST ieim = new IsEdgeInMST(graph);
        for (Edge e : graph.edges())
            if(ieim.dfs(e))
                actual.add(e);

        System.out.println("Expected: " + expected);
        System.out.println("Actual: " + actual);

        assertEquals(new HashSet<Edge>(expected), new HashSet<Edge>(actual));
    }

}