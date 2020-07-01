package com.bradychiu.algs4.queues;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] storage;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        size = 0;
        storage = null;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        } else if (size == 0) {
            storage = (Item[]) new Object[2];
        } else if (storage.length == size) {
            resize(storage.length * 2);
        }
        storage[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        int returnIndex = size == 1 ? 0 : randomItemIndex();
        Item item = storage[returnIndex];
        storage[returnIndex] = storage[--size];
        storage[size] = null;

        if (size > 0 && storage.length >= size * 4) {
            resize(storage.length / 2);
        }

        return item;
    }


    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException();
        } else if (size == 1) {
            return storage[0];
        } else {
            return storage[randomItemIndex()];
        }

    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {

        private final int iterSize;
        private final Item[] iterStorage;

        private int iterIndex;

        public RandomIterator() {
            iterIndex = 0;
            iterSize = size;
            iterStorage = (Item[]) new Object[iterSize];
            for (int i = 0; i < iterSize; i++) {
                iterStorage[i] = storage[i];
            }
            StdRandom.shuffle(iterStorage);
        }

        @Override
        public boolean hasNext() {
            return iterIndex != iterSize;
        }

        @Override
        public Item next() {
            if (iterIndex == iterSize) {
                throw new NoSuchElementException();
            }
            return iterStorage[iterIndex++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private int randomItemIndex() {
        return StdRandom.uniform(size);
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = storage[i];
        }
        storage = copy;
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> miRandomizedQueue = new RandomizedQueue<>();

        int testSize = 11;
        int halfTestSize = testSize / 2;
        for (int i = 0; i < testSize; i++) {
            StdOut.println("Enqueue check:\t\t\t" + i);
            miRandomizedQueue.enqueue(i);
        }

        for (int i = 0; i < halfTestSize; i++) {
            StdOut.println("Dequeue check:\t\t\t" + miRandomizedQueue.dequeue());
        }
        int trueSize = testSize % 2 == 0 ? halfTestSize : halfTestSize + 1;
        StdOut.println("size() check:\t\t\t" + ((miRandomizedQueue.size() == trueSize)));

        Iterator<Integer> iter = miRandomizedQueue.iterator();
        for (int i = 0; i < trueSize; i++) {
            StdOut.println("iter.next() check:\t\t" + iter.next());
        }
        StdOut.println("iter.hasNext() check:\t" + ((!iter.hasNext())));

        for (int i = 0; i < trueSize; i++) {
            StdOut.println("Dequeue check:\t" + miRandomizedQueue.dequeue());
        }
        StdOut.println("isEmpty() check:\t" + ((miRandomizedQueue.isEmpty())));
    }

}
