package com.omjego.dictionary;

import com.omjego.structure.Structure;
import com.omjego.word.Word;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by OMKAR JADHAV on 5/5/2017.
 */

/**
 * This class represents main dictionary class.
 */
public class Dictionary {

    Structure structure;
    List<String> favourites, recent;
    public Dictionary() {

        //Initialize "recent" list for this session

        recent = new LinkedList<String>();
        //Load favourites
        loadFavourites();
        //Initialize required  data structure. Use Factory Method.

    }

    public Word search(String s) {
        return null;
    }

    public List<String > getSynonyms(String s) {
        return null;
    }

    public void addWord(String s) {

    }

    public void updateRecent(String s) {

        if (recent.contains(s)) {
            recent.remove(s);
        }
        ((LinkedList)recent).addFirst(s);
    }

    public void loadFavourites()  {

        FileReader reader;
        favourites = new LinkedList<String>();
        try {
            reader = new FileReader("");
            BufferedReader bf = new BufferedReader(reader);
            String s = null;
            while ((s = bf.readLine()) != null) {
                favourites.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        System.err.println("Favourites loaded succesfully");
    }

    public  List<String> getFavourites() {
        return favourites;
    }

    public void addFavourite(String s) {
        if (! favourites.contains(s))
            favourites.add(s);
    }


}
