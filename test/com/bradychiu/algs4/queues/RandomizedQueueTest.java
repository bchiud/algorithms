package com.bradychiu.algs4.queues;

import org.junit.jupiter.api.*;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class RandomizedQueueTest {
    private static RandomizedQueue<Integer> q;

    @BeforeAll
    static void setUp() {
        q = new RandomizedQueue<>();
    }

    @BeforeEach
    void init() {
        assertEquals(0, q.size());
    }

    @AfterEach
    void cleanup() {
        int n = q.size();
        for (int i = 0; i < n; i++) {
            q.dequeue();
        }
    }

    @AfterAll
    static void tearDown() {
    }

    @Test
    public void isEmpty() {
        assertEquals(true, q.isEmpty());
        q.enqueue(1);
        q.dequeue();
        assertEquals(true, q.isEmpty());
    }

    @Test
    void size() {
        assertEquals(0, q.size());
    }

    @Test
    void enqueue() {
        q.enqueue(0);
        assertEquals(1, q.size());
    }

    @Test
    void dequeue() {
        q.enqueue(1);
        assertEquals(1, q.dequeue());
        assertEquals(0, q.size());
    }

    @Test
    void sample() {
        assertEquals(0, q.size());
        q.enqueue(1);
        assertEquals(1, q.sample());
    }

    @Test
    void iterator() {
        int nums = 3;
        int trueSum = 0;
        int testSum = 0;

        for (int i = 0; i < nums; i++) {
            trueSum += i;
            q.enqueue(i);
        }

        Iterator<Integer> iter = q.iterator();
        for (int i = 0; i < nums; i++) {
            testSum += iter.next();
        }
        assertFalse(iter.hasNext());

        assertEquals(trueSum, testSum);
    }
}