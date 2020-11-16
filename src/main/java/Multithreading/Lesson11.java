package Multithreading;

import java.util.concurrent.Semaphore;

/**
 * @author Seva Nardin
 *
 * Semaphore используются когда есть какой то ресурс Сервер например
 * и его испольуют несколько потоков и мы хотим ограничить доступ к этому ресурсу
 */
public class Lesson11 {
    public static void main(String[] args) {
        // кол-во разрешений для потоков
        Semaphore semaphore = new Semaphore( 3 );

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

    // запрещаем создавать объекты этого класса Singleton
    private Connection() {
    }

    public static Connection getConnection() {
        return connection;
    }

    public void doWork() throws InterruptedException {
        synchronized (this) {
            connectionCount++;
        }
        Thread.sleep(3000);

        synchronized (this) {
            connectionCount--;
        }

    }
}
