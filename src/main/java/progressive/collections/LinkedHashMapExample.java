package progressive.collections;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Seva Nardin
 */
public class LinkedHashMapExample {

    // класс сохраняет порядок ввода значении при возврате map

    public static void main(String[] args) {
        Map<Integer, String> hashMap = new HashMap<Integer, String>();             // внутри нет порядка
        Map<Integer, String> linkedHashMap = new LinkedHashMap<>(); // гарантируется порядок, в каком добавили в таком они и вернутся
        Map<Integer, String> treeMap = new TreeMap<>();             // гарантирует сортировку пар по ключу

        testMap( hashMap );
        System.out.println( "hashMap" + "\n" );
        testMap( linkedHashMap );
        System.out.println( "linkedHashMap" + "\n" );
        testMap( treeMap );
        System.out.println( "treeMap" + "\n" );
    }

    // метод для тестирования
    public static void testMap( Map<Integer, String> map ) {
        map.put( 39, "Bob" );
        map.put( 12, "Mike" );
        map.put( 78, "Tom" );
        map.put( 0, "Tim" );
        map.put( 1500, "Lewis" );
        map.put( 7, "Bob" );

        for( Map.Entry<Integer, String> m : map.entrySet() ) {
            System.out.println(m.getKey() + " : " + m.getValue() );
        }
    }
}
