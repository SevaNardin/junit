import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** Класс для тестов */
public class TestIT {

    @Test
    @DisplayName( "Пример теста" )
    void test() {
        int a = 2 ;
        int b = 4 ;
        int c = b / a ;
        // проверка что a = 2
        Assertions.assertEquals( 2, a , "Error: no correct value!");
    }

}
