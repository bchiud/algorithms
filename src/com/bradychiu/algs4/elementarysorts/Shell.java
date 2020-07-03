package com.bradychiu.algs4.elementarysorts;

class Shell {
    private Shell() {
    }

    static void sort(Comparable[] arr) {
        int n = arr.length;

        int h = calcMaxH(n);
        while (h > 0) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(arr[j], arr[j - h]); j -= h) {
                    swap(arr, j, j - h);
                }
            }

            h /= 3;
        }

        assert isSorted(arr);
    }

    private static int calcMaxH(int n) {
        int h;
        for (h = 1; h < n / 3; h = h * 3 + 1) {

        }
        return h;
    }

    private static int getNextH(int h) {
        return (h - 1) / 3;
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private static boolean isSorted(Comparable[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (less(arr[i], arr[i - 1])) {
                return false;
            }
        }

        return true;
    }

    private static void swap(Object[] arr, int i, int j) {
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}