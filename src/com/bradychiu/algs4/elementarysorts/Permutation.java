package com.bradychiu.algs4.elementarysorts;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

    private boolean isPermutation(Comparable[] a, Comparable[] b) {
        Shell.sort(a);
        Shell.sort(b);

        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Point2D i = new Point2D(1, 4);
        Point2D j = new Point2D(3, 2);
        Point2D k = new Point2D(5, 0);

        Point2D[] a = new Point2D[]{i, j, k};
        Point2D[] b = new Point2D[]{j,k,i};

        Permutation p = new Permutation();
        StdOut.println(p.isPermutation(a,b));

    }
}
