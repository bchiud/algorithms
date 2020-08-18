package com.bradychiu.algs4.elementarysymboltrees;

import edu.princeton.cs.algs4.StdOut;

public class JavaAutoboxingEquals {
    public static void main(String[] args) {
        double a = 0.0;
        double b = -0.0;
        StdOut.println(a == b); // true

        Double x = a;
        Double y = b;
        StdOut.println(x.equals(y)); // false

        a = Double.NaN;
        b = Double.NaN;
        StdOut.println(a == b); // false;

        x = a;
        y = b;
        StdOut.println(x.equals(y)); // true
    }
}
