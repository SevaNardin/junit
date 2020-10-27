package Multithreading;

import java.util.Scanner;

/**
 * @author Seva Nardin
 * одна переменная делится между несколькими потоками - синхронизация
 * ключевое слово volatile
 */
public class Lesson2 {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        // запуск потока
        myThread.start();

        // выключаем поток
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        // нажимаем Enter
        myThread.shutdown();

        // может не всегда работать нормально из-за когерентности кэшей
        // поэтому используем volatile - переменная может быть изменена - гарнтирует когерентность кэшей
        // у разных потоков одно тоже значение переменной


    }
}

class MyThread extends Thread {
    // выключатель потока
    // volatile нужен когда один пишет в переменную а другие потоки ее читают
    private volatile boolean running = true;

    public void run() {
        while(running) {
            System.out.println("Hello");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        this.running = false;
    }
}


