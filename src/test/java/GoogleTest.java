import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

/**
 * @author Seva Nardin
 *
 * UI тесты
 */

public class GoogleTest {

    @BeforeEach
    void init() {
        // Конфигурация Selenide
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;              // Запуск браузера заблокирована, скрытый режим
        Configuration.startMaximized = false;
        Configuration.holdBrowserOpen = false;
    }

    @Test
    @DisplayName( "Open google (UI Test)" )
    void openGoogle() throws InterruptedException {
        // открытие страницы
        Selenide.open("http:google.com");
        // ищем поле Поиск , пишем текст и нажимаем Enter
        Selenide.$( By.name("q")).setValue("test").pressEnter();
        // ждем результата
        Thread.sleep(3000);
        // проверка тайтла страницы
        Assertions.assertTrue( Selenide.title().contains("test - Поиск в Google") );
    }

}
