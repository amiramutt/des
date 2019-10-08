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
        int nshift;
        char temp1, temp2 = 0;
        String[] rounds = new String[16];
        char key[][] = new char[16][48];

        int[] pbox = Transformations.twoToOneDimensions(Constants.P_BOX_KEY_C0_D0);

        char[] key1 = new char[56];

        //string to char array
        char[] parts = binaryWithoutCheckBits.replace(" ", "").toCharArray();

        char[] stringToCharArrayLEFT = new char[28];
        char[] stringToCharArrayRIGHT = new char[28];

        for (int i = 0; i < 56; i++)
            key1[i] = parts[pbox[i] - 1];

        for (int i = 0; i < 28; i++)
            stringToCharArrayLEFT[i] = key1[i];
        for (int i = 0; i < 28; i++)
            stringToCharArrayRIGHT[i] = key1[i + 28];

        int key2[] = Transformations.twoToOneDimensions(Constants.P_BOX_KEY_each28to24_BITS);

        for (int i = 0; i < 16; i++) {
            if (i == 0 || i == 1 || i == 8 || i == 15)  //условия сдвига на 1 или 2 цифры
                nshift = 1;
            else
                nshift = 2;
            if (nshift == 1) {

                temp1 = stringToCharArrayLEFT[0];
                temp2 = stringToCharArrayRIGHT[0];
                for (int j = 0; j < 27; j++) {
                    stringToCharArrayLEFT[j] = stringToCharArrayLEFT[j + 1];
                    stringToCharArrayRIGHT[j] = stringToCharArrayRIGHT[j + 1];
                }
                stringToCharArrayLEFT[27] = temp1;
                stringToCharArrayRIGHT[27] = temp2;

            } else {
                for (int io = 0; io < 2; io++) {
                    temp1 = stringToCharArrayLEFT[0];
                    temp2 = stringToCharArrayRIGHT[0];
                    for (int j = 0; j < 27; j++) {
                        stringToCharArrayLEFT[j] = stringToCharArrayLEFT[j + 1];
                        stringToCharArrayRIGHT[j] = stringToCharArrayRIGHT[j + 1];
                    }
                    stringToCharArrayLEFT[27] = temp1;
                    stringToCharArrayRIGHT[27] = temp2;
                }

            }

            for (int j = 0; j < 24; j++)
                key[i][j] = stringToCharArrayLEFT[key2[j] - 1];
            for (int j = 24; j < 48; j++)
                key[i][j] = stringToCharArrayRIGHT[key2[j] - 1 - 28];

        }

        for (int i = 0; i < 16; i++) {
            rounds[i] = String.valueOf(key[i]);
        }

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
