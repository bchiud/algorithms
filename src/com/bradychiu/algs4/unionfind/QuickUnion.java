package com.bradychiu.algs4.unionfind;

public class QuickUnion {
    private int[] storage;

    public QuickUnion(int n) {
        storage = new int[n];
        for (int i = 0; i < n; i++) {
            storage[i] = i;
        }
    }

    public int find(int i) {
        while (storage[i] != i) {
            i = storage[i];
        }
        return i;
    }

    public void union(int p, int q) {
        // change root of p to point to root of q
        int pVal = find(p);
        int qVal = find(q);
        storage[pVal] = qVal;
    }
}
