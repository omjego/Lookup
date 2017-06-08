package com.omjego.structure.treemap;

import com.omjego.structure.Structure;
import com.omjego.word.Word;

import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by OMKAR JADHAV on 5/21/2017.
 * Copy code, make changes and have fun.
 * /

 /**
 *
 * Internally Java uses RBTrees (kind of balanced binary search tree) for TreeMap, so that
 * insertion,deletion and search works in log2(N) time.
 */
public class RBTree implements Structure{

    private TreeMap<String, Word> tree;
    public RBTree() {
        tree = new TreeMap<>();
    }
    @Override
    public Word find(String s) {
        s = s.toLowerCase();
        return tree.get(s);
    }

    @Override
    public boolean exists(String s) {
        s = s.toLowerCase();
        return tree.containsKey(s);
    }

    @Override
    public void addWord(String s, String meaning) {
        s = s.toLowerCase();
        if (tree.containsKey(s)) {
            throw new RuntimeException("Word " + s + " already exist.");
        }
        tree.put(s, new Word(s, meaning));
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
