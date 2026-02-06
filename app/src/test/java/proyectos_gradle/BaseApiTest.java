package proyectos_gradle;

import io.restassured.RestAssured;
import io.qameta.allure.restassured.AllureRestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseApiTest {

    @BeforeAll
    static void setupApi() {
        // URL base centralizada
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        
        // Este filtro hace que TODAS las peticiones aparezcan con lujo de detalle en Allure
        // sin tener que escribirlo en cada test individualmente.
        RestAssured.filters(new AllureRestAssured());
    }
}