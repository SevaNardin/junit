package Regexp;

import java.util.Arrays;

/**
 * @author Seva Nardin
 *
 * регулярные выражения 1 часть
 */

public class Reg1 {
    public static void main(String[] args) {

        /*
        \\d - одна цифра
        \\w - одна буква
         */


        String str = "1";
        System.out.println(str.matches("\\d{2}"));

        String a = "Hello there hey";
        String[] word = a.split(" ");
        System.out.println(Arrays.toString( word ) );

        String b = "Hello world";
        System.out.println(b.replace("world", "Seva"));
    }

}
