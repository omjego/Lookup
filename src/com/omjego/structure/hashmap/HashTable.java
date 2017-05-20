package com.omjego.structure.hashmap;

import com.omjego.structure.Structure;
import com.omjego.word.Word;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by OMKAR JADHAV on 5/21/2017.
 * Copy code, make changes and have fun.
 */


public class HashTable implements Structure  {

    private HashMap<String, Word> hashTable;

    public HashTable() {
        hashTable = new HashMap<>();
    }
    @Override
    public Word find(String s) {
        s = s.toLowerCase();
        return hashTable.get(s);
    }

    @Override
    public boolean exists(String s) {
        s = s.toLowerCase();
        return hashTable.containsKey(s);
    }

    @Override
    public void addWord(String s, String meaning) {
        s = s.toLowerCase();
        if (hashTable.containsKey(s)) {
            throw new RuntimeException(" Word : "+ s + " already exists");
        }
        hashTable.put(s, new Word(s, meaning));
    }

    @Override
    public Word getRandom() {
        return null;
    }

    @Override
    public List<String> getSynonyms(String s) {
        return null;
    }

    @Override
    public void addList(List<String> words, List<String> meaning) {

        Iterator itWor = words.listIterator(), itMean = meaning.listIterator();
        while (itWor.hasNext()) {
            addWord((String) itWor.next(), (String) itMean.next());
        }
    }
}
