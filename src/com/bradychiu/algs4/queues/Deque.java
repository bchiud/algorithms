package com.bradychiu.algs4.queues;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node firstNode;
    private Node lastNode;
    private int size;

    // construct an empty deque
    public Deque() {
        firstNode = null;
        lastNode = null;
        size = 0;
    }

    private class Node {
        private final Item item;
        private Node prev;
        private Node next;

        public Node(Item item) {
            this.item = item;
        }
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        checkItem(item);
        Node newFirst = new Node(item);
        if (isEmpty()) {
            firstNode = newFirst;
            lastNode = newFirst;
        } else {
            newFirst.next = firstNode;
            firstNode.prev = newFirst;
            firstNode = newFirst;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        checkItem(item);
        Node newLast = new Node(item);
        if (isEmpty()) {
            firstNode = newLast;
            lastNode = newLast;
        } else {
            lastNode.next = newLast;
            newLast.prev = lastNode;
            lastNode = newLast;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        checkSize();
        Item item = firstNode.item;
        if (size() == 1) {
            firstNode = null;
            lastNode = null;
        } else {
            firstNode = firstNode.next;
            firstNode.prev = null;
        }
        size--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        checkSize();
        Item item = lastNode.item;
        if (size() == 1) {
            firstNode = null;
            lastNode = null;
        } else {
            lastNode = lastNode.prev;
            lastNode.next = null;
        }
        size--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node currentNode = firstNode;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public Item next() {
            if (currentNode == null) {
                throw new NoSuchElementException();
            }
            Item item = currentNode.item;
            currentNode = currentNode.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private void checkItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
    }

    private void checkSize() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> miDeque = new Deque<>();
        for (int i = 3; i > 0; i--) {
            miDeque.addFirst(i);
        }
        for (int i = 4; i < 6; i++) {
            miDeque.addLast(i);
        }

        for (int i = 1; i < 3; i++) {
            StdOut.println("check removeFirst():\t" + (miDeque.removeFirst() == i));
        }
        StdOut.println("check size():\t\t\t" + (miDeque.size() == 3));

        Iterator<Integer> iter = miDeque.iterator();
        for (int i = 3; i < 6; i++) {
            StdOut.println("check iter.next():\t\t" + ((iter.next() == i)));
        }
        StdOut.println("check iter.hasNext():\t" + ((!iter.hasNext())));

        for (int i = 5; i > 2; i--) {
            StdOut.println("check removeLast():\t\t" + ((miDeque.removeLast() == i)));
        }
        StdOut.println("check isEmpty():\t\t" + ((miDeque.isEmpty())));
    }
}
