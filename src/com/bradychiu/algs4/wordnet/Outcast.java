package com.bradychiu.algs4.wordnet;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {

    private final WordNet wordnet;

    // constructor takes a WordNet object
    public Outcast(WordNet wordnet) {
        this.wordnet = wordnet;
    }

    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns) {
        int maxDist = 0;
        String outcast = "";

        for (String a : nouns) {
            int totalDist = 0;
            for (String b : nouns)
                totalDist += wordnet.distance(a, b);
            if (totalDist > maxDist) {
                maxDist = totalDist;
                outcast = a;
            }
        }

        return outcast;
    }

    // see test client below
    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}
