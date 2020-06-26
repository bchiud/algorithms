package com.bradychiu.algs4.com.bradychiu.algs4.unionfind;

public class WeightedQuickUnion {

    int[] storage;
    int[] size;

    public WeightedQuickUnion(int n) {
        storage = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            storage[i] = i;
            size[i] = 1;
        }
    }

    public int find(int i) {
        while (storage[i] != i) {
            // path compression
            storage[i] = storage[storage[i]];
            i = storage[i];
        }
        return i;
    }

    public void union(int p, int q) {
        int pRootVal = find(p);
        int qRootVal = find(q);
        if (pRootVal == qRootVal)
            return;
        if (size[pRootVal] < size[qRootVal]) {
            storage[pRootVal] = qRootVal;
            size[qRootVal] += size[pRootVal];
        } else {
            storage[qRootVal] = pRootVal;
            size[pRootVal] += size[qRootVal];
        }

    }
}
