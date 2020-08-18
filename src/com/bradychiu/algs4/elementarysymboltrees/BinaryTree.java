package com.bradychiu.algs4.elementarysymboltrees;

public class BinaryTree<Key extends Comparable<Key>> {

    public BinaryTreeNode root;

    public BinaryTree() {
        this(null);
    }

    public BinaryTree(Key key) {
        root = new BinaryTreeNode(key);
    }
}
