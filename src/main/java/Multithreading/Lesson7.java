package Multithreading;

import java.util.Scanner;

/**
 * @author Seva Nardin
 *
 * wait() and notifay()
 * для синхронизации потоков
 */
public class Lesson7 {
    public static void main(String[] args) throws InterruptedException {
        WaitAndNotifay waitAndNotifay = new WaitAndNotifay();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    waitAndNotifay.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    waitAndNotifay.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

    }
}

class WaitAndNotifay {
    public void produce() throws InterruptedException {
        synchronized ( this ) {
            System.out.println("Produce thread started");
            // потоки ждут
            wait();
            System.out.println("Producer thread resumed");
        }

    }

    public void consume() throws InterruptedException {
        Thread.sleep( 2000 );

        Scanner scanner = new Scanner(System.in);
        // для продолжения программы полсе нажатия Enter
        synchronized ( this ) {
            System.out.println("Waiting for return key pressed");
            scanner.nextLine();
            // чтобы все потоки которые ждут проснулись
            notify();
        }

    }
}
