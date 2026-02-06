package proyectos_gradle;

import org.openqa.selenium.By;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Al usar "extends BaseTest", esta clase hereda el driver, el setup y el tearDown automáticamente
public class HybridTest extends BaseTest {

    @Test
    @DisplayName("Integración: Obtener usuario por API y verificarlo en Wikipedia")
    void testApiToUi() {
        // 1. API: Obtenemos el nombre (Leanne Graham)
        String nameFromApi = 
            given()
                .baseUri("https://jsonplaceholder.typicode.com")
            .when()
                .get("/users/1")
            .then()
                .statusCode(200)
                .extract()
                .path("name");

        System.out.println("DEBUG: Dato de API -> " + nameFromApi);

        // 2. UI: El 'driver' ya viene inicializado desde la clase padre (BaseTest)
        driver.get("https://en.wikipedia.org/wiki/" + nameFromApi.replace(" ", "_"));

        // 3. Verificación
        String bodyText = driver.findElement(By.tagName("body")).getText();
        boolean isPresent = bodyText.contains(nameFromApi);
        
        if(!isPresent) {
            System.out.println("DEBUG: El navegador está en: " + driver.getCurrentUrl());
        }

        assertTrue(isPresent, "El nombre de la API '" + nameFromApi + "' no se encontró en la página.");
    }
}