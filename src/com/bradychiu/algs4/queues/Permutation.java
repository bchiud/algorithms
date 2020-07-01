package com.bradychiu.algs4.queues;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> miRandomizedQueue = new RandomizedQueue<String>();

        if (k != 0) {
            while (!StdIn.isEmpty()) {
                String s = StdIn.readString();

                miRandomizedQueue.enqueue(s);
            }
        }

        while (k != 0) {
            StdOut.println(miRandomizedQueue.dequeue());
            k--;
        }
    }
}
