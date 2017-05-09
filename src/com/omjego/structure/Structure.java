package com.omjego.structure;

/**
 * Created by OMKAR JADHAV on 5/5/2017.
 */

import com.omjego.word.Word;

import java.util.List;

/**
 * Provides a template for underlying data structure
 */
public interface Structure {

    public Word  find(String s);
    public boolean exists(String s);
    public void addWord(String s, String meaning);
    public Word getRandom();
    public List<String> getSynonyms(String s);

}
