import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Date;

import static org.hamcrest.Matchers.is;
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
    @ValueSource( ints = { 4, 2, 3 } )
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

    @Test
    @DisplayName( "API testing")
    public void postmanFirstGetTest(){
        RestAssured.
                when().get("https://postman-echo.com/get?foo1=bar1&foo2=bar2").
                then().assertThat().statusCode(200).
                and().body("args.foo2", is("bar2"));
    }

    @Test
    public void postRequestExampleTest() {
        String someRandomString = String.format("%1$TH%1$TM%1$TS", new Date());
        JSONObject requestBody = new JSONObject();
        requestBody.put("FirstName", someRandomString);
        requestBody.put("LastName", someRandomString);
        requestBody.put("UserName", someRandomString);
        requestBody.put("Password", someRandomString);
        requestBody.put("Email", someRandomString + "@gmail.com");
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());
        Response response = request.post("https://webhook.site/a18a23cb-e9a0-4f03-a7fa-cdfcfa76ca98");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        System.out.println("The status code recieved: " + statusCode);
    }

}
