package com.bradychiu.algs4.unionfind;

import java.util.Arrays;

public class WeightedQuickUnion {
    private int count;
    private int[] storage;
    private int[] size;

    public WeightedQuickUnion(int n) {
        count = n;
        storage = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            storage[i] = i;
            size[i] = 1;
        }
    }

    public int count() {
        return count;
    }

    public String storageToString() {
        return Arrays.toString(storage);
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
        count--;
    }

    public static void main(String[] args) {
        //        int n = StdIn.readInt();
        //        WeightedQuickUnion uf = new WeightedQuickUnion(n);
        //        while (!StdIn.isEmpty()) {
        //            int p = StdIn.readInt();
        //            int q = StdIn.readInt();
        //            if (uf.find(p) == uf.find(q)) continue;
        //            uf.union(p, q);
        //            StdOut.println(p + " " + q);
        //        }
        //        StdOut.println(uf.count() + " components");

        WeightedQuickUnion wqu = new WeightedQuickUnion(10);
        System.out.println(wqu.count() == 10);
        wqu.union(0, 1);
        System.out.println(wqu.count() == 9);
        wqu.union(2, 3);
        System.out.println(wqu.count() == 8);
        wqu.union(0, 2);
        System.out.println(wqu.count() == 7);
        wqu.union(1, 3);
        System.out.println(wqu.count() == 7);
    }
}
