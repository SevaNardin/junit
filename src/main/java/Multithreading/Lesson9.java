package Multithreading;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Seva Nardin
 *
 * Класс CountDownLatch
 */
public class Lesson9 {
    public static void main(String[] args) throws InterruptedException {
        //отсчитаем три раза до тех пока защелка отопрется
        CountDownLatch countDownLatch = new CountDownLatch( 3 );
        // все потоки будут делить один объект - будут выполнять одновременно одно задание
        // три потока в пуле бутуд отсчитывать
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        // задание for 3 threads
        for(int i=0; i<3; i++) {
//            executorService.submit( new Processor(countDownLatch) );
            executorService.submit( new Processor(i, countDownLatch) );
        }
        // гасим потоки
        executorService.shutdown();
        // 4 поток будет ждать пока защелка откроется
//        countDownLatch.await();
//        System.out.println("Защелка открыта, главный поток запущен");
        // запуск потоков
        for(int i=0; i<3; i++) {
            Thread.sleep(1000);
            countDownLatch.countDown();
        }

    }
}

class Processor implements Runnable {
    private int id;
    private CountDownLatch countDownLatch;
    // конструктор
    public Processor(int id, CountDownLatch countDownLatch) {
        this.id = id;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep( 3000 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // отсчет
//        countDownLatch.countDown();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread with " + id + "processed");
    }
}
