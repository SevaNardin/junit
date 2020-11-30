package Multithreading;

import java.util.Random;

/**
 * @author Seva Nardin
 *
 * Прерывание потоков
 *
 */
public class Lesson13 {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Start");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                for(int i=0 ; i<1_000_000; i++) {
                    // проверка что хотят остановить поток
                    if( Thread.currentThread().isInterrupted() ) {
                        System.out.println("Thread was interrupted");
                        break;
                    }
                    Math.sin(random.nextDouble());
                }
            }
        });

        System.out.println("Start Thread");

        thread.start();
        Thread.sleep(1000);
        // прерывание потока // не останавливает поток, но дает сообщение остановить
        thread.interrupt();

        thread.join();


        System.out.println("Has finished");
    }




}
