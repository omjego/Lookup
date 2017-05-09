
/**
 * #CodeLikeMartian
 */

import java.io.*;
import java.util.*;

class TaskF {


    public static InputReader in = new InputReader( System.in );
    public static PrintWriter out = new PrintWriter( System.out );

    public static void main( String[] atgs ) {

        new TaskF( ).solve( );
        out.close( );
        System.exit( 0 );
    }

    private static final int MAXN = 5*(int)1e5 + 7;

    private int[][] trie = new int[MAXN][28];
    private int marker = 0;
    private ArrayList<String> ans;
    public boolean insert(String s, int unblocked) {

        int len = s.length();
        int[] data = new int[len];
        int current = 0;
        for (int i = 0; i < len; i++) {
            data[i] = s.charAt(i) - 97;
            if (trie[current][data[i]] != -1) {

            }
            else {
                trie[current][data[i]] = ++marker;
            }
            current = trie[current][data[i]];

            if (unblocked == 1)
                trie[current][26] = unblocked;
        }
        if (unblocked == 0) {
            if (trie[current][26] == 1) {
                return false;
            }
        }
        return true;
    }

    void trieDFS(int index, String s) {
        if (trie[index][26] == 0) {
            ans.add(s);
            return;
        }
        for (int i = 0; i < 26; i++) {
            if (trie[index][i] != -1 ) {
                trieDFS(trie[index][i], s + (char)(i + 97) );
            }
        }
        return;
    }

    public void solve() {

        for (int i = 0; i < MAXN; i++) {
            for (int j = 0; j < 26; j++) {
                trie[i][j] = -1;
            }
        }
        trie[0][26] = -1;
        int N = in.nextInt();
        ArrayList<String> blocked, unblocked;
        blocked = new ArrayList<>();
        unblocked = new ArrayList<>();
        ans = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int good = in.next().charAt(0) == '+' ? 1 : 0;
            String s = in.next();
            if (good == 1) {
                unblocked.add(s);
            }
            else{
                blocked.add(s);
            }
        }
        boolean possible = true;
        for (int i = 0; i < unblocked.size(); i++) {
             insert(unblocked.get(i), 1);
        }

        for (int i = 0; i < blocked.size(); i++) {
                possible = insert(blocked.get(i), 0);
                if (! possible) {
                    out.println("-1");
                    return;
                }
        }

        trieDFS(0, "");
        out.println(ans.size());

        ans.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        for (int i = 0; i < ans.size(); i++) {
            out.println(ans.get(i));
        }
    }


    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader( InputStream stream ) {
            reader = new BufferedReader( new InputStreamReader( stream ), 32768 );
            tokenizer = null;
        }

        public String next() {
            while ( tokenizer == null || !tokenizer.hasMoreTokens( ) ) {
                try {
                    tokenizer = new StringTokenizer( reader.readLine( ) );
                } catch ( IOException e ) {
                    throw new RuntimeException( e );
                }
            }
            return tokenizer.nextToken( );
        }

        public int nextInt() {
            return Integer.parseInt( next( ) );
        }

        public long nextLong() {
            return Long.parseLong( next( ) );
        }

        public double nextDouble() {
            return Double.parseDouble( next( ) );
        }

        public String nextLine() throws IOException {
            return reader.readLine( );
        }
    }


}

