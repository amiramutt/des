package cypher;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * File has been created by "zheka"
 * Date:  "28.09.2019"
 */

public class Transformations {
    public static String stringToBinary(String s) {
        String binary = "";
        for (int i = 0; i < s.length(); i++) {
            String x = "";
            int count = 0;
            int n = s.charAt(i);
            while (n > 0) {
                int a = n % 2;
                if (a == 1) {
                    count++;
                }
                x = a + "" + x;
                n = n / 2;
            }
            while (x.length() < 8)
                x = "0" + x;
            if (i == 0)
                binary += x;
            else
                binary += " " + x;
            x = "";
        }
        return binary;
    }

    public static String stringToBinaryOld(String s) {
        byte[] bytes = new byte[0];
        try {
            bytes = s.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
            binary.append(' ');
        }
        return String.valueOf(binary);
    }

    public static String binaryToString(String b) {
        String result = "";
        if (b == null)
            return "";
        b = b.replace(" ", "");

        System.out.println(b);
        System.out.println(b.length());

        for (int i = 0, k = 0; i < 65; i++, k++) {
            if (k == 7) {
                int num = Integer.parseInt(b.substring(i - 7, i+1), 2);
                result += "" + (char) num;
                k = -1;
                continue;
            }


        }

        return result;
    }

    public static String binaryToStringOld(String b) {
        String result = "";
        if (b == null)
            return "";
        b = b.replace(" ", "");
//        System.out.println(b);
//        result = Arrays.stream(b.split("(?<=\\G.{8})"))/* regex to split the bits array by 8*/
//                .parallel()
//                .map(eightBits -> (char) Integer.parseInt(eightBits, 2))
//                .collect(
//                        StringBuilder::new,
//                        StringBuilder::append,
//                        StringBuilder::append
//                ).toString();
        byte[] ret = new byte[b.length() / 8];
        for (int i = 0; i < ret.length; i++) {
            String chunk = b.substring(i * 8, i * 8 + 8);
            ret[i] = (byte) Short.parseShort(chunk, 2);
        }
        result = new String(ret, StandardCharsets.UTF_8);

        return result;
    }


    //Преобразует одномерный массив в двумерный
    public static int[][] oneToTwoDimensions(int[] oneDimArray, int rows, int columns) {
        int[][] twoDimArray = new int[rows][columns];

        int i = 0;
        for (int r = 0; r < twoDimArray.length; r++) {
            for (int c = 0; c < twoDimArray[r].length; c++) {
                twoDimArray[r][c] = oneDimArray[i];
                ++i;
            }
        }
        return twoDimArray;
    }


    //Преобразует двумерный массив в одномерный
    public static int[] twoToOneDimensions(int[][] twoDimArray) {
        int[] oneDimArray = new int[twoDimArray.length * twoDimArray[0].length];

        int i = 0;
        for (int r = 0; r < twoDimArray.length; r++) {
            for (int c = 0; c < twoDimArray[r].length; c++) {
                oneDimArray[i] = twoDimArray[r][c];
                ++i;
            }
        }
        return oneDimArray;
    }

    //преобразует массив в строку
    public static String arrayToString(int array[]) {
        String result = "";
        for (int i = 0; i < array.length; i++) {
            result += array[i] + "";
        }
        return result;
    }

}
