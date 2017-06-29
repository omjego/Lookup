package com.omjego.structure.trie;

/**
 * Represents a node of a standard trie data structure.
 * Created by OMKAR JADHAV on 5/8/2017.
 */
public class TrieNode {

    private static final int ALPHABET_SIZE = 256;
    private TrieNode[] nextLevel;
    private char ch;
    private Object word;     //Just to make Trie generic. Make sure to typecast the result.
    private boolean isEnd;   //Denotes if the given node is end of some string.

    public static int count = 0;

    TrieNode() {

        nextLevel = new TrieNode[ALPHABET_SIZE];
        ++count;
    }

    TrieNode(char ch, boolean isEnd) {
        this.ch = ch;
        this.isEnd = isEnd;
        nextLevel = new TrieNode[ALPHABET_SIZE];
        ++count;
    }

    TrieNode(Object word) {
        this.word  = word;
    }

    public TrieNode getNextLevel(int position) {

        return nextLevel[position];
    }

    public char getCh() {
        return ch;
    }

    public Object getWord() {
        return word;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setNextLevel(int position, TrieNode node) {
        this.nextLevel[position] = node;
    }

    public void setCh(char ch) {
        this.ch = ch;
    }

    public void setWord(Object word) {
        this.word = word;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }
}
