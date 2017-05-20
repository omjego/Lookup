package com.omjego.structure;

import com.omjego.structure.trie.Trie;

/**
 * Singleton class, on request gives instance of subclass of structure.
 * Created by OMKAR JADHAV on 5/9/2017.
 */

public class StructureFactory {

    private static StructureFactory singleton = null;
    private StructureFactory() {

    }

    public static StructureFactory getInstance() {

        if (singleton == null) {
            singleton = new StructureFactory();
        }
        return singleton;
    }

    public Structure getStructure(String className) {

        if (className.equals("Trie")) {
            return new Trie();
        }

        throw new RuntimeException("Class specified does not exists");
    }
}
