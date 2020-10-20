package progressive.collections;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Seva Nardin
 */
public class SetExample {
    public static void main(String[] args) {
        // Set хранит в себе просто объекты - множество
        Set<String> hashSet = new HashSet<>();
        Set<String> linkedHashSet = new LinkedHashSet<>();
        Set<String> treeSet = new TreeSet<>();

        hashSet.add( "Mike" );
        hashSet.add( "Tom" );
        hashSet.add( "Alice" );
        hashSet.add( "Elvis" );
        hashSet.add( "Jack" );
        hashSet.add( "Tom" );
        hashSet.add( "Tom" );

        for ( String s : hashSet ) {
            System.out.println( s );
        }
        // В Set нет дубликатов , Том будет в одном экземпл-е
        // contains in hashSet работает быстро
        System.out.println( hashSet.contains( "Mike" ) );
        System.out.println( hashSet.contains( "Tim" ));

        System.out.println( "hashSet" + "\n" );

        linkedHashSet.add( "Mike" );
        linkedHashSet.add( "Tom" );
        linkedHashSet.add( "Alice" );
        linkedHashSet.add( "Elvis" );
        linkedHashSet.add( "Jack" );

        for ( String s : linkedHashSet ) {
            System.out.println( s );
        }

        System.out.println( "\n" );

        treeSet.add( "Mike" );
        treeSet.add( "Tom" );
        treeSet.add( "Alice" );
        treeSet.add( "Elvis" );
        treeSet.add( "Jack" );

        for ( String s : linkedHashSet ) {
            System.out.println( s );
        }
    }

}
