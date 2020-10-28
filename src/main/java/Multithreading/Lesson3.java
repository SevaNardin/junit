package Multithreading;

/**
 * @author Seva Nardin
 */
public class Lesson3 {
    private int counter;

    public static void main(String[] args) throws InterruptedException {
        Lesson3 lesson3 = new Lesson3();
        lesson3.doWork();
    }
    // синхронизация потоков/ у всех объектов есть монитор, завладеть монитором может только один поток
    // всегда будет 20000 / поток2 ждет пока не отработает поток 1 и  так далее
//    public synchronized void increment() {
//        counter++;
//    }
    // тоже самое
    public void increment() {
        // синхронизированный блок
        synchronized( this ) {
            counter++;
        }
    }

    public void doWork() throws InterruptedException {
        // поток 1
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for( int i = 0; i < 10000; i++ ) {
                    increment();
                    //counter++;
                }
            }
        });

        // поток 2
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for( int i = 0; i < 10000; i++ ) {
                    increment();
                    //counter++;
                    // аналоги
                    //counter = counter + 1;
                    //counter += 1;
                }
            }
        });

        // запуск 2х потоков
        // инкримитируем counter и каждый раз разные значения
        thread1.start();
        thread2.start();
        // ждем пока отработают 2 потока (один поток ждет др поток пока отработает)
        thread1.join();
        thread2.join();

        System.out.println(counter);

    }

}
