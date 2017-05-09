package com.omjego.word;

/**
 * Created by OMKAR JADHAV on 5/5/2017.
 */


import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a single Word in dictionary.
 */
public class Word {

    private int id;
    private String word, meaning;
    private List<String> synonyms;


    public Word(String word, String meaning) {
        this.word = word;
        this.meaning = meaning;
        this.synonyms = new LinkedList<String>();

    }

    //Getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public void addSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }



    //Needed in implementation of Dictionary using HashMap as underlying data structure.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Word)) return false;
        Word word1 = (Word) o;
        if (!getWord().equals(word1.getWord()))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        return getWord().hashCode();
    }
}

