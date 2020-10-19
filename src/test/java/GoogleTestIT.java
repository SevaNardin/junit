import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

/**
 * @author Seva Nardin
 */

public class GoogleTestIT {

    @Test
    @DisplayName( "Open google (UI Test)" )
    void openGoogle() {
        open("http:google.com");
    }

}
