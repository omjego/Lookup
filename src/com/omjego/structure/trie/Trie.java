package com.omjego.structure.trie;


import com.omjego.structure.Structure;
import com.omjego.word.Word;
import java.util.List;

/**
 * Represents Trie and uses TrieNode to build the trie.
 *
 * Created by OMKAR JADHAV on 5/8/2017.
 */
public class Trie implements Structure{

    private TrieNode root;

    Trie() {
        root = new TrieNode();
    }

    /**
     * Checks if the string is in the trie or not
     * @param s
     * @return if string exists returns equivalent Word object otherwise null
     */
    @Override
    public Word find(String s) {

        if( ! exists(s)) {
            return null;
        }
        s = s.toLowerCase();
        TrieNode traveller  = root;
        for (int i = 0 ; i < s.length(); ++i) {
            int index = s.charAt(i) - 97;
            traveller = traveller.getNextLevel(index);
        }
        return (Word)traveller.getWord();
    }

    @Override
    public boolean exists(String s) {

        s = s.toLowerCase();

        TrieNode traveller  = root;
        for (int i = 0 ; i < s.length(); ++i) {

            int index = s.charAt(i) - 97;
            TrieNode next = traveller.getNextLevel(index);

            //If word doesn't exist
            if (next == null) {
                return  false;
            }
            traveller = traveller.getNextLevel(index);
        }

        return traveller.isEnd();
    }


    @Override
    public void addWord(String s, String meaning) {

        Word newWord = new Word(s, meaning);
        s = s.toLowerCase();

        TrieNode traveller  = root;
        for (int i = 0 ; i < s.length(); ++i) {

            int index = s.charAt(i) - 97;
            TrieNode next = traveller.getNextLevel(index);

            //If word doesn't exist
            if (next == null) {
                TrieNode newNode = new TrieNode(s.charAt(i), false);
                traveller.setNextLevel(index, newNode);
            }
            traveller = traveller.getNextLevel(index);
        }

        if (traveller.isEnd()) {
            throw new RuntimeException(" Word : "+ s + " already exists");
        }
        traveller.setEnd(true);
        traveller.setWord(newWord);
        System.err.println("Word : " + s + " inserted successfully");
    }

    @Override
    public Word getRandom() {
        return null;
    }

    @Override
    public List<String> getSynonyms(String s) {
        return null;
    }
}
