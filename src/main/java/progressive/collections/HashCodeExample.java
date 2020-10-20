package progressive.collections;

import java.util.*;

/**
 * @author Seva Nardin
 */
public class HashCodeExample {

    public static void main(String[] args) {
        Map<Person, String> map = new HashMap<>();
        Set<Person> set = new HashSet<>();

        Person person1 = new Person( 1, "Mike" );
        Person person2 = new Person( 1, "Mike" );

        map.put( person1, "123" );
        map.put( person2, "123" );

        set.add(person1);
        set.add(person2);

        System.out.println(map);
        System.out.println(set);

    }
}

//class progressive.collections.Person {
//    private int id;
//    private String name;
//
//    public progressive.collections.Person(int id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//
//    @Override
//    public String toString() {
//        return "progressive.collections.Person{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                '}';
//    }
//    // если не переопределить эти методы то будут сравниваться ссылки на объекты
//    // equals работает долго он идет по всем полям поэтому чтобы ускорить надо сначало вызывать hashcode, но hashcode не всегда выдает правильный ответ
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        progressive.collections.Person person = (progressive.collections.Person) o;
//        return id == person.id && Objects.equals(name, person.name);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name);
//    }
//}
