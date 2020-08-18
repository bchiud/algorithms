package com.bradychiu.algs4.elementarysymboltrees;

import java.util.ArrayList;
import java.util.List;

public class InOrderTraversalConstantExtraSpace<Key> {

    BinaryTree tree;

    public InOrderTraversalConstantExtraSpace(BinaryTree tree) {
        this.tree = tree;
    }

    public Key[] traverse() {
        return traverse(tree.root);
    }

    private Key[] traverse(BinaryTreeNode root) {
        List<Key> ordered = new ArrayList<Key>();

        if (root != null) {
            BinaryTreeNode current = root;
            while(current != null) {

                if (current.left != null) {
                    BinaryTreeNode pre = current.left;

                    while (pre.right != null && pre.right != current) {
                        pre = pre.right;
                    }

                    if (pre.right == null) {
                        pre.right = current;
                        current = current.left;
                    } else {
                        pre.right = null;
                        ordered.add((Key) current.key);
                        current = current.right;
                    }
                } else {
                    ordered.add((Key) current.key);
                    current = current.right;
                }
            }
        }

        return (Key[]) ordered.toArray();
    }

}
