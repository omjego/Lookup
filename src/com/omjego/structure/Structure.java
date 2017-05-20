package com.omjego.structure;

import com.omjego.word.Word;

import java.util.List;

/**
 * Provides a template for underlying data structure
 */
public interface Structure {

    Word  find(String s);
    boolean exists(String s);
    void addWord(String s, String meaning);
    Word getRandom();
    List<String> getSynonyms(String s);
    void addList(List<String> words, List<String> meaning);
}
