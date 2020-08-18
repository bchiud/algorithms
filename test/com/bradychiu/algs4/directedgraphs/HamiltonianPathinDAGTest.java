package com.bradychiu.algs4.directedgraphs;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class HamiltonianPathinDAGTest {

    @Test
    void hasHamiltonianPath() {
        Digraph dag = new Digraph(7);
        dag.addEdge(0, 5);
        dag.addEdge(0, 2);
        dag.addEdge(0, 1);
        dag.addEdge(3, 6);
        dag.addEdge(3, 5);
        dag.addEdge(3, 4);
        dag.addEdge(5, 2);
        dag.addEdge(6, 4);
        dag.addEdge(6, 0);
        dag.addEdge(3, 2);
        dag.addEdge(1, 4);
        HamiltonianPathinDAG hp = new HamiltonianPathinDAG(dag);
        assert(hp.isHamiltonianPath());

        Stack<Integer> expectedStack = new Stack<>();
        expectedStack.push(3);
        expectedStack.push(6);
        expectedStack.push(0);
        expectedStack.push(5);
        expectedStack.push(2);
        expectedStack.push(1);
        expectedStack.push(4);
        Iterator<Integer> expected = expectedStack.iterator();
        Iterator<Integer> actual = hp.hamiltonianPath().iterator();
        while (expected.hasNext() || actual.hasNext()) {
            assertEquals(expected.next(), actual.next());
        }
    }

    @Test
    void isNotDAG() {
        Digraph G = new Digraph(5);
        G.addEdge(0, 1);
        G.addEdge(1,2);
        G.addEdge(2, 3);
        G.addEdge(3, 4);
        G.addEdge(4, 0);

        HamiltonianPathinDAG hp = new HamiltonianPathinDAG(G);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> hp.isHamiltonianPath());
        String expectedMessage = "Not a Directed Acyclic Graph";
        assertEquals(expectedMessage, exception.getMessage());
    }
}