import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

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
        Configuration.headless = true;              // Запуск браузера заблокирован, скрытый режим
        Configuration.startMaximized = false;
        Configuration.holdBrowserOpen = false;
    }

    @Test
    @DisplayName( "Open google (UI Test)" )
    void openGoogle() throws InterruptedException {
        // открытие страницы
        Selenide.open("https://yandex.ru/");
        // ищем поле Поиск , пишем текст и нажимаем Enter
        if( Selenide.$( By.xpath("//*[@id='text']")).isDisplayed()  ) {
            System.out.println( "Элемент найден!" );
            Selenide.$( By.xpath("//*[@id='text']")).setValue("test").pressEnter();
        }
        // ждем результата
        Thread.sleep(3000);
        // проверка тайтла страницы
        Assertions.assertTrue( Selenide.title().contains("Яндекс: нашлось") );
    }

}
