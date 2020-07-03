package com.bradychiu.algs4.elementarysorts;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class TwoSetIntersection {

    private int countIntersections(Comparable[] a, Comparable[] b) {
        Shell.sort(a);
        Shell.sort(b);

        int n = 0;
        int i = 0;
        int j = 0;
        while (i < a.length && j < b.length) {
            int compare = a[i].compareTo(b[j]);
            if (compare == 0) {
                n++;
                i++;
                j++;
            } else if (compare < 1) {
                i++;
            } else {
                j++;
            }
        }
        return n;
    }

    private static class Point2D implements Comparable<Point2D> {

        private int x;
        private int y;

        private Point2D(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point2D o) {
            if (this.y < o.y) {
                return -1;
            } else if (this.y > o.y) {
                return 1;
            } else if (this.x < o.x) {
                return -1;
            } else {
                return this.x > o.x ? 1 : 0;
            }
        }

        public String toString() {
            return "(" + this.x + ", " + this.y + ")";
        }
    }

    public static void main(String[] args) {

        int points = 10;
        int maxVal = 4;
        int indexZeroMaxVal = maxVal + 1;
        Point2D[] a = new Point2D[points];
        Point2D[] b = new Point2D[points];

        for (int i = 0; i < points; i++) {
            a[i] = new Point2D(StdRandom.uniform(indexZeroMaxVal), StdRandom.uniform(indexZeroMaxVal));
            b[i] = new Point2D(StdRandom.uniform(indexZeroMaxVal), StdRandom.uniform(indexZeroMaxVal));
        }

        StdOut.println("1st array pre sort:\t\t" + Arrays.toString(a));
        StdOut.println("2nd array pre sort:\t\t" + Arrays.toString(b));

        TwoSetIntersection tsi = new TwoSetIntersection();
        int inters = tsi.countIntersections(a, b);

        StdOut.println("1st array post sort:\t" + Arrays.toString(a));
        StdOut.println("2nd array post sort:\t" + Arrays.toString(b));

        StdOut.println("Intersections:\t\t\t" + inters);
    }
}
