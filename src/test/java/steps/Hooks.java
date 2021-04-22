package steps;

import io.cucumber.java.Before;

import static com.codeborne.selenide.Selenide.open;

/**
 * @author Seva Nardin
 * Cucumber test
 * шаг Открытие страницы
 */
public class Hooks {

    @Before
    public void openUrl() {
        open("https://grinfer.com/home");
    }

}
