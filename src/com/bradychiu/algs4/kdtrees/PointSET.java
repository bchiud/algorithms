package com.bradychiu.algs4.kdtrees;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PointSET {

    private SET<Point2D> storage;

    // construct an empty set of points
    public PointSET() {
        storage = new SET<>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return storage.isEmpty();
    }

    // number of points in the set
    public int size() {
        return storage.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();

        storage.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();

        return storage.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        Iterator<Point2D> points = storage.iterator();
        while (points.hasNext()) {
            Point2D point = points.next();
            StdDraw.point(point.x(), point.y());
        }
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null)
            throw new IllegalArgumentException();

        List<Point2D> contains = new ArrayList<Point2D>();

        Iterator<Point2D> points = storage.iterator();
        while (points.hasNext()) {
            Point2D point = points.next();
            if (rect.contains(point))
                contains.add(point);
        }

        return contains;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();

        double minDistance = Double.MAX_VALUE;
        Point2D nearest = null;

        Iterator<Point2D> points = storage.iterator();
        while (points.hasNext()) {
            Point2D point = points.next();
            double distance = p.distanceSquaredTo(point);
            if (distance < minDistance) {
                minDistance = p.distanceSquaredTo(point);
                nearest = point;
            }
        }

        return nearest;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
        PointSET p = new PointSET();
        p.insert(new Point2D(0.125, 0.5));
        p.insert(new Point2D(0.375, 0.375));
        p.insert(new Point2D(0.625, 0.875));
        p.insert(new Point2D(0.875, 0.875));
        p.insert(new Point2D(0.875, 0.75));
        assert p.size() == 5;
    }
}
