package Multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author Seva Nardin
 *
 * Semaphore используются когда есть какой то ресурс Сервер например
 * и его испольуют несколько потоков и мы хотим ограничить доступ к этому ресурсу
 */
public class Lesson11 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(200);

        Connection connection = Connection.getConnection() ;

        for( int i=0; i<200; i++ ) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        connection.work();
                    } catch( InterruptedException e ) {
                        e.printStackTrace();
                    }
                }
            });

            executorService.shutdown();
            executorService.awaitTermination(1, TimeUnit.DAYS) ;

        }

        // кол-во разрешений для потоков
//        Semaphore semaphore = new Semaphore( 3 );

//        // потоки начинают взаимоде-ть с ресурсом
//        try {
//            semaphore.acquire();
//            semaphore.acquire();
//            semaphore.acquire();
//
//            System.out.println( "All permits have been acquire" );
//            // это не запустится так как все разрешения 3 были сипользованы
//            semaphore.acquire();
//
//            System.out.println("Can't reach here....");
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        // отдает рарешение на ресурс другому потоку
//        semaphore.release();
//        // выводит кол-во разрешений
//        System.out.println( semaphore.availablePermits() );

    }
}

// типа соединение с сервером
class Connection {
    private static Connection connection = new Connection();
    private int connectionCount;
    // 10 разрешений
    private Semaphore semaphore = new Semaphore( 10);

    // запрещаем создавать объекты этого класса Singleton
    private Connection() {
    }

    public static Connection getConnection() {
        return connection;
    }

    public void work() throws InterruptedException {
        semaphore.acquire();
        try {
            doWork();
        } finally {
            semaphore.release();
        }

    }

    private void doWork() throws InterruptedException {
        synchronized (this) {
            connectionCount++;
            System.out.println( connectionCount );
        }
        Thread.sleep(5000);

        synchronized (this) {
            connectionCount--;
        }

    }
}
