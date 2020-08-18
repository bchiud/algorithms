package com.bradychiu.algs4.elementarysymboltrees;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class InOrderTraversalConstantExtraSpaceTest {

    private BinaryTree<Integer> bt;
    private InOrderTraversalConstantExtraSpace iotces;

    @BeforeEach
    void setUp() {
        bt = new BinaryTree(20);
        bt.root.left = new BinaryTreeNode(10);
        bt.root.left.left = new BinaryTreeNode(5);
        bt.root.left.right = new BinaryTreeNode(15);
        bt.root.right = new BinaryTreeNode(30);
        bt.root.right.left = new BinaryTreeNode(25);
        bt.root.right.right = new BinaryTreeNode(35);

        iotces = new InOrderTraversalConstantExtraSpace(bt);
    }

    @AfterEach
    void tearDown() {
        bt = null;
    }

    @Test
    void traverse() {
        Integer[] expected = new Integer[]{5, 10, 15, 20, 25, 30, 35};

        assert(Arrays.equals(expected, iotces.traverse()));
    }
}