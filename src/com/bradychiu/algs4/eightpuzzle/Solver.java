package com.bradychiu.algs4.eightpuzzle;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

    private Move finalMove;

    // find a solution to the initial board (using the A* algorithm)

    /**
     * insert the initial search node (the initial board, 0 moves, and a null previous search node) into a priority queue.
     * Then, delete from the priority queue the search node with the minimum priority,
     * and insert onto the priority queue all neighboring search nodes
     * (those that can be reached in one move from the dequeued search node)
     */
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException("Solver initialized with null input");
        }

        MinPQ<Move> moves = new MinPQ<>();
        moves.insert(new Move(initial));

        MinPQ<Move> twinMoves = new MinPQ<>();
        twinMoves.insert(new Move(initial.twin()));

        while (true) {
            Move current = moves.delMin();

            if (current.board.isGoal()) {
                finalMove = current;
                break;
            }

            for (Board board : current.board.neighbors()) {
                if (current.previous == null || !board.equals(current.previous.board)) {
                    moves.insert(new Move(board, current));
                }
            }

            current = twinMoves.delMin();

            if (current.board.isGoal()) {
                break;
            }

            for (Board board : current.board.neighbors()) {
                if (current.previous == null || !board.equals(current.previous.board)) {
                    twinMoves.insert(new Move(board, current));
                }
            }
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return finalMove != null;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return isSolvable() ? finalMove.moves : -1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!isSolvable()) {
            return null;
        }

        Stack<Board> solution = new Stack<Board>();

        Move current = finalMove;
        while (current.previous != null) {
            solution.push(current.board);
            current = current.previous;
        }
        solution.push(current.board);

        return solution;
    }

    private class Move implements Comparable<Move> {
        private final Board board;
        private Move previous;
        private int moves;
        private int priority;

        public Move(Board board) {
            this(board, null);
        }

        public Move(Board board, Move previous) {
            this.board = board;
            this.previous = previous;
            this.moves = previous != null ? previous.moves + 1 : 0;
            priority = priority();
        }

        public int compareTo(Move move) {
            return this.priority - move.priority;
        }

        private int priority() {
            return board.manhattan() + moves;
        }
    }

    // test client (see below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
