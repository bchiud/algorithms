package com.bradychiu.algs4.analysisofalgorithms;

/**
 * 3-SUM in quadratic time. Design an algorithm for the 3-SUM problem that takes time proportional to n^2
 * in the worst case. You may assume that you can sort the n integers in time proportional to n^2 or better.
 *
 * Hint: given an integer x and a sorted array a[] of n distinct integers,
 * design a linear-time algorithm to determine if there exists two distinct indices i and j such that a[i] + a[j] == x.
 */

public class ThreeSum {

    public static int threeSumCubic(int[] a, int x) {
        int n = a.length;
        int count = 0;

        for (int i = 0; i < a.length - 2; i++) {
            for (int j = i + 1; j < a.length - 1; j++) {
                for (int k = j + 1; k < a.length; k++) {
                    if (a[i] + a[j] + a[k] == x) {
                        System.out.println(i + ": " + a[i] + ", " + j + ": " + a[j] + ", " + k + ": " + a[k]);
                        count++;
                    }
                }
            }
        }

        return count;
    }

    /**
     * Time: O(n^2)
     * Space: O(n)
     */
    public static int threeSumQuadratic(int[] a, int x) {
        int n = a.length;
        int count = 0;

        for (int i = 0; i < n - 2; i++) {
            if (a[i] > x)
                break;

            if (i == 0 || (i > 0 && a[i] != a[i - 1])) {
                int j = i + 1;
                int k = n - 1;
                int target = x - a[i];
                while (j < k) {
                    if (a[j] + a[k] == target) {
                        count++;
                        while (j < k && a[j] == a[j + 1])
                            j++;
                        while (j < k && a[k] == a[k - 1])
                            k--;
                        j++;
                        k--;
                    } else if (a[j] + a[k] < target){
                        j++;
                    } else {
                        k--;
                    }
                }
            }
        }

        return count;
    }

}
