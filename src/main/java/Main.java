import cypher.DES;
import cypher.Key;

/**
 * File has been created by "zheka"
 * Date:  "26.09.2019"
 */

public class Main {

    public static void main(String[] args) {

        Key key = new Key("somekey");
        DES des = new DES(key);
        des.encrypt("sample plain text");


    }

}
