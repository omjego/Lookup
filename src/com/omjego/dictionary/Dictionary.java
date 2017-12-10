package com.omjego.dictionary;

import com.omjego.auxiliary.Pair;
import com.omjego.structure.Structure;
import com.omjego.structure.StructureFactory;
import com.omjego.word.Word;

import java.io.*;
import java.util.*;

/**
 * Created by OMKAR JADHAV on 5/5/2017.
 */

/**
 * Singleton class
 * This class represents main dictionary class.
 */

public class Dictionary {

    private static final String favFile = "favourites.txt";
    private static final String dictFile = "words.txt";
    private static final int maxWords = 200000;


    private Structure structure;
    private List<String> favourites, recent;
    private  Matcher matcher;

    private static Dictionary singleton = null;


    // Bil Pugh's method  of implementing singleton pattern
    private static class SingletonHolder {
        private static final Dictionary singletonObject = new Dictionary();
    }

    public static Dictionary getInstance() {
        return SingletonHolder.singletonObject;
    }


    private Dictionary() {

        //Initialize required  data structure. Use Factory Method.
        StructureFactory factory = StructureFactory.getInstance();
        structure = factory.getStructure("Trie");

        /*
        TODO Initialize "recent" list for this session
        */


        loadDictionary();
        recent = new LinkedList<>();
        /*
         TODO Load favourites
         loadFavourites();
        */
        matcher = new Matcher();

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
            LinkedList<String> wList , mList;
            wList = new LinkedList<>();
            mList = new LinkedList<>();
            while ((str = buffer.readLine()) != null) {

                str = str.trim();
                //Add word to dictionary
                //** Create list and then send list to the structure
                wList.addLast(str);
                mList.addLast("NULL");
                ++count;
                if (count > maxWords)
                    break;
            }

            // generateTest(wList, 100000);
            structure.addList(wList, mList);

            System.out.println(count);
            buffer.close();
            reader.close();


            //System.err.println("Dictionary loaded successfully : Words loaded- "  + count);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void generateTest(List<String> list, int count)  {
        list = new ArrayList<>(list);
        try {
            FileWriter writer = new FileWriter("test.txt");
            while (count > 0) {
                int wIndex = (int)(Math.random()*list.size());
                writer.write( list.get( wIndex ) + "\n");
                --count;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.err.println("MSG : Tests generated.");
    }
    /**
     *
     * @param s : String to search into the dictionary
     * @return  : Returns Word object representing the word, otherwise null
     */
    public Word search(String s) {
        return structure.find(s);
    }

    public List<Word> suggestWords(String s ) {
        return matcher.findMatch_1(s);
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

    /**
     *  Inner class which holds methods to find nearest match of given word
     */
    class Matcher {


        /**
         * Try  1 : Total brute force
         * Find nearest match of string s differing by one char position.
         * @param s
         * @return
         */
        public List<Word> findMatch_1(String s) {

            List<Word> list = new LinkedList<>();
            s = s.toLowerCase();
            Word result  = structure.find(s);

            if (result != null ) {
                list.add(result);
            }
            StringBuilder sb = new StringBuilder(s);
            for (int i = 0; i < s.length(); i++) {
                for (int j = 97; j <= 122 ; j++) {
                    sb.setCharAt(i, (char)j);
                    String str = sb.toString();
                    if ( str.equals(s) ) {
                        continue;
                    }
                    result =  structure.find( str );
                    if (result != null) {
                        list.add(result);
                    }
                }
                sb.setCharAt(i, s.charAt(i));
            }
            return list;
        }

        //Suggestions Approach 2

        /**
         * Finding all the strings with "Edit distance" <= DIST, and order them using frequency with which this words
         * used in language. Output top 10 words.
         * @param s
         * @return
         */
        public List<Word> findMatch_2(String s) {
            List<Word> list = null;
            s = s.toLowerCase();

            PriorityQueue<Pair<String, Integer> > priorityQueue = new PriorityQueue<>(new Comparator<Pair>() {
                @Override
                public int compare(Pair o1, Pair o2) {
                    Comparable obj1 = (Comparable)o1.second;
                    Comparable obj2 = (Comparable)o2.second;
                    return obj2.compareTo(obj1);
                }
            });
            int i = 0;
            while (  i < 10 ) {
                String str = priorityQueue.poll().first;
                Word word = structure.find(str);
                if (str != null ) {
                    list.add( word );
                }
            }
            return list;
        }

        /**
         * Unfortunately java doesn't support default parameters
         * @param str
         * @param DIST
         * @return
         */
        private List<String>  getStringsEditDistance(String str, int DIST) {

            Set<String> set = new TreeSet<>();
            str = str.toLowerCase();
            int N = str.length();
            char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();

            //deletes
            for (int i = 0; i < N; i++) {
                set.add(str.substring(0, i) + str.substring(i + 1));
            }

            //replaces
            for (int i = 0; i < N; i++) {
                for (Character ch: chars) {
                    set.add(str.substring(0, i) + ch + str.substring(i + 1));
                }
            }

            //transpose - exchange chars at consecutive locations
            for (int i = 0; i < N - 1; i++) {
                set.add(str.substring(0, i) + str.charAt(i + 1) + str.charAt(i) +  (i + 2 < N ? str.substring(i + 2) : ""));
            }
            //inserts
            for (int i = 0; i < N; i++) {
                for (char ch: chars) {
                    set.add(str.substring(0, i) + ch + str.substring(i));
                }
            }

            return new LinkedList<>(set);
        }

    }

}
