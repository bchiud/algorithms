package com.bradychiu.algs4.collinearpoints;

import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        StdDraw.point(x, y);
    }
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

    public  int compareTo(Point that) {
        if (this.y < that.y) {
            return -1;
        } else if (this.y > that.y) {
            return 1;
        } else if (this.x < that.x) {
            return -1;
        } else {
            return this.x > that.x ? 1 : 0;
        }
    }

    public double slopeTo(Point that) {
        boolean xSame = this.x == that.x;
        boolean ySame = this.y == that.y;
        if (xSame && ySame) {
            return Double.NEGATIVE_INFINITY;
        } else if (xSame) {
            return Double.POSITIVE_INFINITY;
        } else if (ySame) {
            return (double) 0;
        }
        return (double) (that.y - this.y) / (that.x - this.x);
    }
    public Comparator<Point> slopeOrder() {
        return new SlopeComparator();

    }

    private class SlopeComparator implements Comparator<Point> {
        public int compare(Point a, Point b) {
            double aSlope = slopeTo(a);
            double bSlope = slopeTo(b);

            if (aSlope > bSlope) {
                return 1;
            } else if (bSlope > aSlope) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
