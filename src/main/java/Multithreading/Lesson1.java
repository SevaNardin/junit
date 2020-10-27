package Multithreading;

/**
 * @author Seva Nardin
 * Многопоточность
 */
public class Lesson1 {
    public static void main(String[] args) throws InterruptedException {
        // потоки не синхронизированы
//        MyThread myThread = new MyThread();
//        // создаем новый поток
//        myThread.start();
//
//        // второй поток
//        MyThread myThread1 = new MyThread();
//        myThread1.start();
//
//        System.out.println("Hello from main thread");
        // поток засыпает
//        Thread.sleep( 2000 );

        Thread thread = new Thread( new Runner() );
        thread.start();

    }
}

class Runner implements Runnable {
    @Override
    public void run() {
        for(int i=0 ; i<1000; i++) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.getMessage();
            }
            System.out.println("Hello from MyThread!" + i );
        }
    }
}

//class MyThread extends Thread {
//    public void run() {
//        for(int i=0 ; i<1000; i++) {
//            try {
//                Thread.sleep(1000);
//            } catch (Exception e) {
//                e.getMessage();
//            }
//            System.out.println("Hello from MyThread!" + i );
//        }
//    }
//}
