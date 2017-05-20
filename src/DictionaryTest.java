import com.omjego.dictionary.Dictionary;
import com.omjego.structure.trie.Trie;
import com.omjego.structure.trie.TrieNode;

/**
 * Created by OMKAR JADHAV on 5/9/2017.
 */
public class DictionaryTest {

    public static  void main(String []args) {

        Dictionary dict = new Dictionary();

        System.out.println(dict.search("Abol"));
        //System.err.println(Runtime.getRuntime().freeMemory());
    }
}
