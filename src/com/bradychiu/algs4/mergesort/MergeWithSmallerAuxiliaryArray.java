package com.bradychiu.algs4.mergesort;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class MergeWithSmallerAuxiliaryArray {

    public void sort(Comparable[] a) {
        int n = a.length / 2;
        Comparable[] aux = new Comparable[n];

        for (int i = 0; i < n; i++) {
            aux[i] = a[i];
        }

        int i = 0; // aux index
        int j = n; // 2nd half of a index
        int k = 0; // a index

        while (k < a.length) {
            if (i >= n) {
                a[k++] = a[j++];
            } else if (j >= a.length) {
                a[k++] = aux[i++];
            } else if (less(a[j], aux[i])) {
                a[k++] = a[j++];
            } else {
                a[k++] = aux[i++];
            }
        }

        assert isSorted(a);
    }

    private boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1]) ) {
                return false;
            }
        }
        return true;
    }

    private boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[] {2,4,6,8, 1,3,5,7};

        StdOut.println("Pre sorted array:\t" + Arrays.toString(arr));

        MergeWithSmallerAuxiliaryArray merge = new MergeWithSmallerAuxiliaryArray();
        merge.sort(arr);

        StdOut.println("Post sorted array:\t" + Arrays.toString(arr));
    }

}
