package proyectos_gradle;

import io.restassured.RestAssured;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HybridTest {

    private WebDriver driver;

    @BeforeEach
    void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Para que corra rápido en segundo plano
        driver = new ChromeDriver(options);
    }

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

        // 2. UI: Vamos a Wikipedia directamente con el término de búsqueda
        // Usamos el nombre para buscar un artículo (aunque no exista, validaremos el texto)
        driver.get("https://en.wikipedia.org/wiki/" + nameFromApi.replace(" ", "_"));

        // 3. Verificación
        // Wikipedia mostrará el nombre en el título principal (id='firstHeading') 
        // o en el texto de "búsqueda" si el artículo no existe.
        String bodyText = driver.findElement(By.tagName("body")).getText();
        
        boolean isPresent = bodyText.contains(nameFromApi);
        
        if(!isPresent) {
            System.out.println("DEBUG: El navegador está en: " + driver.getCurrentUrl());
        }

        assertTrue(isPresent, "El nombre de la API '" + nameFromApi + "' no se encontró en la página.");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}