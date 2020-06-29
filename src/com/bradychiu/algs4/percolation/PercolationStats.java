package com.bradychiu.algs4.percolation;

import edu.princeton.cs.algs4.*;

public class PercolationStats {

    private static final String ERROR_POSITIVE_N = "N must be a positive integer";
    private static final String ERROR_POSITIVE_TRIALS = "Trials must be a positive integer";

    private static final double ZSCORE_95 = 1.96;
    private final int n;
    private final int trials;
    private final double[] results;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0)
            throw new IllegalArgumentException(ERROR_POSITIVE_N);
        if (trials <= 0)
            throw new IllegalArgumentException(ERROR_POSITIVE_TRIALS);

        this.n = n;
        int sites = this.n * this.n;
        this.trials = trials;

        results = new double[this.trials];
        for (int i = 0; i < this.trials; i++) {
            results[i] = (double) runMonteCarloSim() / sites;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(results);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(results);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (ZSCORE_95 * stddev() / Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (ZSCORE_95 * stddev() / Math.sqrt(trials));
    }

    private void openRandomSite(Percolation p) {
        boolean opened = false;

        while (!opened) {
            int row = StdRandom.uniform(1, n + 1);
            int col = StdRandom.uniform(1, n + 1);

            if (!p.isOpen(row, col)) {
                p.open(row, col);
                opened = true;
            }
        }
    }

    private int runMonteCarloSim() {
        Percolation p = new Percolation(n);

        while (!p.percolates()) {
            openRandomSite(p);
        }

        return p.numberOfOpenSites();
    }

    // test client (see below)
    public static void main(String[] args) {
        Stopwatch stopwatch = new Stopwatch();
        // int n = Integer.parseInt(args[0]);
        // int t = Integer.parseInt(args[1]);
        int n = StdIn.readInt();
        int t = StdIn.readInt();
        PercolationStats ps = new PercolationStats(n, t);

        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
        StdOut.println("time                    = " + stopwatch.elapsedTime());

    }
}
