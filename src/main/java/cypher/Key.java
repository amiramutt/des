package cypher;

/**
 * File has been created by "zheka"
 * Date:  "28.09.2019"
 */

public class Key {

    private String key;
    private String keyTransformed;
    private String binaryTransformed;
    private String binary;
    private String[] rounds;
    private String binaryWithoutCheckBits;

    public String getBinary() {
        return binary;
    }

    public String getKey() {
        return key;
    }

    public String[] getRounds() {
        return rounds;
    }

    public String getBinaryWithoutCheckBits() {
        return binaryWithoutCheckBits;
    }


    //    private String binaryTransformed;
    public Key(String key) {
        this.key = key;
        if (key.length() != 7) {
            System.err.println("The key should have 7 characters!!!");
            System.exit(1);
        }
        this.binaryWithoutCheckBits = Transformations.stringToBinary(key);
        this.rounds = generateRounds(binaryWithoutCheckBits);
        //Binary
        this.binaryTransformed = keyTransformation(this.binaryWithoutCheckBits);
        this.binary = this.binaryTransformed;
        this.keyTransformed = Transformations.binaryToString(binaryTransformed);
    }

    //Генерация ключей для раундов
    private String[] generateRounds(String binaryWithoutCheckBits) {
        //TODO(Закончить функцию генерации ключей для раундов)
        String[] rounds = new String[16];

        return rounds;
    }


    private String keyTransformation(String sevenBytes) {
        String result = sevenBytes.replace(" ", "");
        int counter = 0;
        for (int i = 0, k = 0; i < 64; i++, k++) {
            if (k == 7) {
                if (counter % 2 == 0) {
                    if (i == 63)
                        result = result.substring(0, i) + "1";
                    else {
                        result = result.substring(0, i) + "1" + result.substring(i);
                    }
                } else {
                    if (i == 63)
                        result = result.substring(0, i) + "0";
                    else
                        result = result.substring(0, i) + "0" + result.substring(i);
                }
                k = -1;
                counter = 0;
                continue;
            }
            if (result.charAt(i) == '1')
                counter++;


        }

        for (int i = 0, k = 0; i < 72; i++, k++) {
            if (k == 8) {
                result = result.substring(0, i) + " " + result.substring(i);
                k = -1;
            }
        }
        return result;
    }


}
