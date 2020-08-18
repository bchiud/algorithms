package com.bradychiu.algs4.directedgraphs;

import edu.princeton.cs.algs4.Digraph;
import org.junit.jupiter.api.Test;

import java.util.Stack;

class ShortestDirectCycleTest {

    @Test
    void hasCycle() {
        Digraph G = new Digraph(13);
        G.addEdge(0, 1);
        G.addEdge(0, 5);
        G.addEdge(2, 0);
        G.addEdge(2, 3);
        G.addEdge(3, 2);
        G.addEdge(3, 5);
        G.addEdge(4, 2);
        G.addEdge(4, 3);
        G.addEdge(5, 4);
        G.addEdge(6, 0);
        G.addEdge(6, 4);
        G.addEdge(6, 8);
        G.addEdge(6, 9);
        G.addEdge(7, 6);
        G.addEdge(7, 9);
        G.addEdge(8, 6);
        G.addEdge(9, 10);
        G.addEdge(9, 11);
        G.addEdge(10, 12);
        G.addEdge(11, 4);
        G.addEdge(11, 12);
        G.addEdge(12, 9);

        ShortestDirectCycle sdc = new ShortestDirectCycle(G);
        assert(sdc.hasCycle());

        Stack<Integer> expected = new Stack<>();
        expected.push(8);
        expected.push(6);
        expected.push(8);
        Iterable<Integer> actual = sdc.cycle();
        assert(expected.equals(actual));
    }

    @Test
    void directedAcyclicGraph() {
        Digraph G = new Digraph(7);
        G.addEdge(0,1);
        G.addEdge(0,2);
        G.addEdge(0,5);
        G.addEdge(1,4);
        G.addEdge(3,2);
        G.addEdge(3,4);
        G.addEdge(3,5);
        G.addEdge(3,6);
        G.addEdge(5,2);
        G.addEdge(6,4);

        ShortestDirectCycle sdc = new ShortestDirectCycle(G);
        assert(!sdc.hasCycle());
    }
}