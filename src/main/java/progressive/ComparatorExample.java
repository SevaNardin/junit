package progressive;

import java.util.*;

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
        animals.add( "elethant" );
        animals.add( "frog" );
        // сортирвка списка элементов - естественный порядок
//        Collections.sort( animals );
        Collections.sort( animals, new StringLengthComparator() );
        System.out.println( animals );

        List<Integer> numbers = new ArrayList<>();
        numbers.add( 3 );
        numbers.add( 1 );
        numbers.add( 555 );
        numbers.add( 20 );
        numbers.add( 10 );
//        Collections.sort( numbers );
        Collections.sort( numbers, new BackwardsIntegerComparator() );
        System.out.println( numbers );

        List< Person > people = new ArrayList<>();
        people.add( new Person( 3, "Jack" ) );
        people.add( new Person(2, "Kirk" ) );
        people.add( new Person(1, "katy" ) );

        Collections.sort(people, new Comparator<Person>()  {
            @Override
            public int compare(Person o1, Person o2 ) {
                if( o1.getId() > o2.getId() ) {
                    return 1;
                } else if( o1.getId() < o2.getId() ) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        System.out.println(people);

    }
}
// для задания своего порядка сортировки - по длине строк
class StringLengthComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2 ) {
        if(o1.length() > o2.length() ) {
            return 1;
        } else if( o1.length() < o2.length() ) {
            return -1;
        } else {
            return 0;
        }
    }
}

class BackwardsIntegerComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2 ) {
        if(o1 > o2 ) {
            return 1;
        } else if( o1 < o2 ) {
            return -1;
        } else {
            return 0;
        }
    }
}

class Person {
    private int id;
    private String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
