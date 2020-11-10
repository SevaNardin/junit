import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executor;
import java.util.logging.Logger;

/** Класс для интеграционных тестов */

public class TestIT {
    static Logger LOGGER;
    private Response response;


    @Test
    @DisplayName( "Пример теста" )
    void test() {
        int a = 2 ;
        int b = 4 ;
        int c = b / a ;
        // проверка что a = 2
        Assertions.assertEquals( 2, a , "Error: no correct value!");
    }

    @Test
    @DisplayName( "Запуск потоков" )
    void changeState() {
        try {

            Runnable task = () -> {
                System.out.println("start");

                response = RestAssured.get( "https://api.github.com/users/eugenp" );

                System.out.println("stop");
            };

//            Thread thread1 = new Thread(task);
//            Thread thread2 = new Thread(task);
//            thread1.start();
//            thread2.start();

//            ExecutorService ex = Executors.newFixedThreadPool(2);
//            ex.execute( task );

            Executor executor = (runnable) -> {
                new Thread(runnable).start();
            };
            executor.execute(task);
            executor.execute(task);

        } catch( Exception e  ) {
            Assertions.fail(e);
        }

    }

}
