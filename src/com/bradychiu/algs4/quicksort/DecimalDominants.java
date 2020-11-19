package com.bradychiu.algs4.quicksort;

import edu.princeton.cs.algs4.StdOut;

import java.util.*;

/**
 * Given an array with n keys, design an algorithm to find all values that occur more than n/10 times.
 * The expected running time of your algorithm should be linear
 * https://en.wikipedia.org/wiki/Boyer%E2%80%93Moore_majority_vote_algorithm
 */

public class DecimalDominants {

    private static final String ERROR_K_OUT_OF_BOUNDS = "k is out of bounds";

    private static Object[] brute(Comparable[] a, int k) {
        int times = a.length / k;

        Map<Comparable, Integer> counts = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            counts.put(a[i], counts.getOrDefault(a[i], 0) + 1);
        }

        Object[] result = counts.keySet().toArray();
        Arrays.sort(result);

        return result;
    }

    private static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        shuffle(a);
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
        assert isSorted(a, lo, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable pivot = a[lo];

        while (true) {
            while (a[++i].compareTo(pivot) < 0)
                if (i >= hi)
                    break;
            while (a[--j].compareTo(pivot) > 0)
                if (j <= lo)
                    break;
            if (i >= j)
                break;
            swap(a, i, j);
        }

        swap(a, lo, j);

        return j;
    }

    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i < hi + 1; i++) {
            if (a[1].compareTo(a[i - 1]) < 0) {
                return false;
            }
        }
        return true;
    }

    private static String arrayToString(Object[] a) {
        String s = "(" + a[0].toString();
        for (int i = 1; i < a.length; i++) {
            s = s + ", " + a[i].toString();
        }
        return s + ")";
    }

    private static Comparable select(Comparable[] a, int k) {
        if (k < 0 || k > a.length - 1) {
            throw new IllegalArgumentException(ERROR_K_OUT_OF_BOUNDS);
        }
        shuffle(a);
        int lo = 0;
        int hi = a.length - 1;
        while (hi > lo) {
            int i = partition(a, lo, hi);
            if (i > k) {
                hi = i - 1;
            } else if (i < k) {
                lo = i + 1;
            } else {
                return a[i];
            }
        }
        return a[lo];
    }

    private static void shuffle(Object[] a) {
        Random r = new Random();
        for (int i = 0; i < a.length; i++) {
            swap(a, i, r.nextInt(a.length));
        }
    }

    private static void swap(Object[] a, int i, int j) {
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // private static Comparable[] nOrMoreOccurences(Comparable[] a, int n) {
    //     Comparable[] everyPossibleNValue = new Comparable[a.length / n];
    //     int k = a.length / n;
    //     for (int i = 0; i < a.length / n; i++) {
    //         everyPossibleNValue[i] = select(a, i * a.length / n);
    //     }
    //
    //     ArrayList<Comparable> result = new ArrayList<>();
    //
    //     for (int i = 1; i < everyPossibleNValue.length; i++) {
    //         if (everyPossibleNValue[i] == everyPossibleNValue[i - 1]) {
    //             result.add(everyPossibleNValue[i]);
    //         }
    //     }
    //
    //     return (Comparable[]) result.toArray();
    // }

    // TODO: 7/15/20 finish this https://stackoverflow.com/questions/9599559/decimal-dominant-number-in-an-array
    private static Comparable[] bayorMooreMajorityVote(Comparable[] a, int k) {
        Map<Comparable, Integer> counter = new HashMap<Comparable, Integer>(k - 1);

        for (int i = 0; i < a.length; i++) {
            if (counter.size() == k) {
                counter.replaceAll((key, val) -> (val > 0 ? val - 1 : counter.remove(key)));
            }

            if (i < k) {
                counter.put(a[i], counter.getOrDefault(a[i], 0) + 1);
            } else {
                if (counter.containsKey(a[i])) {
                    counter.put(a[i], counter.get(a[i]) + 1);
                }
            }
        }

        // Comparable[] result = counter.size();
        return a;
    }


    public static void main(String[] args) {
        Integer[] a = new Integer[]{0,1,1,3,4,5,6,7,8,9};

        StdOut.println("In:\t" + arrayToString(a));
        Object[] aOutput = brute(a, 10);
        StdOut.println("In:\t" + arrayToString(aOutput));

        Integer[] b = new Integer[]{1,1,1,2,2,2};
        StdOut.println("Out:\t" + arrayToString(b));
        Object[] bOutput = brute(b, 3);
        StdOut.println("Out:\t" + arrayToString(bOutput));


    }
}
