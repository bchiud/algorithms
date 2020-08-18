package com.bradychiu.algs4.elementarysymboltrees;

public class BinaryTreeNode<Key extends Comparable<Key>> {
    Key key;
    BinaryTreeNode left;
    BinaryTreeNode right;

    public BinaryTreeNode(Key key) {
        this.key = key;
    }
}
