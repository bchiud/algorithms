package com.bradychiu.algs4.undirectedgraphs;

import edu.princeton.cs.algs4.Graph;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NonRecursiveDFSTest {

    private static Graph G;

    @BeforeAll
    static void init() {
        G = new Graph(13);

        G.addEdge(0,1);
        G.addEdge(0,2);
        G.addEdge(0,5);
        G.addEdge(0,6);
        G.addEdge(3,4);
        G.addEdge(3,5);
        G.addEdge(4,6);

        G.addEdge(7,8);

        G.addEdge(9,10);
        G.addEdge(9,11);
        G.addEdge(9, 12);
        G.addEdge(11,12);
    }

    @Test
    void connectedComponentOne() {
        NonRecursiveDFS nonRecursiveDFS = new NonRecursiveDFS(G, 5);

        for (int i = 0; i < 7; i++)
            assertEquals(true, nonRecursiveDFS.marked(i));

        for (int i = 7; i < 9; i++)
            assertEquals(false, nonRecursiveDFS.marked(i));

        for (int i = 9; i < 13; i++)
            assertEquals(false, nonRecursiveDFS.marked(i));
    }

    @Test
    void connectedComponentTwo() {
        NonRecursiveDFS nonRecursiveDFS =  new NonRecursiveDFS(G, 7);

        for (int i = 7; i < 9; i++)
            assertEquals(true, nonRecursiveDFS.marked(i));

        for (int i = 0; i < 7; i++)
            assertEquals(false, nonRecursiveDFS.marked(i));

        for (int i = 9; i < 13; i++)
            assertEquals(false, nonRecursiveDFS.marked(i));
    }

    @Test
    void connectedComponentThree() {
        NonRecursiveDFS nonRecursiveDFS =  new NonRecursiveDFS(G, 10);

        for (int i = 0; i < 7; i++)
            assertEquals(false, nonRecursiveDFS.marked(i));

        for (int i = 7; i < 9; i++)
            assertEquals(false, nonRecursiveDFS.marked(i));

        for (int i = 9; i < 13; i++)
            assertEquals(true, nonRecursiveDFS.marked(i));
    }
}