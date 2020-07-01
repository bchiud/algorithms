package com.bradychiu.algs4.stacksandqueues;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

public class StackWithMax {

    private Stack<Integer> maxes;
    private Stack<Integer> values;

    public StackWithMax() {
        maxes = new Stack<>();
        values = new Stack<>();
    }

    public void push(Integer i) {
        if (maxes.isEmpty() || i >= maxes.peek()) {
            maxes.push(i);
        }
        values.push(i);
    }

    public Integer pop() {
        if (size() == 0) {
            throw new NoSuchElementException();
        } else if (values.peek() == maxes.peek()) {
            maxes.pop();
        }
        return values.pop();
    }

    public Integer max() {
        if (size() == 0) {
            throw new NoSuchElementException();
        } else {
            return maxes.peek();
        }
    }

    public int size() {
        return values.size();
    }

    public static void main(String[] args) {
        StackWithMax miStack = new StackWithMax();
        miStack.push(1);
        miStack.push(3);
        miStack.push(7);
        miStack.push(5);
        miStack.push(9);
        miStack.push(9);
        StdOut.println(miStack.max() == 9);
        miStack.pop();
        StdOut.println(miStack.max() == 9);
        miStack.pop();
        StdOut.println(miStack.max() == 7);
        miStack.pop();
        StdOut.println(miStack.max() == 7);
    }

}
