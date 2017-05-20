package com.omjego.dictionary;

import com.omjego.structure.Structure;
import com.omjego.structure.StructureFactory;
import com.omjego.structure.trie.TrieNode;
import com.omjego.word.Word;

import java.io.*;
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
    private static final String favFile = "favourites.txt";
    private static final String dictFile = "words.txt";
    private static final int maxWords = 200000;

    public Dictionary() {

        //Initialize required  data structure. Use Factory Method.
        StructureFactory factory = StructureFactory.getInstance();
        structure = factory.getStructure("Trie");


        //Initialize "recent" list for this session

        loadDictionary();
        recent = new LinkedList<>();
        //Load favourites
        //loadFavourites();


    }

    /**
     * Function load which loads Words from a given file and add them to underlying data structure
     */
    private void loadDictionary() {

        try {
            FileReader reader = new FileReader(dictFile);
            BufferedReader buffer = new BufferedReader(reader);
            String str = null;

            // Modify here. Just checking if it works or not. Word may contain Meaning,
            // synonyms, meaning in other languages and few examples.
            int count = 0;
            while ((str = buffer.readLine()) != null) {

                str = str.trim();
                //Add word to dictionary
                //** Create list and then send list to the structure
                structure.addWord(str, "NULL");
                ++count;
                if (count > maxWords)
                    break;
            }
            System.out.println(count);
            buffer.close();
            reader.close();
            System.err.println("Nodes created :" + TrieNode.count);
            System.err.println("Dictionary loaded successfully : Words loaded- "  + count);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * @param s : String to search into the dictionary
     * @return  : Returns Word object representing the word, otherwise null
     */
    public Word search(String s) {

        return structure.find(s);
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
        favourites = new LinkedList<>();
        try {
            reader = new FileReader("");
            BufferedReader bf = new BufferedReader(reader);
            String s;
            while ((s = bf.readLine()) != null) {
                favourites.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        System.err.println("Favourites loaded successfully");
    }

    public  List<String> getFavourites() {
        return favourites;
    }

    public void addFavourite(String s) {
        if (! favourites.contains(s))
            favourites.add(s);
    }


}
