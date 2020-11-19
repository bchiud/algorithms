package com.bradychiu.algs4.minimumspanningtree;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BottleneckMinimumSpanningTreeTest {

    @Test
    void fixed() {
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
        BottleneckMinimumSpanningTree bmst = new BottleneckMinimumSpanningTree();
        List<Edge> result = bmst.find(graph);
        bmst.sort(result);
        result.iterator().forEachRemaining(e -> bmst.printEdge(e));
        assertEquals(7, result.get(result.size() - 1).weight());
    }
}