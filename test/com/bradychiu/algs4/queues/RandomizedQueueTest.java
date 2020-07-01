package com.bradychiu.algs4.queues;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

class RandomizedQueueTest {

    private int testSize;
    private int halfTestSize;
    private RandomizedQueue<Integer> q;

    @Before
    void setUp() {
        testSize = 10;
        halfTestSize = testSize / 2;
        q = new RandomizedQueue<>();
    }

    @After
    void tearDown() {
        testSize = halfTestSize = Integer.parseInt(null);
        q = null;
        assertNull(testSize);
        assertNull(halfTestSize);
        assertNull(q);
    }

    @Test
    void isEmpty() {
        q.enqueue(1);
        q.dequeue();
        assertEquals(0, q.size());
    }

    @Test
    void size() {
        assertEquals(0, q.size());
        q.enqueue(0);
        assertEquals(1, q.size());
    }

    @Test
    void enqueue() {
        q.enqueue(0);
        assertEquals(1, q.size());
    }

    @Test
    void dequeue() {
        q.enqueue(0);
        q.dequeue();
        assertEquals(0, q.size());
    }

    @Test
    void sample() {
        assertEquals(0, q.size());
        q.enqueue(1);
        assertEquals(1, q.size());
        int i = q.sample();
        assertEquals(1, i);
        assertEquals(1, q.size());
    }

    @Test
    void iterator() {
        int nums = 3;
        int trueSum = 0;
        int testSum = 0;
        Iterator<Integer> iter = q.iterator();

        for (int i = 0; i < nums; i++) {
            trueSum += i;
            q.enqueue(i);
        }
        assertFalse(iter.hasNext());

        for (int i = 0; i < nums; i++) {
            testSum += iter.next();
        }
        assertFalse(iter.hasNext());

        assertEquals(trueSum, testSum);
    }
}