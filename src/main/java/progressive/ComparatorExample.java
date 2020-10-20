package progressive;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Seva Nardin
 *
 * Сортировка объектов
 */
public class ComparatorExample {
    public static void main(String[] args) {
        List<String> animals = new ArrayList<>();

        animals.add( "doc" );
        animals.add( "cat" );
        animals.add( "frog" );
        animals.add( "bird" );
        // сортирвка списка элементов
        Collections.sort( animals );
        System.out.println( animals );



    }
}
