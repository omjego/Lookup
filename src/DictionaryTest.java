import com.omjego.dictionary.Dictionary;
import com.omjego.structure.trie.Trie;
import com.omjego.structure.trie.TrieNode;
import com.omjego.word.Word;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by OMKAR JADHAV on 5/9/2017.
 */

public class DictionaryTest {

    public static  void main(String []args) throws IOException {

        //Load test file
        FileReader fileReader = new FileReader("test.txt");
        BufferedReader buffer = new BufferedReader(fileReader);
        List<String> list = new LinkedList<>();
        String str = null;
        while ((str = buffer.readLine()) != null) {
            str = str.trim();
            list.add(str);
        }

        //Fire queries
        Runtime runtime = Runtime.getRuntime();
        long initialMemory = runtime.totalMemory() - runtime.freeMemory();
        Dictionary dict = Dictionary.getInstance();

        long startTime = System.currentTimeMillis();

        for (String testWord : list) {
            Word result = dict.search(testWord);
        }

        long endTime = System.currentTimeMillis();

        System.out.println("Time taken : " + (endTime - startTime)  + " ms");
        long finalMemory = runtime.totalMemory() - runtime.freeMemory();
        double MB = 1024.0 * 1024.0;
        System.err.println("Memory used : " + (finalMemory - initialMemory)/MB + " MB");
    }

}
