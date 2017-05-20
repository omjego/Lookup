package com.omjego.structure.ternary_st;

import com.omjego.structure.Structure;
import com.omjego.word.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OMKAR JADHAV on 5/16/2017.
 * Copy code, make changes and have fun.
 */

public class TernaryTree implements Structure{

    private TernaryNode root = null;
    private List<String> words, meaning;

    @Override
    public Word find(String s) {

        char[] data = s.toLowerCase().toCharArray();
        return search(root, data, 0);
    }

    /**
     * Helper function for find.
     *
     **/

    public Word search(TernaryNode node ,char[] data, int index ) {

        if (node == null) {
            //End of branch
            return null;
        }

        if (index == data.length ) { // Check for end of string.
            if (node.isEnd()) {
                return (Word) node.getWord(); // If current node is marked and end then SUCCESS !
            }
            return null; //Word don't exist.
        }

        if (data[index] < node.getCh()) {
            return search( node.getLeft(), data, index);
        }
        else if( data[index] > node.getCh() ) {
            return search(node.getRight(), data, index);
        }
        else {
            search(node.getMid(), data, index + 1);
        }

        return null;
    }

    @Override
    public boolean exists(String s) {
        return find(s) != null;
    }

    /**
     * As this function might create unbalanced tree, we'll use another method which uses divide and conquer
     * strategy on list of words to add them into tree(Refer addList method).
     * @param s String to insert
     * @param meaning Meaning
     */
    @Override
    public void addWord(String s, String meaning) {

        char[] data = s.toLowerCase().toCharArray();
        int i = 0;
        TernaryNode run = this.root;

        if (root == null){
            root = new TernaryNode(data[i]);  //If tree is empty then create root node
        }

        while (i < data.length) {

            if(data[i] < run.getCh()){
                if (run.getLeft() == null) {
                    run.setLeft( new TernaryNode( data[i] ) );
                }
                run = run.getLeft();
            }
            else if(data[i] > run.getCh()) {
                if (run.getRight() == null) {
                    run.setRight( new TernaryNode( data[i] ) );
                }
                run = run.getRight();
            }
            else {
                if (run.getMid() == null ) {
                    run.setMid( new TernaryNode( data[i] ) );
                }
                run = run.getMid();
                ++i;
            }
        }

        if (run.isEnd()) {
            throw new RuntimeException("Word already exists : " + s);
        }

        //Mark end of string '\0'
        run.setEnd(true);
        run.setWord( new Word(s, meaning) );
    }


    /**
     * Function to add words recursively by using divide and conquer, so that it remains balanced.
     * @param left Start index
     * @param right Finish
     */
    private void addRange(int left, int right) {
        if (left > right) {
            return;
        }
        int mid = (left + right) >> 1;
        addWord(words.get(mid) , meaning.get(mid));

        //Recursively add words from left and right half.
        addRange(left, mid - 1);
        addRange(mid + 1, right);

    }

    @Override
    public void addList(List<String> words, List<String> meaning) {
        this.words = new ArrayList<>(words);
        this.meaning = new ArrayList<>(meaning);
        addRange(0, words.size() - 1);
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

