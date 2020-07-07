package com.bradychiu.algs4.collinearpoints;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BruteCollinearPoints {

    private static final String ERROR_NULL_POINTS = "Points argument cannot be null";
    private static final String ERROR_REPEATED_POINT = "Same point cannot be repeated";

    private int n;
    private LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) {
        checkNulls(points);

        n = 0;
        int size = points.length;
        segments = new LineSegment[size];

        for (int i = 0; i < size; i++) {
            Point p = points[i];

            for (int j = i + 1; j < size; j++) {
                Point q = points[j];

                if (p.compareTo(q) == 0) {
                    throw new IllegalArgumentException(ERROR_REPEATED_POINT);
                }

                double slopePQ = p.slopeTo(q);

                for (int k = j + 1; k < size; k++) {
                    Point r = points[k];

                    double slopePR = p.slopeTo(r);
                    if (slopePQ != slopePR) {
                        continue;
                    }

                    for (int m = k + 1; m < size; m++) {
                        Point s = points[m];

                        double slopePS = p.slopeTo(s);
                        if (slopePQ == slopePS) {
                            if (n == segments.length) {
                                doubleSegments();
                            }
                            Point[] collinears = {p, q, r, s};
                            Arrays.sort(collinears);
                            segments[n++] = new LineSegment(collinears[0], collinears[3]);
                        }
                    }
                }
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

        for (int i = 0; i < p.length; i++) {
            if (p[i] == null) {
                throw new IllegalArgumentException(ERROR_NULL_POINTS);
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
