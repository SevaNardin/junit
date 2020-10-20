package progressive.collections;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Seva Nardin
 */
public class HashMapExample {

    public static void main(String[] args) {
        // 1 -> [....]
        // 2 -> [....]
        // Пара -> ключ значение
        Map<Integer, String> map = new HashMap<>();

        map.put( 1, "One");
        map.put( 2, "Two");
        map.put( 3, "Three");

        System.out.println( map );
        // переписывает 3 значение
        map.put(3, "Four");

        System.out.println( map );
        // получаем значение
        System.out.println( map.get(1) );
        System.out.println( map.get(10) );

        // прохождение по мапе с помощью forEach - entrySet() возвращаете все объекты map
        // класс hashmap не гаранитрует никого порядка
        for( Map.Entry<Integer, String> entry : map.entrySet()  ) {
            System.out.println( entry.getKey() + " -> " + entry.getValue() );
        }



    }



}
