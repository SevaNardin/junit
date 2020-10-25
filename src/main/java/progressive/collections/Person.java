package progressive.collections;

import java.util.Objects;

/**
 * @author Seva Nardin
 */
public class Person {
    private int id;
    private String name;
    // кнтрукторы класса
    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Person(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    // если не переопределить эти методы то будут сравниваться ссылки на объекты
    // equals работает долго он идет по всем полям поэтому чтобы ускорить надо сначало вызывать hashcode, но hashcode не всегда выдает правильный ответ
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && Objects.equals(name, person.name);
    }
    // сравнивать быстрее целые числа
    // колизия когда два хэша равны но объекты разные (поля и значения)
    // объект преобразовываем в число object -> int
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + ( name != null ? name.hashCode() : 0  );
        return result;
    }

    /**
     * Контракт hashcode() и equals()
     * 1) Если хэши у объектов разные то и объекты точно разные
     * 2) Если хэши одинаковые не факт что объекты одинаковые и надо вызывать equals() выдаст точный объект
     *
     * */

}
