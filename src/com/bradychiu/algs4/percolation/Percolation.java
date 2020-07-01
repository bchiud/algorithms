package com.bradychiu.algs4.percolation;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private static final boolean CLOSED = false;
    private static final boolean OPEN = true;
    private static final String ERROR_COL_DIMENSIONS = "Column is not within dimensions";
    private static final String ERROR_POSITIVE_INT = "Argument must be a positive integer";
    private static final String ERROR_ROW_DIMENSIONS = "Row is not within dimensions";
    private static final String ERROR_ROW_COL_DIMENSIONS = "Row and column are not within dimensions";

    private final int n;
    private final int sites;
    private final WeightedQuickUnionUF quf;

    private boolean[][] grid;
    private int opens;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n < 1) {
            throw new IllegalArgumentException(ERROR_POSITIVE_INT);
        }

        this.n = n;
        sites = this.n * this.n;

        grid = new boolean[this.n][this.n];
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                grid[i][j] = CLOSED;
            }
        }

        quf = new WeightedQuickUnionUF(sites + 2); // one dummy node each at top and bottom
    }


    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        checkRange(row, col);

        if (isOpen(row, col)) {
            return;
        } else {
            grid[row - 1][col - 1] = OPEN;
            opens++;

            if (row == 1) {
                quf.union(0, getQuickUnionFindIndex(row - 1, col - 1));
            }
            if (row == n) {
                quf.union(sites + 1, getQuickUnionFindIndex(row - 1, col - 1));
            }

            int index = getQuickUnionFindIndex(row - 1, col - 1);
            int rowAbove = row - 1;
            int rowBelow = row + 1;
            int colLeft = col - 1;
            int colRight = col + 1;

            if (row > 1 && isOpen(rowAbove, col)) {
                quf.union(index, getQuickUnionFindIndex(rowAbove - 1, col - 1));
            }
            if (row < n && isOpen(rowBelow, col)) {
                quf.union(index, getQuickUnionFindIndex(rowBelow - 1, col - 1));
            }
            if (col > 1 && isOpen(row, colLeft)) {
                quf.union(index, getQuickUnionFindIndex(row - 1, colLeft - 1));
            }
            if (col < n && isOpen(row, colRight)) {
                quf.union(index, getQuickUnionFindIndex(row - 1, colRight - 1));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkRange(row, col);
        return grid[row - 1][col - 1] == OPEN;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        checkRange(row, col);
        if (isOpen(row, col)) {
            return quf.find(0) == quf.find(getQuickUnionFindIndex(row - 1, col - 1));
        } else {
            return false;
        }
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return opens;
    }

    // does the system percolate?
    public boolean percolates() {
        return quf.find(0) == quf.find(sites + 1);
    }

    private void checkRange(int row, int col) {
        boolean checkRowError = row < 1 || row > n;
        boolean checkColError = col < 1 || col > n;
        if (checkRowError && checkColError) {
            throw new IllegalArgumentException(ERROR_ROW_COL_DIMENSIONS);
        } else if (checkRowError) {
            throw new IllegalArgumentException(ERROR_ROW_DIMENSIONS);
        } else if (checkColError) {
            throw new IllegalArgumentException(ERROR_COL_DIMENSIONS);
        }
    }

    private int getQuickUnionFindIndex(int row, int col) {
        return 1 + row * n + col;
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation p = new Percolation(10);
        StdOut.println(p.isOpen(1, 1) == p.isFull(1, 1));
        p.open(1, 1);
        StdOut.println(p.isOpen(1, 1) == p.isFull(1, 1));
        for (int i = 0; i < p.sites; i++) {
            p.open(i / p.n + 1, (i % p.n) + 1);
        }
        StdOut.println(p.numberOfOpenSites() == p.sites);
    }
}
