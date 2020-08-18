package com.bradychiu.algs4.kdtrees;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.LinkedList;
import java.util.List;

public class KdTree {

    private Node root;
    private int size;

    // construct an empty set of points
    public KdTree() {
        root = null;
        size = 0;
    }

    // is the set empty?
    public boolean isEmpty() {
        return root == null;
    }

    // number of points in the set
    public int size() {
        return size;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D point) {
        if (point == null)
            throw new IllegalArgumentException();

        root = insert(root, point, true, null);
    }

    private Node insert(Node curr, Point2D point, boolean isVertical, Node prev) {
        if (curr == null) {
            size++;
            return new Node(point, isVertical, null);
        }

        if (curr.point.compareTo(point) == 0)
            return curr;

        int comp = curr.compareTo(point);
        if (comp > 0) {
            curr.left = insert(curr.left, point, !isVertical, curr);
        } else {
            curr.right = insert(curr.right, point, !isVertical, curr);
        }

        return curr;
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();

        return get(root, p) != null;
    }

    private Point2D get(Node curr, Point2D p) {
        if (curr == null)
            return null;

        if (curr.point.compareTo(p) == 0)
            return curr.point;

        int comp = curr.compareTo(p);
        if (comp > 0) {
            return get(curr.left, p);
        } else {
            return get(curr.right, p);
        }
    }

    // draw all points to standard draw
    public void draw() {
        if (root != null)
            draw(root);
    }

    private void draw(Node curr) {
        StdDraw.setPenRadius(0.005);

        if (curr.isVertical) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(curr.point.x(), curr.rect.ymin(), curr.point.x(), curr.rect.ymax());
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(curr.rect.xmin(), curr.point.y(), curr.rect.xmax(), curr.point.y());
        }

        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.point(curr.point.x(), curr.point.y());

        if (curr.left != null)
            draw(curr.left);
        if (curr.right != null)
            draw(curr.right);
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null)
            throw new IllegalArgumentException();

        if (isEmpty())
            return null;

        List<Point2D> list = new LinkedList<>();
        range(root, rect, list);
        return list;
    }

    private void range(Node curr, RectHV rect, List<Point2D> list) {
        if (curr == null)
            return;

        if (rect.intersects(curr.rect)) {
            if (rect.contains(curr.point))
                list.add(curr.point);
            if (curr.left != null)
                range(curr.left, rect, list);
            if (curr.right  != null)
                range(curr.right, rect, list);
        }
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D point) {
        if (point == null)
            throw new IllegalArgumentException();

        if (isEmpty())
            return null;

        return nearest(root, point, root.point);
    }

    private Point2D nearest(Node curr, Point2D point, Point2D nearestPoint) {
        if (curr == null)
            return null;

        if (nearestPoint == null)
            return curr.point;

        if (curr.rect.distanceSquaredTo(point) < point.distanceSquaredTo(nearestPoint)) {
            if (point.distanceSquaredTo(curr.point) < point.distanceSquaredTo(nearestPoint))
                return curr.point;

            Point2D left;
            Point2D right;
            if (curr.compareTo(point) > 0) {
                left = nearest(curr.left, point, nearestPoint);
                right = nearest(curr.right, point, nearestPoint);
            } else {
                right = nearest(curr.right, point, nearestPoint);
                left = nearest(curr.left, point, nearestPoint);
            }

            return point.distanceSquaredTo(left) <= point.distanceSquaredTo(right) ? left
                                                                                   : right;
        }

        return nearestPoint;
    }

    private class Node {
        public Point2D point;
        public RectHV rect;
        public Node left;
        public Node right;
        public boolean isVertical;

        public Node(Point2D point, boolean isVertical, Node parent) {
            this.point = point;
            this.isVertical = isVertical;

            if (parent == null) {
                this.rect = new RectHV(0.0, 0.0, 1.0, 1.0);
            } else {
                double xmin = parent.rect.xmin();
                double xmax = parent.rect.xmax();
                double ymin = parent.rect.ymin();
                double ymax = parent.rect.ymax();

                int comp = parent.compareTo(point);

                if (this.isVertical) {
                    if (comp > 0) {
                        ymax = parent.point.y();
                    } else {
                        ymin = parent.point.y();
                    }
                } else {
                    if (comp > 0) {
                        xmax = parent.point.x();
                    } else {
                        xmin = parent.point.x();
                    }
                }

                this.rect = new RectHV(xmin, ymin, xmax, ymax);
            }
        }
        private int compareTo(Point2D that) {
            return this.isVertical ? Double.compare(this.point.x(), that.x())
                                   : Double.compare(this.point.y(), that.y());
        }
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
        KdTree k = new KdTree();
        k.insert(new Point2D(1.0, 2.0));
        assert k.contains(new Point2D(1.0, 2.0));
        assert !k.contains(new Point2D(0.0, 2.0));
        assert !k.contains(new Point2D(1.0, 0.0));

        k.insert(new Point2D(2.0, 1.0));
        assert k.contains(new Point2D(2.0, 1.0));
        assert !k.contains(new Point2D(1.0, 0.0));
        assert !k.contains(new Point2D(0.0, 1.0));

        KdTree k2 = new KdTree();
        k2.insert(new Point2D(0.7, 0.2));
        k2.insert(new Point2D(0.5, 0.4));
        k2.insert(new Point2D(0.2, 0.3));
        k2.insert(new Point2D(0.4, 0.7));
        k2.insert(new Point2D(0.9, 0.6));
        assert k2.contains(new Point2D(0.2, 0.3));

        KdTree k3 = new KdTree();
        k3.insert(new Point2D(0.5, 1.0));
        k3.insert(new Point2D(0.75, 0.75));
        k3.insert(new Point2D(0.0, 0.75));
        k3.insert(new Point2D(0.0, 0.25));
        k3.insert(new Point2D(0.0, 0.0));
        k3.insert(new Point2D(0.75, 0.5));
        k3.insert(new Point2D(0.75, 0.0));
        k3.insert(new Point2D(1.0, 0.0));
        k3.insert(new Point2D(0.5, 0.75));
        k3.insert(new Point2D(0.25, 0.0));
        assert k3.contains(new Point2D(0.0, 0.75));

        KdTree k4 = new KdTree();
        k4.insert(new Point2D(0.125, 0.5));
        k4.insert(new Point2D(0.375, 0.375));
        k4.insert(new Point2D(0.625, 0.875));
        k4.insert(new Point2D(0.875, 0.875));
        k4.insert(new Point2D(0.875, 0.75));
        assert k4.size() == 5;

        KdTree k5 = new KdTree();
        k5.insert(new Point2D(0.7, 0.2));
        k5.insert(new Point2D(0.5, 0.4));
        k5.insert(new Point2D(0.2, 0.3));
        k5.insert(new Point2D(0.4, 0.7));
        k5.insert(new Point2D(0.9, 0.6));
        Point2D nearestPoint = k5.nearest(new Point2D(0.901, 0.684));
        assert nearestPoint.x() == 0.9;
        assert nearestPoint.y() == 0.6;
    }
}
