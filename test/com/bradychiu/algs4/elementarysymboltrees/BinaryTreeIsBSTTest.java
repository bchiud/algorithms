package com.bradychiu.algs4.elementarysymboltrees;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeIsBSTTest {

    BinaryTree bt;
    BinaryTreeIsBST btc;

    @BeforeEach
    void setUp() {
        bt = new BinaryTree(20);
        bt.root.left = new BinaryTreeNode(10);
        bt.root.left.left = new BinaryTreeNode(5);
        bt.root.left.right = new BinaryTreeNode(15);
        bt.root.right = new BinaryTreeNode(30);
        bt.root.right.left = new BinaryTreeNode(25);
        bt.root.right.right = new BinaryTreeNode(35);

        btc = new BinaryTreeIsBST(bt, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    @Test
    void isBSTTrue() {
        assertEquals(true, btc.isBST());
    }

    @Test
    void isBSTFalse() {
        bt.root.right.right.right = new BinaryTreeNode(1);
        assertEquals(false, btc.isBST());
    }

    @AfterEach
    void tearDown() {
        bt = null;
    }
}