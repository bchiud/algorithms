package com.bradychiu.algs4.undirectedgraphs;

import edu.princeton.cs.algs4.Graph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EulerCycleTest {

    @Test
    void isEulerCycle() {
        Graph G = new Graph(3);
        G.addEdge(0, 1);
        G.addEdge(0, 2);
        G.addEdge(1, 2);
        EulerCycle eulerCycle = new EulerCycle(G);
        assertEquals(true, eulerCycle.isEulerCycle());
    }

    @Test
    void isNotEulerCycle() {
        Graph G = new Graph(5);
        G.addEdge(0, 1);
        G.addEdge(0, 2);
        G.addEdge(1, 2);
        G.addEdge(2,3);
        EulerCycle eulerCycle = new EulerCycle(G);
        assertEquals(false, eulerCycle.isEulerCycle());
    }

    @Test
    void oneEdge() {
        Graph G = new Graph(2);
        G.addEdge(0, 1);
        EulerCycle eulerCycle = new EulerCycle(G);
        assertEquals(false, eulerCycle.isEulerCycle());
    }

    @Test
    void twoEdge() {
        Graph G = new Graph(2);
        G.addEdge(0, 1);
        G.addEdge(1,0);
        EulerCycle eulerCycle = new EulerCycle(G);
        assertEquals(true, eulerCycle.isEulerCycle());
    }
}