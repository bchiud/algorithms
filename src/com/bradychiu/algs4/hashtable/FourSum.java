package com.bradychiu.algs4.hashtable;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.ThreeSumFast;

import java.util.*;

public class FourSum {

    /**
     * 4-SUM. Given an array a[] of n integers, the 4-SUM problem is to determine if there exist distinct indices
     * i, j, k, and l such that a[i] + a[j] = a[k] + a[l].
     * Design an algorithm for the 4-SUM problem that takes time proportional to n^2 (under suitable technical assumptions).
     */

    private static class Pair {
        int index0;
        int index1;

        public Pair(int index0, int index1) {
            this.index0 = index0;
            this.index1 = index1;
        }
    }

    /**
     * Time: O(n^3)
     * Space: O(1)
     */
    public static boolean fourSumPointers(Integer[] a) {
        int n = a.length;
        int count = 0;

        if (n < 4)
            throw new IllegalArgumentException("Not enough integers");

        Arrays.sort(a);

        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                int left = j + 1;
                int right = n - 1;

                while (left < right) {
                    int outer = a[i] + a[right];
                    int inner = a[j] + a[left];
                    if (outer > inner) {
                        right--;
                    } else if (outer < inner){
                        left++;
                    } else { // iRight == jLeft
                        count++;
                        left++;
                    }
                }
            }
        }

        return count > 0;
    }

    /**
     * Time: O(n^4) - n^2 possible sums * n^2 iteration over sums
     * Space: O(n^2)
     */
    public static boolean fourSumHashTwoLoop(Integer[] a) {
        int n = a.length;
        int count = 0;

        if (n < 4)
            throw new IllegalArgumentException("Not enough integers");

        HashMap<Integer, List<Pair>> sumPairs = new HashMap<>();

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum = a[i] + a[j];

                List<Pair> pairs = sumPairs.get(sum);
                if (pairs == null)
                    sumPairs.put(sum, new ArrayList<>());
                sumPairs.get(sum).add(new Pair(i, j));
            }
        }

        for (List<Pair> entry : sumPairs.values()) {
            Pair[] pairs = new Pair[entry.size()];
            pairs = entry.toArray(pairs);
            for (int i = 0; i < entry.size() - 1; i++) {
                for (int j = i + 1; j < entry.size(); j++) {
                    if (pairs[i].index0 != pairs[j].index0
                            && pairs[i].index0 != pairs[j].index1
                            && pairs[i].index1 != pairs[j].index0
                            && pairs[i].index1 != pairs[j].index1)
                        count++;
                }
            }
        }

        return count > 0;
    }

    /**
     * Time: O(n^4)
     * Space: O(n^2)
     */
    public static boolean fourSumHashOneLoop(Integer[] a) {
        int n = a.length;
        int count = 0;

        if (n < 4)
            throw new IllegalArgumentException("Not enough integers");

        // sum < i < j >>>
        HashMap<Integer, HashMap<Integer, HashSet<Integer>>> sumPairs = new HashMap<>();

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum = a[i] + a[j];

                HashMap<Integer, HashSet<Integer>> pairs = sumPairs.get(sum);

                if (pairs != null) { // entry for given sum exists
                    int keySize = pairs.size();
                    boolean keyContainsI = pairs.containsKey(i);
                    boolean keyContainsJ = pairs.containsKey(j);

                    if ((!keyContainsI && !keyContainsJ && keySize >= 1)
                            || (keyContainsI && !keyContainsJ && keySize >= 2)
                            || (!keyContainsI && keyContainsJ && keySize >= 2)
                            || (keyContainsI && keyContainsJ && keySize >= 3)) {
                        for (Integer key : pairs.keySet()) {
                            if (key != i || key != j) {
                                HashSet<Integer> values = pairs.get(key);
                                boolean valueContainsI = values.contains(i);
                                boolean valueContainsJ = values.contains(j);
                                int valueSize = values.size();

                                if ((!valueContainsI && !valueContainsJ && valueSize >= 1)
                                        || (valueContainsI && !valueContainsJ && valueSize >= 2)
                                        || (!valueContainsI && valueContainsJ && valueSize >= 2)
                                        || (valueContainsI && valueContainsJ && valueSize >= 3))
                                    count++;
                            }
                        }
                    }
                } else { // entry for given sum does not exist
                    pairs = new HashMap<>();
                }

                HashSet<Integer> jValues = pairs.get(i);
                if(jValues == null) {
                    jValues = new HashSet<>();
                }
                jValues.add(j);
                pairs.put(i, jValues);
                sumPairs.put(sum, pairs);

                // for debugging:
                // printHmHmHsSumPairs(sumPairs);
            }
        }

        return count > 0;
    }

    /**
     * quadratic not possible for this version of 4sum?
     */
    // public static boolean fourSumQuadratic(Integer[] a) {
    //     int n = a.length;
    //
    //     if (n < 4)
    //         throw new IllegalArgumentException("Not enough integers");
    //
    //     HashMap<Integer, HashSet<Pair>> sumPairs = new HashMap<>();
    //
    //     for (int i = 0; i < n - 1; i++) {
    //         for (int j = i + 1; j < n; j++) {
    //             int sum = a[i] + a[j];
    //             sumPairs.computeIfAbsent(a[i] + a[j], k -> new HashSet<>()).add(new Pair(i, j));
    //         }
    //     }
    //
    //     for (int i = 0; i < n - 1; i++) {
    //         for (int j = i + 1; j < n; j++) {
    //
    //         }
    //     }
    //
    //     return false;
    // }

    private static void printHmHmHsSumPairs(HashMap<Integer, HashMap<Integer, HashSet<Integer>>> sumPairs) {
        for (Integer sum : sumPairs.keySet()) {
            String s = sum + " :";
            HashMap<Integer, HashSet<Integer>> pairs = sumPairs.get(sum);
            for (Integer index0 : pairs.keySet()) {
                for (Integer index1 : pairs.get(index0)) {
                    s += " (" + index0 + ", " + index1 + ")";
                }
            }
            System.out.println(s);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println(fourSumPointers(new Integer[]{1, 3, 6, 7}));
    }
}
