// package com.bradychiu.algs4.priorityqueues;
//
// import com.bradychiu.algs4.queues.RandomizedQueue;
// import edu.princeton.cs.algs4.StdRandom;
//
// /**
//  * Randomized priority queue.
//  * Describe how to add the methods sample() and delRandom() to our binary heap implementation.
//  * The two methods return a key that is chosen uniformly at random among the remaining keys,
//  * with the latter method also removing that key. The sample() method should take constant time;
//  * the delRandom() method should take logarithmic time. Do not worry about resizing the underlying array.
//  */
//
// public class RandomizedPriorityQueues<Key extends Comparable<Key>> {
//
//     private Key[] items;
//     private int size;
//
//     public RandomizedPriorityQueues(int capacity) {
//         items = (Key[]) new Comparable[capacity + 1];
//         size = capacity;
//     }
//
//     public void insert(Key item) {
//         items[++size] = item;
//         swim(size);
//     }
//
//     public Key deleteMax() {
//         Key max = items[1];
//         items[1] = items[size];
//         items[size--] = null;
//         sink(1);
//         return max;
//     }
//
//     public int size() {
//         return size;
//     }
//
//     private void swim(int k) {
//
//     }
//
//     private void sink(int k) {
//
//     }
//
//
//     public Key sample() {
//
//     }
//
//     public Key delSample() {
//         return items[randomIndex()];
//     }
//
//     private int randomIndex() {
//         if (size == 0)
//             throw new NullPointerException("No items in PriorityQueue");
//         if (size == 1)
//             return 1;
//         return StdRandom.uniform(1, size());
//     }
// }
