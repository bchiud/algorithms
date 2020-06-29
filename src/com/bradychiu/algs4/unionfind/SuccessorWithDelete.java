package com.bradychiu.algs4.unionfind;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class SuccessorWithDelete {
    private int count;
    private int[] storage;
    private int[] size;
    private int[] largest;
    WeightedQuickUnionFindLargest quf;

    public SuccessorWithDelete(int n) {
        count = n;
        storage = new int[n];
        size = new int[n];
        largest = new int [n];
        for (int i = 0; i < n; i++) {
            storage[i] = i;
            size[i] = 1;
            largest[i] = i;
        }
        quf = new WeightedQuickUnionFindLargest(n);
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

    public int remove(int i) {
        if(i == count - 1) {
            return i;
        } else {
            union(i, i + 1);
            return largest[i];
        }
    }

    public void union(int p, int q) {
        int pRootVal = find(p);
        int qRootVal = find(q);
        if (pRootVal == qRootVal)
            return;
        if (size[pRootVal] < size[qRootVal]) {
            storage[pRootVal] = qRootVal;
            size[qRootVal] += size[pRootVal];
            if(largest[qRootVal] < largest[pRootVal]) {
                largest[qRootVal] = largest[pRootVal];
            }
        } else {
            storage[qRootVal] = pRootVal;
            size[pRootVal] += size[qRootVal];
            if(largest[pRootVal] < largest[qRootVal]) {
                largest[pRootVal] = largest[qRootVal];
            }
        }
        count--;
    }

    public static void main(String[] args) {
        SuccessorWithDelete swd = new SuccessorWithDelete(10);
        swd.remove(2);
        StdOut.println(swd.remove(2) == 3);
        swd.remove(3);
        StdOut.println(swd.remove(2) == 4);
        StdOut.println(swd.remove(8) == 8);
        swd.remove(8);
        StdOut.println(swd.remove(8) == 9);
        swd.remove(9);
        StdOut.println(swd.remove(8) == -1);
        swd.remove(5);
        swd.remove(4);
        StdOut.println(swd.remove(3) == 6);
    }
}