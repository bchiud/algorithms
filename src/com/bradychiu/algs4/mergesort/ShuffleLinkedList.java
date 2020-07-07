package com.bradychiu.algs4.mergesort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.NoSuchElementException;

/**
 * Shuffling a linked list.
 * Given a singly-linked list containing nn items, rearrange the items uniformly at random.
 * Your algorithm should consume a logarithmic (or constant) amount of extra memory and run in time proportional to n \log nnlogn in the worst case.
 */

public class ShuffleLinkedList {

    public static final String ERROR_LINKEDLIST_UNDERFLOW = "LinkedList underflow";

    private Node first;
    private Node last;
    private int size;

    private class Node {
        Object item;
        Node next;

        public Node() {
        }

        public Node(Object item) {
            this.item = item;
        }

        public Node(Object item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    public ShuffleLinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    public void add(Object item) {
        Node newNode = new Node(item);
        if (first == null && last == null) {
            first = newNode;
            last = newNode;
        } else if (first != null && first == last) {
            first.next = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    public Node getFirst() {
        if (first == null) {
            throw new NoSuchElementException(ERROR_LINKEDLIST_UNDERFLOW);
        }
        return first;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public String toString() {
        Node current = getFirst();
        String s = current.item.toString();
        while (current.next != null) {
            current = current.next;
            s = s + ", " + current.item.toString();
        }
        return s;
    }

    public Node merge(Node left, Node right) {

        Node shuffled = new Node();
        Node current = shuffled;

        while (left != null && right != null) {
            int rnd = StdRandom.uniform(2);
            if (rnd == 0) {
                current.next = left;
                current = current.next;
                left = left.next;
            } else {
                current.next = right;
                current = current.next;
                right = right.next;
            }
        }
        if (left != null) {
            current.next = left;
        } else if (right != null) {
            current.next = right;
        }

        return shuffled.next;
    }

    public void shuffle() {
        first = sort(first);
    }

    private Node sort(Node start) {
        if (start == null || start.next == null) {
            return start;
        }

        Node slow = start;
        Node fast = start;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node left = start;
        Node right = slow.next;
        slow.next = null;

        left = sort(left);
        right = sort(right);
        return merge(left, right);
    }

    public static void main(String[] args) {
        ShuffleLinkedList ll = new ShuffleLinkedList();
        for(Integer i = 0; i < 10; i++) {
            ll.add(i);
        }
        StdOut.println("Pre shuffle:\t" + ll.toString());
        ll.shuffle();
        StdOut.println("Post shuffle:\t" + ll.toString());
    }
}
