package com.bradychiu.algs4.unionfind;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.HashMap;

public class SuccessorWithDelete {
    private int count;
    private int[] storage;
    private int[] size;
    private HashMap<Integer, Integer> largest;

    public SuccessorWithDelete(int n) {
        count = n;
        storage = new int[n];
        size = new int[n];
        largest = new HashMap<>();
        for (int i = 0; i < n; i++) {
            storage[i] = i;
            size[i] = 1;
            largest.put(i, i);
        }
    }

    public int count() {
        return count;
    }

    public String storageToString() {
        return Arrays.toString(storage);
    }

    public int find(int i) {
        return largest.get(storage[i]);
    }

    private void updateLargest(int parentRoot, int childRoot) {
        if (largest.get(childRoot) > largest.get(parentRoot)) {
            largest.put(parentRoot, largest.get(childRoot));
        }
        largest.remove(childRoot);
    }

    public void union(int p, int q) {
        int pRootVal = find(p);
        int qRootVal = find(q);
        if (pRootVal == qRootVal)
            return;
        if (size[pRootVal] < size[qRootVal]) {
            storage[pRootVal] = qRootVal;
            size[qRootVal] += size[pRootVal];
            updateLargest(qRootVal, pRootVal);
        } else {
            storage[qRootVal] = pRootVal;
            size[pRootVal] += size[qRootVal];
            updateLargest(pRootVal, qRootVal);
        }
        count--;
    }

    public static void main(String[] args) {
        SuccessorWithDelete wqu = new SuccessorWithDelete(10);
        wqu.union(0, 2);
        wqu.union(8, 4);
        StdOut.println(wqu.find(0) == 2);
        StdOut.println(wqu.find(4) == 8);
        wqu.union(0, 4);
        StdOut.println(wqu.find(0) == 8);
        StdOut.println(wqu.find(2) == 8);
        wqu.union(0, 6);
        StdOut.println(wqu.find(6) == 8);
        wqu.union(1, 9);
        wqu.union(1, 2);
        StdOut.println(wqu.find(4) == 9);
    }
}
