package Multithreading;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author Seva Nardin
 * Паттерн producer - consumer
 *
 */
public class Lesson6 {
    private static ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);

    public static void main(String[] args) throws InterruptedException {
        // 2 потока
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // закидываем элементы в очередь - создает
                    produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                // получаем элементы очереди потрибитель
                try {
                    consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        // стартуем наши потоки
        thread1.start();
        thread2.start();
        // ждем пока выполняться потоки
        thread1.join();
        thread2.join();

    }

    private static void produce() throws InterruptedException {
        Random random = new Random();

        while(true) {
            queue.put( random.nextInt(100) );
        }
    }

    private static void consumer() throws InterruptedException {
        Random random = new Random();

        while(true) {
            Thread.sleep(100);
            if(random.nextInt(10) == 5) {
                queue.take();

                System.out.println(queue.take());
                System.out.println("Queue size is " + queue.size());
            }
        }
    }
}
