import cypher.DES;
import cypher.Key;
import cypher.Transformations;

import javax.swing.text.TabableView;
import java.io.UnsupportedEncodingException;

/**
 * File has been created by "zheka"
 * Date:  "26.09.2019"
 */

public class Main {

    public static void main(String[] args) {

        Key key = new Key("abcdefg");
        for (int i = 0; i < key.getRounds().length; i++) {
            System.out.println(key.getRounds()[i]);
        }
        DES des = new DES(key);
        des.encrypt("sample plain text");


    }

}
