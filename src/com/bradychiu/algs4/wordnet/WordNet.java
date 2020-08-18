package com.bradychiu.algs4.wordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordNet {

    private final Digraph digraph;
    private final Map<String, Set<Integer>> wordToSynsetIds;
    private Map<Integer, String> synsets;
    private final SAP sap;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null)
            throw new IllegalArgumentException("Two input files need to be provided");

        this.synsets = new HashMap<>();
        this.wordToSynsetIds = constructSynsets(new In(synsets));
        this.digraph = constructDigraph(new In(hypernyms));
        this.sap = new SAP(digraph);

        if (!isRootedDag()) {
            throw new IllegalArgumentException("Input is not a rooted DAG");
        }
    }

    private Map<String, Set<Integer>> constructSynsets(In in) {
        Map<String, Set<Integer>> out = new HashMap<>();

        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] fields = line.split(",");

            int id = Integer.parseInt(fields[0]);
            List<String> synset = Arrays.asList(fields[1].split(" "));

            for (String synonym : synset) {
                if (out.containsKey(synonym))
                    out.get(synonym).add(id);
                else {
                    Set<Integer> ids = new HashSet<Integer>();
                    ids.add(id);
                    out.put(synonym, ids);
                }
            }

            synsets.put(id, fields[1]);
        }

        return out;
    }

    private Digraph constructDigraph(In in) {
        Digraph out = new Digraph(synsets.size());

        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] fields = line.split(",");

            for (int i = 1; i < fields.length; i++)
                out.addEdge(Integer.parseInt(fields[0]), Integer.parseInt(fields[i]));
        }

        return out;
    }

    private boolean isRootedDag() {
        DirectedCycle cycle = new DirectedCycle(digraph);

        int numRoots = 0;
        for (int v = 0; v < digraph.V(); v++)
            if (digraph.outdegree(v) == 0)
                numRoots++;

        return !cycle.hasCycle() && numRoots == 1;
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return Collections.unmodifiableCollection(wordToSynsetIds.keySet());
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null)
            throw new IllegalArgumentException("Null word.");

        return wordToSynsetIds.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        validateNouns(nounA, nounB);

        Iterable<Integer> nounsA = wordToSynsetIds.get(nounA);
        Iterable<Integer> nounsB = wordToSynsetIds.get(nounB);
        return sap.length(nounsA, nounsB);
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        validateNouns(nounA, nounB);

        Set<Integer> nounsA = wordToSynsetIds.get(nounA);
        Set<Integer> nounsB = wordToSynsetIds.get(nounB);
        int ancestorIndex = sap.ancestor(nounsA, nounsB);
        return synsets.get(ancestorIndex);
    }

    private void validateNouns(String nounA, String nounB) {
        if (nounA == null)  throw new IllegalArgumentException("nounA is null");
        if (nounB == null)  throw new IllegalArgumentException("nounB is null");
        if (!isNoun(nounA)) throw new IllegalArgumentException("nounA is not a WordNet noun");
        if (!isNoun(nounB)) throw new IllegalArgumentException("nounB is not a WordNet noun");
    }

    // do unit testing of this class
    public static void main(String[] args) {

    }
}
