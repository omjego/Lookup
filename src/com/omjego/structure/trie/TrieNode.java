package com.omjego.structure.trie;

import com.omjego.word.*;

/**
 * Represents a node of a standard trie data structure.
 * Created by OMKAR JADHAV on 5/8/2017.
 */
public class TrieNode {

    private TrieNode[] nextLevel;
    private char ch;
    private Object word;     //Just to make Trie generic. Make sure to typecast the result.
    private boolean isEnd;   //Denotes if the given node is end of some string.

    TrieNode() {
        nextLevel = new TrieNode[26];
    }

    TrieNode(char ch, boolean isEnd) {
        this.ch = ch;
        this.isEnd = isEnd;
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
