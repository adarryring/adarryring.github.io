package io.github.darryring.learn.java.trie;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        final String rootPath = "C:\\Users\\xh\\Documents\\IdeaProjects\\javatest\\src\\test\\java\\trie/";

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile(rootPath + "pride-and-prejudice.txt", words) &&
                FileOperation.readFile(rootPath + "a-tale-of-two-cities.txt", words)) {

            // Test BST
            long startTime = System.nanoTime();

            BSTSet<String> set = new BSTSet<>();
            for (String word : words)
                set.add(word);

            for (String word : words)
                set.contains(word);

            long endTime = System.nanoTime();

            double time = (endTime - startTime) / 1000000000.0;

            System.out.println("Total different words: " + set.getSize());
            System.out.println("BSTSet: " + time + " s");

            // ---

            // Test TreeMap Trie
            startTime = System.nanoTime();

            Trie trie = new Trie();
            for (String word : words)
                trie.add(word);

            for (String word : words)
                trie.contains(word);

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;

            System.out.println("Total different words: " + trie.getSize());
            System.out.println("TreeMap Trie: " + time + " s");

            // ---

            // Test HashMap Trie
            startTime = System.nanoTime();

            Trie2 trie2 = new Trie2();
            for (String word : words)
                trie2.add(word);

            for (String word : words)
                trie2.contains(word);

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;

            System.out.println("Total different words: " + trie.getSize());
            System.out.println("HashMap Trie: " + time + " s");

            // ---

            // Test Array(Map) Trie
            startTime = System.nanoTime();

            Trie3 trie3 = new Trie3();
            for (String word : words)
                trie3.add(word);

            for (String word : words)
                trie3.contains(word);

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;

            System.out.println("Total different words: " + trie.getSize());
            System.out.println("Array(Map) Trie: " + time + " s");

            // ---

            // Test AVLSet
            startTime = System.nanoTime();

            AVLSet<String> trie4 = new AVLSet<>();
            for (String word : words)
                trie4.add(word);

            for (String word : words)
                trie4.contains(word);

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;

            System.out.println("Total different words: " + trie4.getSize());
            System.out.println("AVLSet: " + time + " s");

            // Test HashTable
            startTime = System.nanoTime();

            // HashTable<String, Integer> ht = new HashTable<>();
            HashTable<String, Integer> ht = new HashTable<>(131071);
            for (String word : words) {
                if (ht.contains(word))
                    ht.set(word, ht.get(word) + 1);
                else
                    ht.add(word, 1);
            }

            for(String word: words)
                ht.contains(word);

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;
            System.out.println("HashTable: " + time + " s");
        }
    }
}
