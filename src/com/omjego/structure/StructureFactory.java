package com.omjego.structure;

import com.omjego.structure.hashmap.HashTable;
import com.omjego.structure.ternary_st.TernaryTree;
import com.omjego.structure.treemap.RBTree;
import com.omjego.structure.trie.Trie;

/**
 * Singleton class, on request gives instance of subclass of structure.
 * Created by OMKAR JADHAV on 5/9/2017.
 */

public class StructureFactory {


    private static StructureFactory singleton = null;

    private StructureFactory() {

    }

    private static class SingletonHolder {
        private static final StructureFactory singletonObject = new StructureFactory();
    }
    public static synchronized StructureFactory getInstance() {
        return SingletonHolder.singletonObject;
    }

    public Structure getStructure(String  className) {

        if (className.equals("Trie")) {
            return new Trie();
        }
        else if(className.equals("TernaryTree")){
            return new TernaryTree();
        }
        else if(className.equals("HashTable")) {
            return new HashTable();
        }
            else if(className.equals("RBTree")) {
            return new RBTree();
        }

        throw new RuntimeException("Class specified does not exists");
    }
}
