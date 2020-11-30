package Multithreading;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Seva Nardin
 * Возвращение значений из потоков и выбрасывать исключения из поток
 */
public class Lesson14 {
    public static void main(String[] args) {

        // пул потоков
        ExecutorService executorService = Executors.newFixedThreadPool( 1);
//        executorService.submit(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        } {
        // если что то возвращаем то жаба понимает что это Callable
        // если нет, Runnable
        Future<Integer> future = executorService.submit( () -> {
                System.out.println("Start");
                try {
                    Thread.sleep( 3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Finished");

            Random random = new Random();

            int randomValue = random.nextInt();
            if( randomValue < 5 ) {
                throw new Exception( "Something exception" );
            }

            return random.nextInt( 10);
            } );

        executorService.shutdown();

        try {
            int result = future.get();  // get дожидается пока поток не отработает
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            Throwable ex = e.getCause();
            System.out.println(ex.getMessage());
        }
    }

    public static int calc() {
        return 4 + 5;
    }
}
