package com.bradychiu.algs4.elementarysorts;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class DutchNationalFlag {

    private Pebble[] pebbles;

    public enum Pebble {
        Red, White, Blue
    }

    public DutchNationalFlag(Pebble[] pebbles) {
        this.pebbles = pebbles;
    }

    public void sortDutchNationalFlag() {
        int red = 0;
        int blue = pebbles.length - 1;
        int i = 0;
        while ( i <= blue) {
            if(color(i) == Pebble.Red) {
                swap(red++, i);
            } else if (color(i) == Pebble.Blue) {
                swap(i, blue--);
            } else {
                i++;
            }
            StdOut.println(red + "," + i + "," + blue);
        }
    }

    private Pebble color(int i) {
        return pebbles[i];
    }

    private void swap(int i, int j) {
        Pebble temp = pebbles[i];
        pebbles[i] = pebbles[j];
        pebbles[j] = temp;
    }

    private Pebble randomPebble() {
        Pebble[] colors = Pebble.values();
        return colors[StdRandom.uniform(Pebble.values().length)];
    }

    public static void main(String[] args) {
        int n = 21;
        Pebble[] pebbles = new Pebble[n];

        DutchNationalFlag dnf = new DutchNationalFlag(pebbles);

        for(int i = 0; i < n; i++) {
            pebbles[i] = dnf.randomPebble();
        }

        StdOut.println("Pre Sort:\t" + Arrays.toString(pebbles));
        dnf.sortDutchNationalFlag();
        StdOut.println("Post Sort:\t" + Arrays.toString(pebbles));
    }

}
