import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Seva Nardin
 *
 * UI тесты
 */

public class GoogleTestIT {

    @Test
//    @Disabled("В CI проблема запуска Selenide надо копать")
    @DisplayName( "Open google (UI Test)" )
    void openGoogle() {
        // открытие страницы
        open("http:google.com");
        // ожидание
        sleep(3000);
        // получаем текущий URL
        String currentURL = url();
        // проверка страницы
        assertEquals( "https://www.google.com/?gws_rd=ssl", currentURL, "Error: No correct URl!" );
    }

}
