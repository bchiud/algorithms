package com.bradychiu.algs4.undirectedgraphs;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.StdOut;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class DiameterAndCenterOfATreeTest {

    private static DiameterAndCenterOfATree diameterAndCenterOfATree;
    private static Graph G;

    @BeforeAll
    static void init() {
        G = new Graph(7);

        G.addEdge(0, 1);
        G.addEdge(0, 2);
        G.addEdge(0, 5);
        G.addEdge(0, 6);
        G.addEdge(3, 4);
        G.addEdge(3, 5);
        G.addEdge(4, 6);

        diameterAndCenterOfATree = new DiameterAndCenterOfATree(G);
    }

    @Test
    void getDiameter() {
        assertEquals(3, diameterAndCenterOfATree.getDiameter());
    }

    @Test
    void getDiameterPath() {
        int[] expectedPath = new int[]{3, 5, 0, 2};

        Iterable<Integer> actualPath = diameterAndCenterOfATree.getDiameterPath();
        int i = 0;
        for (int v : actualPath) {
            assertEquals(expectedPath[i], v);
            i++;
        }
    }

    @Test
    void getCenter() {
        assertEquals(0, diameterAndCenterOfATree.getCenter());
    }

    @Test
    void getFurthestVertexes() {
        int[] furthest = new int[]{3, 2};
        assertArrayEquals(furthest, diameterAndCenterOfATree.getFurthestVertexes());
    }
}