package Multithreading;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Seva Nardin
 */
public class Lesson12 {
    public static void main(String[] args) throws InterruptedException {
        Runer runer = new Runer();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    runer.firstThread();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                runer.secondThread();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        runer.finished();

    }
}

class Runer {
    private Account acc1 =  new Account();
    private Account acc2 =  new Account();

    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    private void takeLocks(Lock lock1, Lock lock2) throws InterruptedException {
        boolean firstLockTacken = false;
        boolean secondLockTacken = false;

        while (true) {
            try {
                firstLockTacken = lock1.tryLock();
                secondLockTacken = lock2.tryLock();
            } finally {
                if (firstLockTacken && secondLockTacken) {
                    return;
                }

                if (firstLockTacken) {
                    lock1.unlock();
                }

                if (secondLockTacken) {
                    lock2.unlock();
                }
            }
            Thread.sleep( 1 );
        }
    }

    public void firstThread() throws InterruptedException {
        Random random = new Random();
        for(int i=0; i<10000; i++) {
            takeLocks(lock1, lock2);
            // deadlock
//            lock1.lock();
//            lock2.lock();
            try {
            Account.transfer( acc1, acc2, random.nextInt(100) );
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void secondThread() {
        Random random = new Random();
        for(int i=0; i<10000; i++) {
            try {
                takeLocks(lock2, lock1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//            lock1.lock();
//            lock2.lock();

            // deadlock
//            lock2.lock();
//            lock1.lock();
            try {
                Account.transfer( acc2, acc1, random.nextInt(100) );
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void finished() {
        System.out.println(acc1.getBalance());
        System.out.println(acc2.getBalance());
        System.out.println("Total balance " + (acc1.getBalance() + acc2.getBalance()) );

    }
}

class Account {
    private int balance = 10000;

    public void deposit( int amount ) {
        balance += amount ;
    }

    public void withdraw( int amount ) {
        balance -= amount ;
    }

    public int getBalance() {
        return balance;
    }

    public static void transfer( Account acc1, Account acc2, int amount ) {
        acc1.withdraw( amount );
        acc2.deposit( amount );

    }
}
