package com.bradychiu.algs4.hashtable;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.bradychiu.algs4.hashtable.FourSum.*;
import static org.junit.jupiter.api.Assertions.*;

class FourSumTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void notEnoughIntegers() {
        assertThrowsAllVersions(IllegalArgumentException.class, new Integer[]{0, 1, 2});
    }

    @Test
    void allZeroes() {
        assertEqualsAllVersions(true, new Integer[]{0, 0, 0, 0});
        assertEqualsAllVersions(true, new Integer[]{0, 0, 0, 0, 0});
    }

    @Test
    void successes() {
        assertEqualsAllVersions(true, new Integer[]{0, 1, 2, 3, 4});
        assertEqualsAllVersions(true, new Integer[]{3, 4, 0, 1, 2});
        assertEqualsAllVersions(true, new Integer[]{5, 17, 8, 0, 0, 0, 0});
    }

    @Test
    void failures() {
        assertEqualsAllVersions(false, new Integer[]{0, 0, 0, 5});
        assertEqualsAllVersions(false, new Integer[]{5, 0, 0, 0});
        assertEqualsAllVersions(false, new Integer[]{0, 0, 1, 5});
        assertEqualsAllVersions(false, new Integer[]{5, 1, 0, 0});
        assertEqualsAllVersions(false, new Integer[]{1, 3, 6, 7});
    }

    private void assertEqualsAllVersions(boolean expected, Integer[] a) {
        assertEquals(expected, fourSumPointers(a));
        assertEquals(expected, fourSumHashTwoLoop(a));
        assertEquals(expected, fourSumHashOneLoop(a));
        // assertEquals(expected, fourSumQuadratic(a));
    }

    private void assertThrowsAllVersions(Class e, Integer[] a) {
        assertThrows(e, () -> fourSumPointers(a));
        assertThrows(e, () -> fourSumHashTwoLoop(a));
        assertThrows(e, () -> fourSumHashOneLoop(a));
        // assertThrows(e, () -> fourSumQuadratic(a));
    }

}