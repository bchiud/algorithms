package com.bradychiu.algs4.com.bradychiu.algs4.unionfind;

public class QuickFind {
    private int[] storage;

    public QuickFind(int n) {
        storage = new int[n];
        for (int i = 0; i < n; i++) {
            storage[i] = i;
        }
    }

    public int find(int p) {
        return storage[p];
    }

    public void union(int p, int q) {
        int pVal = storage[p];
        int qVal = storage[q];
        for (int i = 0; i < storage.length; i++) {
//            change all entries with id[p] to id[q
            if (storage[i] == pVal) {
                storage[i] = qVal;
            }
        }
    }

}
