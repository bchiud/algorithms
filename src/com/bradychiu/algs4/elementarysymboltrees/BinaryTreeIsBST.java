package com.bradychiu.algs4.elementarysymboltrees;

public class BinaryTreeIsBST<Key extends Comparable<Key>> {

    private BinaryTree tree;
    private Key min;
    private Key max;

    public BinaryTreeIsBST(BinaryTree tree, Key min, Key max) {
        this.tree = tree;
        this.min = min;
        this.max = max;
    }

    public boolean isBST() {
        return isBST(tree.root, min, max);
    }

    private boolean isBST(BinaryTreeNode x, Key min, Key max) {
        if (x == null) {
            return true;
        }

        if (x.key.compareTo(min) < 0 || x.key.compareTo(max) > 0) {
            return false;
        }

        return isBST(x.left, min, (Key) x.key) && isBST(x.right, (Key) x.key, max);
    }
}
