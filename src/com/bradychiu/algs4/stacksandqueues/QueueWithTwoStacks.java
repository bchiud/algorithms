package com.bradychiu.algs4.stacksandqueues;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

public class QueueWithTwoStacks<Item> {

    private Stack<Item> pushStack;
    private Stack<Item> popStack;

    public QueueWithTwoStacks() {
        pushStack = new Stack<Item>();
        popStack = new Stack<Item>();
    }

    public void enqueue(Item item) {
        pushStack.push(item);
    }

    public Item dequeue() {

        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        if (popStack.isEmpty()) {
            for (Item item : pushStack) {
                popStack.push(pushStack.pop());
            }

        }
        return popStack.pop();
    }

    public boolean isEmpty() {
        return pushStack.isEmpty() && popStack.isEmpty();
    }

    public int size() {
        return pushStack.size() + popStack.size();
    }

    public static void main(String[] args) {
        QueueWithTwoStacks<Character> miQueue = new QueueWithTwoStacks<>();
        String s1 = "ABCDEF";
        String s2 = "GHIJKL";
        for (char c : s1.toCharArray()) {
            StdOut.println("Adding to queue: " + c);
            miQueue.enqueue(c);
        }
        StdOut.println("Size: " + miQueue.size());
        for (int i = 0; i < 3; i++) {
            StdOut.println("Removing from queue: " + miQueue.dequeue());
        }
        StdOut.println("Size: " + miQueue.size());
        for (char c : s2.toCharArray()) {
            StdOut.println("Adding to queue: " + c);
            miQueue.enqueue(c);
        }
        StdOut.println("Size: " + miQueue.size());
        for (int i = 0; i < 9; i++) {
            StdOut.println("Removing from queue: " + miQueue.dequeue());
        }
        StdOut.println("Size: " + miQueue.size());
        // following line should throw NoSuchElementException
        miQueue.dequeue();
    }
}
