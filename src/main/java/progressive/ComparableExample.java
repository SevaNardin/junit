package progressive;

import java.util.*;

/**
 * @author Seva Nardin
 */
public class ComparableExample {
    public static void main(String[] args) {
        List<PersonOne> peopleList = new ArrayList<>();
        Set<PersonOne> peopleSet = new TreeSet<>();

        Collections.sort(peopleList);

        addElements(peopleList);
        addElements(peopleSet);

        System.out.println(peopleList);
        System.out.println(peopleSet);

    }

    private static void addElements( Collection collection ) {
        collection.add( new PersonOne( 1, "Katy") );
        collection.add( new PersonOne( 2, "Jack") );
        collection.add( new PersonOne( 3, "Many" ));

    }
}

class PersonOne implements Comparable<PersonOne> {
    private int id;
    private String name;

    public PersonOne(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonOne personOne = (PersonOne) o;
        return id == personOne.id && name.equals(personOne.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public int compareTo(PersonOne o) {
        return 0;
    }
}
