package Multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Seva Nardin
 *
 * Thread pool
 * Для одновренного выполнения множества потоков
 *
 */
public class Lesson5 {
    public static void main(String[] args) throws InterruptedException {
        // 2 потока внутри - пул потоков- одновременный запуск потоков
        ExecutorService executorService = Executors.newFixedThreadPool( 2 );
        // выполним 5 заданий - 2 потоками
        for( int i = 0; i < 5; i++ ) {
            // передаем задния через submit
            executorService.submit( new Work(i) );
        }
        // прекращаем выполнение заданий
        executorService.shutdown();
        System.out.println("All tasks submitted");
        // ожидание выполнения заданий в потоках
        executorService.awaitTermination( 1, TimeUnit.DAYS );
    }
}

// блок который выполняет работу
class Work implements Runnable {
    private int id;

    public Work( int id ) { this.id = id; }

    @Override
    public void run() {
        try {
            Thread.sleep( 5000 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Work " + id + "was completed!" );

    }
}
