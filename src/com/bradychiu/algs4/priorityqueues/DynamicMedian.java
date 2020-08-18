package com.bradychiu.algs4.priorityqueues;

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

/**
 * Dynamic median. Design a data type that supports insert in logarithmic time,
 * find-the-median in constant time, and remove-the-median in logarithmic time.
 * If the number of keys in the data type is even, find/remove the lower median.
 */

public class DynamicMedian {

    private MaxPQ<Double> maxPQ;
    private MinPQ<Double> minPQ;

    public DynamicMedian() {
        maxPQ = new MaxPQ<>();
        minPQ = new MinPQ<>();
    }

    public DynamicMedian(Double[] a) {
        this();
        for (Double d : a) {
            insert(d);
        }
    }

    public void insert(Double item) {
        if (maxPQ.size() == 0 && minPQ.size() == 0) {
            maxPQ.insert(item);
        } else {
            if (item.compareTo(getMedian()) < 0) {
                maxPQ.insert(item);

                if (maxPQ.size() - minPQ.size() > 1) {
                    minPQ.insert(maxPQ.delMax());
                }

            } else {
                minPQ.insert(item);

                if (minPQ.size() - maxPQ.size() > 1) {
                    maxPQ.insert(minPQ.delMin());
                }
            }
        }
    }

    public Double getMedian() {
        int maxSize = maxPQ.size();
        int minSize = minPQ.size();

        if (maxSize > minSize) {
            return maxPQ.max();
        } else if ( maxSize < minSize) {
            return minPQ.min();
        } else {
            return ( maxPQ.max() + minPQ.min() ) / 2;
        }
    }

    public Double delMedian() {
        int maxSize = maxPQ.size();
        int minSize = minPQ.size();

        if (maxSize > minSize) {
            return maxPQ.delMax();
        } else {
            return minPQ.delMin();
        }
    }
}
