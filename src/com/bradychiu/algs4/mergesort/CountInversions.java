package com.bradychiu.algs4.mergesort;

import edu.princeton.cs.algs4.*;

public class CountInversions {

    private static int merge(Comparable[] a, int low, int mid, int high) {
        assert isSorted(a, low, mid);
        assert isSorted(a, mid + 1, high);

        int count = 0;
        Comparable[] aux = new Comparable[a.length];
        for (int i = low; i < high; i++) {
            aux[i] = a[i];
        }
        int left = low;
        int right = mid;
        int all = low;

        // String pr = "";
        // for (Object o : Arrays.copyOfRange(a, low, high)) {
        //     pr = pr + o.toString();
        // }
        // StdOut.println("Pre sort:\t" + pr);

        while (all < high) {
            if (left >= mid) {
                a[all++] = aux[right++];
            } else if (right >= high) {
                a[all++] = aux[left++];
            } else if (aux[left].compareTo(aux[right]) > 0) {
                a[all++] = aux[right++];
                count += mid - left;
            } else {
                a[all++] = aux[left++];
            }
        }

        // String po = "";
        // for (Object o : Arrays.copyOfRange(a, low, high)) {
        //     po = po + o.toString();
        // }
        // StdOut.println("Post sort:\t" + po);

        assert isSorted(a, low, high);

        return count;
    }

    private static int sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        return sort(a, 0, a.length);
    }

    private static int sort(Comparable[] a, int low, int high) {
        if (high - low <= 1) {
            return 0;
        }

        int count = 0;
        int mid = (low + high) / 2;

        count += sort(a, low, mid);
        count += sort(a, mid, high);
        count += merge(a, low, mid, high);

        return count;
    }

    private static boolean isSorted(Comparable[] a) {
        int n = a.length;
        return isSorted(a, 0, n);
    }

    private static boolean isSorted(Comparable[] a, int low, int high) {
        if (high - low <= 1) {
            return true;
        }

        for (int i = low + 1; i < high; i++) {
            if (a[i].compareTo(a[i - 1]) < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // Integer[] a = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7}; // 0
        // Integer[] a = new Integer[]{1, 3, 5, 2, 4, 6}; // 3: (3,2) (5,2), (5,4)
        Integer[] a = new Integer[]{0, 1, 2, 3, 9, 5, 6, 4, 7, 8}; // 7: (9,5) (9,6), (9,4) (9,7), (9,8), (5,4), (6,4)
        StdOut.println("Inversions:\t" + sort(a));
    }
}
