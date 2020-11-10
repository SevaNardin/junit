package Multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Seva Nardin
 */
public class Test {
    public static void main(String[] args) {
        Runnable task = () -> {
            System.out.println("Test");
        };

//        Thread thread1 = new Thread(task);
//        Thread thread2 = new Thread(task);
//        thread1.start();
//        thread2.start();

        ExecutorService ex = Executors.newFixedThreadPool(2);
        ex.execute( task );

        ex.shutdown();




    }

}
