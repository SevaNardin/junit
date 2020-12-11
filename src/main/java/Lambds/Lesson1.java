package Lambds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Seva Nardin
 * Лямбды выражения
 * кусок кода передать в метод, меньше кода и удобнее
 */
interface Executable {
    int execute( int x );
}

class Runner {
    public void run( Executable c ) {
        int a  = c.execute( 10 );
        System.out.println( a );
    }
}

public class Lesson1 {

    public static void main(String[] args) throws InterruptedException {
        // заменили лямбдой new Runnable
//        Thread thread = new Thread( () -> System.out.println("Hello!") );
//        thread.start();
//        thread.join();

//        Runner runner = new Runner();
//        runner.run(new Executable() {
//            @Override
//            public int execute(int x) {
//                return x + 5;
//            }
//        });
//
//        runner.run( x -> x + 5 );

        int[] arr = new int[10];
        List<Integer> list = new ArrayList<>();

        fillArr(list);
        fillList(arr);

        System.out.println(list);
        System.out.println(Arrays.toString(arr));

        arr = Arrays.stream( arr ).map( a -> a*2 ).toArray();
        list = list.stream().map( a -> a*2 ).collect(Collectors.toList());

        System.out.println(list);
        System.out.println(Arrays.toString(arr));

    }

    private static void fillArr( List<Integer> list ) {
        for( int i=0; i<10; i++ ) {
            list.add(i +1);
        }

    }

    private static void fillList( int[] arr ) {
        for( int i=0; i<10; i++ ) {
            arr[i] = i +1;
        }
    }
}
