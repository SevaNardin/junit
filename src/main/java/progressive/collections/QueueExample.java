package progressive.collections;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author Seva Nardin
 *
 * Интрефейс Queue
 *
 */
public class QueueExample {
    public static void main(String[] args) {
        // Обращаемся к классу Person
        // создаем новые объекты класса
        Person person1 = new Person( 1 );
        Person person2 = new Person( 2 );
        Person person3 = new Person( 3 );
        Person person4 = new Person( 4 );

        // добавим в очередь не по порядку
        Queue<Person> personQueue = new LinkedList<>();
        personQueue.add( person4 );
        personQueue.add( person2 );
        personQueue.add( person3 );
        personQueue.add( person1 );
        // вывод -  в какой последовательности добавили в такой и выводится
        System.out.println(personQueue);
        // достаем элемент из очереди - будет всегда первый элемент / достали из очереди и удалили, останется 3
        System.out.println( personQueue.remove());
        // достаем первый эелемент но не удаляем элемент
        System.out.println( personQueue.peek());
        System.out.println( personQueue );


        // создаем очередь определнного размера
        Queue<Person> personQueue2 = new ArrayBlockingQueue<>(10);


    }

}
