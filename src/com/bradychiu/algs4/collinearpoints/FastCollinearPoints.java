package com.bradychiu.algs4.collinearpoints;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class FastCollinearPoints {

    private static final String ERROR_NULL_POINTS = "Points argument cannot be null";
    private static final String ERROR_REPEATED_POINT = "Same point cannot be repeated";

    private int n;
    private final int size;
    private LineSegment[] segments;

    public FastCollinearPoints(Point[] points) {
        checkNulls(points);

        Point[] sorted = points.clone();
        Arrays.sort(sorted);
        checkDups(sorted);

        n = 0;
        size = points.length;
        segments = new LineSegment[size];
        Point[] sortedBySlope = points.clone();

        for (Point p : points) {
            Arrays.sort(sortedBySlope);
            Arrays.sort(sortedBySlope, p.slopeOrder());

            int start = 1;
            int end = 1;
            while (end < size) {
                if (end == size - 1 || p.slopeTo(sortedBySlope[start]) != p.slopeTo(sortedBySlope[end + 1])) {
                    if (end + 1 - start >= 3 && p.compareTo(sortedBySlope[start]) < 0) {
                        if (n == segments.length) {
                            doubleSegments();
                        }
                        segments[n++] = new LineSegment(p, sortedBySlope[end]);
                    }
                    start = end + 1;
                }
                end++;
            }
        }
    }

    public int numberOfSegments() {
        return n;
    }

    public LineSegment[] segments() {
        return Arrays.copyOfRange(segments, 0, n);
    }

    private void checkNulls(Point[] p) {
        if (p == null) {
            throw new IllegalArgumentException(ERROR_NULL_POINTS);
        }

        for (int i = 0; i < size; i++) {
            if (p[i] == null) {
                throw new IllegalArgumentException(ERROR_NULL_POINTS);
            }
        }
    }

    private void checkDups(Point[] p) {
        assert isSorted(p);

        for (int i = 1; i < p.length; i++) {
            if (p[i].compareTo(p[i - 1]) == 0) {
                throw new IllegalArgumentException(ERROR_REPEATED_POINT);
            }
        }
    }

    private void doubleSegments() {
        LineSegment[] doubled = new LineSegment[n * 2];
        for (int i = 0; i < n; i++) {
            doubled[i] = segments[i];
        }
        segments = doubled;
    }

    private boolean isSorted(Point[] p) {
        for (int i = 1; i < p.length; i++) {
            if (p[i].compareTo(p[i - 1]) < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
