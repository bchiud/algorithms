package com.bradychiu.algs4.undirectedgraphs;

import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.LinkedList;

public class DiameterAndCenterOfATree {

    /**
     * Diameter and center of a tree. Given a connected graph with no cycles
     * <p>
     * Diameter: design a linear-time algorithm to find the longest simple path in the graph.
     * Center: design a linear-time algorithm to find a vertex such that its maximum distance from any other vertex is minimized.
     */

    private BreadthFirstPaths diameterBFS;
    private Graph G;
    private int center;
    private int diameter;
    private int[] furthest;
    private Iterable<Integer> diameterPath;

    public DiameterAndCenterOfATree(Graph G) {
        this.G = G;
        furthest = new int[2];
        diameter(0);
        center();
    }

    public int getDiameter() {
        return diameter;
    }

    public Iterable<Integer> getDiameterPath() {
        return diameterPath;
    }

    public int getCenter() {
        return center;
    }

    public int[] getFurthestVertexes() {
        return furthest;
    }

    private void diameter(int s) {
        // pick a vertex s; run BFS from s; then run BFS again from the vertex that is furthest from s.

        BreadthFirstPaths initialBFS = new BreadthFirstPaths(G, s);
        furthest[0] = s;
        int maxDist = s;

        for (int i = 0; i < G.V(); i++) {
            if (initialBFS.distTo(i) > maxDist) {
                furthest[0] = i;
                maxDist = initialBFS.distTo(i);
            }
        }

        diameterBFS = new BreadthFirstPaths(G, furthest[0]);

        for (int i = 0; i < G.V(); i++) {
            if (diameterBFS.distTo(i) > maxDist) {
                furthest[1] = i;
                diameter = diameterBFS.distTo(i);
            }
        }

        diameterPath = diameterBFS.pathTo(furthest[1]);
    }

    private void center() {
        Iterator<Integer> centerPath = diameterBFS.pathTo(furthest[1]).iterator();
        while (centerPath.hasNext()) {
            int currentVertex = centerPath.next();
            if (diameterBFS.distTo(currentVertex) >= 0.5 * diameter) {
                center = currentVertex;
                break;
            }
        }
    }
}
