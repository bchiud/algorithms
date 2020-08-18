package com.bradychiu.algs4.balancedsearchtrees;


import edu.princeton.cs.algs4.RedBlackBST;

import java.util.NoSuchElementException;

/**
 * Generalized queue. Design a generalized queue data type that supports all of
 * the following operations in logarithmic time (or better) in the worst case.
 *
 * * Create an empty data structure.
 * * Append an item to the end of the queue.
 * * Remove an item from the front of the queue.
 * * Return the i-th item in the queue.
 * * Remove the i-th item from the queue.
 */
public class GeneralizedQueue<Item> {

    private RedBlackBST<Integer, Item> queue;

    // Create an empty data structure.
    public GeneralizedQueue() {
        queue = new RedBlackBST<>();
    }

    // Append an item to the end of the queue.
    public void enqueue(Item item) {
        queue.put(queue.isEmpty() ? 0 : queue.max() + 1, item);
    }

    // Remove an item from the front of the queue.
    public Item dequeue() {
        if (queue.size() == 0) {
            throw new NoSuchElementException();
        }

        Item item = queue.get(queue.min());
        queue.deleteMin();
        return item;
    }

    // Return the i-th item in the queue.
    public Item get(Integer i) {
        if (i < 0 || i >= queue.size()) {
            throw new NoSuchElementException();
        }

        Integer key = queue.select(i);
        return queue.get(key);
    }

    // Remove the i-th item from the queue.
    public Item remove(Integer i) {
        if (i < 0 || i >= queue.size()) {
            throw new NoSuchElementException();
        }

        Integer key = queue.select(i);
        Item item = queue.get(key);
        queue.delete(key);
        return item;
    }

    public int size() {
        return queue.size();
    }

    public void clear() {
        while (!queue.isEmpty()) {
            dequeue();
        }
        assert queue.isEmpty();
    }
}
