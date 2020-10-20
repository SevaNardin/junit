package progressive.collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Seva Nardin
 */
public class LinkedListExample {

    public static void main(String[] args) {
        List<Integer> linkedList = new LinkedList<>();
        // есть такие же методы как и у Arraylist
        List<Integer> arrayList = new ArrayList<>();

        messureTime( linkedList );
        messureTime( arrayList );
    }

    // метод измерения работы методов LinkedList и ArrayList
    public static void messureTime( List<Integer> list ) {

        // add и get быстрее у ArrayList
//        for( int i = 0 ; i < 100000 ; i++ ) {
//            list.add(i);
//        }

        Long start = System.currentTimeMillis();

        for( int i = 0 ; i < 100000 ; i++ ) {
            list.add( 0, i);
        }

//        for( int i = 0 ; i < 100000 ; i++  ) {
//            list.get(i);
//        }

        Long end = System.currentTimeMillis();

        System.out.println( end - start );

        // методы remove и add( ind, i ) работе быстрее чем у Arraylist

    }

}
