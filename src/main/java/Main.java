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

        Key key = new Key("keykeke");
        DES des = new DES(key);
        des.encrypt("sample plain text");
        des.firstIp("0101100101001010111010100101000111010110100101110100101101011010");


    }

}
