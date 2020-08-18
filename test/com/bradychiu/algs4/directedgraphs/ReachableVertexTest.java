package com.bradychiu.algs4.directedgraphs;

import edu.princeton.cs.algs4.Digraph;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReachableVertexTest {

    private static ReachableVertex rv;

    @BeforeAll
    static void init() {
        rv = new ReachableVertex();
    }

    @Test
    void findReachableVertexDAG() {
        Digraph dag0 = new Digraph(4);
        dag0.addEdge(0, 2);
        dag0.addEdge(1, 3);
        dag0.addEdge(3, 2);
        assertEquals(2, rv.findReachableVertexDAG(dag0));

        // graph from 2:22 of https://www.coursera.org/learn/algorithms-part2/lecture/RAMNS/topological-sort
        Digraph dag1 = new Digraph(7);
        dag1.addEdge(0, 5);
        dag1.addEdge(0, 2);
        dag1.addEdge(0, 1);
        dag1.addEdge(3, 6);
        dag1.addEdge(3, 5);
        dag1.addEdge(3, 4);
        dag1.addEdge(5, 2);
        dag1.addEdge(6, 4);
        dag1.addEdge(6, 0);
        dag1.addEdge(3, 2);
        dag1.addEdge(1, 4);
        assertEquals(-1, rv.findReachableVertexDAG(dag1));

        dag1.addEdge(2, 1); // additional edge to make problem return true
        assertEquals(4, rv.findReachableVertexDAG(dag1));
    }

    @Test
    void findReachableVertexDigraph() {
        Digraph G0 = new Digraph(8);
        G0.addEdge(0, 1);
        G0.addEdge(1, 2);
        G0.addEdge(2, 3);
        G0.addEdge(3, 0);
        G0.addEdge(0, 5);
        G0.addEdge(4, 5);
        // G0.addEdge(5, 4);
        G0.addEdge(6, 7);
        G0.addEdge(7, 6);
        G0.addEdge(6, 5);
        assertEquals(5, rv.findReachableVertexDigraph(G0));

        // graph from 7:30 of https://www.coursera.org/learn/algorithms-part2/lecture/fC5Yw/strong-components
        Digraph G1 = new Digraph(13);
        G1.addEdge(0, 1);
        G1.addEdge(0, 5);
        G1.addEdge(2, 0);
        G1.addEdge(2, 3);
        G1.addEdge(3, 2);
        G1.addEdge(3, 5);
        G1.addEdge(4, 2);
        G1.addEdge(4, 3);
        G1.addEdge(5, 4);
        G1.addEdge(6, 0);
        G1.addEdge(6, 4);
        G1.addEdge(6, 8);
        G1.addEdge(6, 9);
        G1.addEdge(7, 6);
        G1.addEdge(7, 9);
        G1.addEdge(8, 6);
        G1.addEdge(9, 10);
        G1.addEdge(9, 11);
        G1.addEdge(10, 12);
        G1.addEdge(11, 4);
        G1.addEdge(11, 12);
        G1.addEdge(12, 9);
        assertEquals(1, rv.findReachableVertexDigraph(G1));

        Digraph G2 = new Digraph(4);
        G2.addEdge(0, 1);
        G2.addEdge(2, 1);
        G2.addEdge(2, 3);
        assertEquals(-1, rv.findReachableVertexDigraph(G2));
    }
}