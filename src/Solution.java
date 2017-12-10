/**
 * Created by OMKAR JADHAV on 10/31/2017.
 * Copy code, make changes and have fun.
 */
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        while ( line != null ) {
            line = line.trim();
            String str = line.substring( 2 ).trim();
            str = str.replace( " ", "" );
            String ans = "";
            if(line.charAt( 0 ) == 'E') {
                //If its email
                int atIndex = str.indexOf( '@' );
                ans = "E:" + str.charAt( 0 ) + "*****" + str.charAt( atIndex - 1 ) + str.substring( atIndex );
            } else {
                str = str.replace(")", "");
                str = str.replace("(", "");
                int digitCount = 0;
                int pos = -1;
                for(int i = str.length() - 1; i >= 0; --i) {
                    if ( Character.isDigit( str.charAt( i ) )) {
                        ++digitCount;
                    }
                    if ( digitCount == 4) {
                        pos = i;
                        break;
                    }
                }
                for ( int i = 0 ; i < str.length() ; i++ ) {
                    if ( Character.isDigit( str.charAt( i ) ) ) {
                        ans += (i < pos ? '*' : str.charAt( i ));
                    } else {
                        ans +=  (str.charAt( i ) != '+' ? "" : '+');
                    }
                }
                str = ans;
                ans = "";
                int starCount = 0;
                for(int i = str.length() - 1; i >= 0; --i) {
                    if ( str.charAt( i ) == '*' ) {
                        if ( starCount % 3 == 0  && i > 0) {
                            ans = '-' + ans;
                        }
                        ++starCount;
                    }
                    ans = str.charAt( i ) + ans;
                }
                ans = "P:" + ans;
            }
            System.out.println(ans);
            line = br.readLine();
        }
    }
}