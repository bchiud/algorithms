package com.bradychiu.algs4.quicksort;

import edu.princeton.cs.algs4.Quick3way;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class NutsAndBolts {

    public static void sort(Comparable[] a, Comparable[] b) {
        assert a.length == b.length;
        StdRandom.shuffle(a);
        StdRandom.shuffle(b);
        sort(a, b, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, Comparable[] b, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        StdOut.println("Nuts:");
        int pivot = partition(a, lo, hi, b[lo]);
        StdOut.println("Bolts:");
        partition(b, lo, hi, a[pivot]);
        sort(a, b, lo, pivot - 1);
        sort(a, b, pivot + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi, Comparable pivot) {
        StdOut.println("Pivot:\t" + pivot);
        int i = lo;
        for (int j = lo; j < hi; j++) {
            StdOut.println("Loop");
            StdOut.println("i:\t" + i + "\tj:\t" + j + "\tPre:\t" + arrayToString(Arrays.copyOfRange(a, lo, hi + 1)));
            if (a[j].compareTo(pivot) < 0) {
                exchange(a, i++, j);
            } else if (a[j].compareTo(pivot) == 0) {
                exchange(a, j--, hi);
            }
            StdOut.println("i:\t" + i + "\tj:\t" + j + "\tPost:\t" + arrayToString(Arrays.copyOfRange(a, lo, hi + 1)));
        }
        StdOut.println("i:\t" + i + "\tPre:\t" + arrayToString(Arrays.copyOfRange(a, lo, hi + 1)));
        exchange(a, i, hi);
        StdOut.println("i:\t" + i + "\tPost:\t" + arrayToString(Arrays.copyOfRange(a, lo, hi + 1)));
        return i;
    }

    private static void exchange(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private static String arrayToString(Comparable[] a) {
        String s = "(" + a[0].toString();
        for (int i = 1; i < a.length; i++) {
            s = s + ", " + a[i].toString();
        }
        s = s + ")";
        return s;
    }

    public static void main(String[] args) {
        int n = 5;
        Integer[] nuts = new Integer[n];
        Integer[] bolts = new Integer[n];
        for (int i = 0; i < n; i++) {
            nuts[i] = i;
            bolts[i] = i;
        }
        StdRandom.shuffle(nuts);
        StdRandom.shuffle(bolts);

        StdOut.println("Nuts Start:\t" + arrayToString(nuts));
        StdOut.println("Bolts Start:\t" + arrayToString(bolts));

        sort(nuts, bolts);


        StdOut.println("Nuts End:\t" + arrayToString(nuts));
        StdOut.println("Bolts End:\t" + arrayToString(bolts));
    }
}
