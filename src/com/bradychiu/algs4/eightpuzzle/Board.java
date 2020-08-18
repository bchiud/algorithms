package com.bradychiu.algs4.eightpuzzle;

import java.util.Arrays;
import java.util.LinkedList;

public class Board {
    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)

    private final int n;
    private final int[][] board;

    public Board(int[][] tiles) {
        board = copy(tiles);
        n = board.length;
    }

    // string representation of this board
    public String toString() {
        StringBuilder boardString = new StringBuilder()
                .append(n);

        for (int row = 0; row < n; row++) {
            boardString.append("\n");

            for (int col = 0; col < n; col++) {
                boardString.append(" ")
                        .append(board[row][col]);
            }
        }

        return boardString.toString();
    }

    // board dimension n
    public int dimension() {
        return this.n;
    }

    // number of tiles out of place
    public int hamming() {
        int hamming = 0;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (board[row][col] != 0 && !correctPosition(row, col)) {
                    hamming++;
                }

            }
        }

        return hamming;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int manhattan = 0;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (board[row][col] != 0 && !correctPosition(row, col)) {
                    int currentTile = board[row][col];
                    int correctRow = (currentTile - 1) / n;
                    int correctCol = (currentTile - 1) % n;

                    manhattan += Math.abs(correctRow - row) + Math.abs(correctCol - col);
                }
            }
        }

        return manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) {
            return true;
        } else if (y == null) {
            return false;
        } else if (y.getClass() != this.getClass()) {
            return false;
        }
        Board that = (Board) y;
        return Arrays.deepEquals(this.board, that.board);
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        LinkedList<Board> neighbors = new LinkedList<>();

        int[] blankLocation = blankLocation();
        int row = blankLocation[0];
        int col = blankLocation[1];

        if (row > 0)
            neighbors.add(new Board(swap(row, col, row - 1, col)));
        if (row < n - 1)
            neighbors.add(new Board(swap(row, col, row + 1, col)));
        if (col > 0)
            neighbors.add(new Board(swap(row, col, row, col - 1)));
        if (col < n - 1)
            neighbors.add(new Board(swap(row, col, row, col + 1)));

        return neighbors;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        if (board[0][0] != 0 && board[0][1] != 0) {
            return new Board(swap(0, 0, 0, 1));
        } else {
            return new Board(swap(1, 0, 1, 1));
        }
    }

    private int[] blankLocation() {
        int[] location = new int[2];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (board[row][col] == 0) {
                    location[0] = row;
                    location[1] = col;
                    return location;
                }
            }
        }

        throw new RuntimeException("No blank tile found");
    }

    private int[][] copy(int[][] original) {
        int[][] copy = new int[original.length][original.length];
        for (int row = 0; row < original.length; row++) {
            for (int col = 0; col < original.length; col++) {
                copy[row][col] = original[row][col];
            }
        }
        return copy;
    }

    private boolean correctPosition(int row, int col) {
        if (board[row][col] == 0) {
            return row == n - 1 && col == n - 1;
        } else {
            return board[row][col] == row * n + col + 1;
        }
    }


    private int[][] swap(int oldRow, int oldCol, int newRow, int newCol) {
        int[][] copy = copy(board);
        int temp = copy[oldRow][oldCol];
        copy[oldRow][oldCol] = copy[newRow][newCol];
        copy[newRow][newCol] = temp;
        return copy;
    }

    // unit testing (not graded)
    public static void main(String[] args) {

    }
}
