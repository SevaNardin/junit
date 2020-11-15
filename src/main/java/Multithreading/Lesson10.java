package Multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Seva Nardin
 *
 * Класс ReentrantLock
 */
public class Lesson10 {
    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                task.firstThread();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                task.secondThread();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        task.showCounter();
        // результат будет разный так как потоки пишут в одну переменную
        // для этого используют ReentrantLock
        //  тепреь всегда будет 2000

    }
}
// класс с 2 заданиями
class Task {
    private int counter;
    private Lock lock = new ReentrantLock();

    private void increment() {
        for(int i=0; i<1000; i++) {
            counter++;
        }

    }
    // 1 thread
    public void firstThread() {
        // lock работает как synchronyze
        lock.lock();
        increment();
        lock.unlock();
    }
    // 2 thread
    public void secondThread() {
        // будет ждать пока первый поток не вызовет unlock
        lock.lock();
        increment();
        lock.unlock();
    }

    public void showCounter() {
        System.out.println(counter);
    }

}
