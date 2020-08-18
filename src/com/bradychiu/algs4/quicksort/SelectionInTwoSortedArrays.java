package com.bradychiu.algs4.quicksort;

import edu.princeton.cs.algs4.StdOut;

public class SelectionInTwoSortedArrays {

    private static Comparable medianMerge(Comparable[] a, Comparable[] b) {
        int n = a.length + b.length;
        int k = n / 2;

        if (a[0] instanceof Number && n % 2 == 0) {
            int lower = (Integer) kthElementMerge(a, b, k);
            int upper = (Integer) kthElementMerge(a, b, k + 1);
            return (lower + upper) / 2.0;
        } else {
            return kthElementMerge(a, b, k + 1);
        }
    }

    // O(k) since we stop at k merges; O(m+n) if we don't
    private static Comparable kthElementMerge(Comparable[] a, Comparable[] b, int k) {
        int i = 0; // a index
        int j = 0; // b index
        int m = a.length;
        int n = b.length;
        Comparable[] sorted = new Comparable[m + n];

        while (i + j < k) {
            if (i == m) {
                sorted[i + j] = a[j++];
            } else if (j == n) {
                sorted[i + j] = a[i++];
            } else if (a[i].compareTo(b[j]) <= 0) {
                sorted[i + j] = a[i++];
            } else {
                sorted[i + j] = b[j++];
            }
        }

        return sorted[k - 1];
    }

    private static Comparable medianBinarySearch(Comparable[] a, Comparable[] b) {
        int n = a.length + b.length;
        int k = n / 2;

        if (a[0] instanceof Number && n % 2 == 0) {
            int lower = (Integer) kthElementBinarySearch(a, b, k);
            int upper = (Integer) kthElementBinarySearch(a, b, k + 1);
            return (lower + upper) / 2.0;
        } else {
            return kthElementBinarySearch(a, b, k + 1);
        }
    }

    private static Comparable kthElementBinarySearch(Comparable[] a, Comparable[] b, int k) {
        return kthElementBinarySearch(a, b, 0, 0, a.length, b.length, k);
    }

    /**
     * https://stackoverflow.com/a/29352604/3195226
     * O(log k)
     */

    private static Comparable kthElementBinarySearch(Comparable[] a, Comparable[] b, int ai, int bi, int an, int bn, int k) {
        if (ai + bi == k - 1) {
            if (an > ai && (bn <= bi || a[ai].compareTo(b[bi]) < 0)) {
                return a[ai];
            } else {
                return b[bi];
            }
        }

        int step = (k - ai - bi) / 2;
        int as = ai + step;
        int bs = bi + step;
        if (an > as - 1 && (bn <= bs - 1 || a[as - 1].compareTo(b[bs - 1]) < 0)) {
            ai = as;
        } else {
            bi = bs;
        }
        return kthElementBinarySearch(a, b, ai, bi, an, bn, k);
    }

    public static void main(String[] args) {
        // Integer[] a = {3, 12, 13, 14, 21, 29, 35, 36, 38, 40, 41}; // {0, 2, 4, 6};
        // Integer[] b = {-5, -3, 1, 5, 7, 9, 10, 11, 13, 14, 19}; // {1, 3, 5, 7};
        Integer[] a = {0, 2, 4, 6, 8, 10};
        Integer[] b = {1, 3, 5, 7, 9, 11};
        // StdOut.println(medianMerge(a, b));
        StdOut.println(kthElementMerge(a, b, 5));
        // StdOut.println(medianBinarySearch(a, b));
        StdOut.println(kthElementBinarySearch(a, b, 5));
    }
}
