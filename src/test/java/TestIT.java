import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

/** Класс для интеграционных тестов */

public class TestIT {
    // поле в области памяти
    int a ;
    int b ;
    String str = "Test" ;

    @BeforeEach
    void init() {
        a = 2;
        b = 4;
    }

    @AfterEach
    void finish() {
        System.out.println( "Test Passed" + str );
    }

    @Test
    @DisplayName( "Теста 01" )
    void test() {
        int c = b / a ;
        // проверка что a = 2
        Assertions.assertEquals( 2, c , "Error: no correct value!");
    }

    @Test
    @DisplayName( "Теста 02" )
    void test2() {
        int c = b / a ;
        // проверка что a = 2
        Assertions.assertEquals( 2, c , "Error: no correct value!");
    }








    @ParameterizedTest( name = "#{index} - Run test with args={0}" )
    @ValueSource( ints = { 1, 2, 3 } )
    void test01( int num ) {
        // передаваемое значение
        System.out.println("Значение: " + num );
        // объявляем итговое значение
        int total = 1 + num ;

        System.out.println("Итоговая сумма: " + total );
        // проверяем что итоговое значение больше 2
        assertTrue( total > 2 , "Errore: total than less 2!");
    }

    @ParameterizedTest(name = "#{index} - Run test with args={0}")
    @ValueSource(ints = {1, 2, 3})
    void test_int_arrays_custom_name(int arg) {
        assertTrue(arg > 0);
    }


}
