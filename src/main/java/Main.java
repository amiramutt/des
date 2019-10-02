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

    public static void main(String[] args) throws UnsupportedEncodingException {
        Key key = new Key("keyke ke");
        DES des = new DES(key);
        des.encrypt("texthelloworldhellyeah");


    }

}
