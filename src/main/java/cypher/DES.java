package cypher;

/**
 * File has been created by "zheka"
 * Date:  "29.09.2019"
 */

public class DES {

    private Key key;

    public DES(Key key) {
        this.key = key;
    }

    public String encrypt(String text) {
        String result = "";

        //Удлиняет текст до кратности в 64 бита
        text = lengthen(text);

        //Отправляет каждые 64 бита в раунды с последующей конкатенацией в результат
        for (int i = 0; i < text.length(); i += 8)
            result += encryptEightBytes(Transformations.stringToBinary(text.substring(i, i + 8)).replace(" ", ""));
        return result;

    }

    //Удлиняет текст до кратности в 64 бита
    private String lengthen(String text) {
        String result;
        result = text;
        while (result.length() % 8 != 0)
            result += " ";

        return result;
    }

    private String encryptEightBytes(String b) {
        String result = "";
        result = firstIp(b);
        for (int i = 1; i <= 16; i++) {
            result = round(result, i);
        }

        result = lastIp(result);
        return result;
    }

    private String round(String b, int roundNumber) {
        String result;
        result = "";
        String left = b.substring(0, b.length() / 2);
        String right = b.substring(b.length() / 2);

        //P-box расширения с 32 до 48 битов
        String rightFunc = pBox(right);
        //XOR с ключом
        rightFunc = xorWithKey(rightFunc, key.getRounds()[roundNumber - 1]);
        //S-Box'ы
        rightFunc = sBox(rightFunc);
        //Прямой P-Box
        rightFunc = straightPBox(rightFunc);

        left = xorWithLeft(rightFunc, left);
        if (roundNumber == 16)
            result = left + right;
        else
            result = right + left;

        return result;
    }

    //Начальная IP перестановка
    public String firstIp(String b) {
        String result = "";
        int firstIp[] = Transformations.twoToOneDimensions(Constants.FIRST_IP_PERMUTATION);

        for (int i = 0; i < firstIp.length; i++)
            result += b.charAt(firstIp[i] - 1);

        return result;

    }

    //Конечная IP перестановка
    public String lastIp(String b) {
        //TODO(конечная IP перестановка)
        String result = "";

        int lastIp[] = Transformations.twoToOneDimensions(Constants.LAST_IP_PERMUTATION);

        for (int i = 0; i < lastIp.length; i++)
            result += b.charAt(lastIp[i] - 1);

        return result;

    }

    //P-box расширения с 32 до 48 битов
    private String pBox(String right) {

        //TODO(P-Box расширения)
        String result = "";

        return result;
    }

    //xor with key
    private String xorWithKey(String rightFunc, String key) {
        //TODO(xor с ключом)

        String result = "";

        return result;
    }

    //Набор с-боксов
    private String sBox(String rightFunc) {
        //TODO(S-Box'ы)

        String result = "";

        return result;
    }

    //прямой пи-бокс
    private String straightPBox(String rightFunc) {
        //TODO(Прямой p-box)

        String result = "";

        return result;
    }


    //xor с левой частью
    private String xorWithLeft(String rightFunc, String left) {
        //TODO(xor с левой частью)

        String result = "";

        return result;
    }



  /*  public String decrypt(String text){
        String result = "";

        return result;
    }*/

    public Key getKey() {
        return key;
    }
}
