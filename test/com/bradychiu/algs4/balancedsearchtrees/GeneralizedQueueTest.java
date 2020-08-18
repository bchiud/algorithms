package com.bradychiu.algs4.balancedsearchtrees;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class GeneralizedQueueTest {

    private static GeneralizedQueue<Character> queue;

    @BeforeAll
    static void init() {
        queue = new GeneralizedQueue<>();
    }

    @BeforeEach
    void setUp() {
        for (char c = 'A'; c <= 'F'; c++) {
            queue.enqueue(c);
        }
    }

    @AfterEach
    void tearDown() {
        queue.clear();
    }

    @AfterAll
    static void cleanUp() {
        queue = null;
    }

    @Test
    void enqueue() {
        int size = queue.size();
        char c = 'G';
        queue.enqueue(c);
        assertEquals(size + 1, queue.size());
        assertEquals(c, queue.get(size));
    }

    @Test
    void dequeue() {
        int size = queue.size();
        queue.dequeue();
        assertEquals(size - 1, queue.size());
    }

    @Test
    void get() {
        assertEquals('B', queue.get(1));
    }

    @Test
    void remove() {
        int size = queue.size();
        assertEquals('B', queue.remove(1));
        assertEquals(size - 1, queue.size());
    }

    @Test
    void size() {
        queue.clear();
        assertEquals(0, queue.size());
        queue.enqueue('G');
        assertEquals(1, queue.size());
    }

    @Test
    void clear() {
        queue.enqueue('G');
        assert(queue.size() > 0);
        queue.clear();
        assertEquals(0, queue.size());
    }
}