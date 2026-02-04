package proyectos_gradle;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AppTest {
    private WebDriver driver;

    @BeforeEach
    void setup() {
        // Esto se ejecuta ANTES de cada test
        driver = new ChromeDriver();
    }

    @Test
    @DisplayName("Login exitoso en Herokuapp")
    void testLoginExitoso() {
        // Navegamos a la sección de Login
        driver.get("https://the-internet.herokuapp.com/login");

        // Instanciamos nuestra página
        LoginPage loginPage = new LoginPage(driver);

        // Usamos los métodos (El test ahora es fácil de leer)
        loginPage.escribirUsuario("tomsmith");
        loginPage.escribirPassword("SuperSecretPassword!");
        loginPage.clickLogin();

        // Verificación
        String mensaje = loginPage.obtenerMensaje();
        assertTrue(mensaje.contains("You logged into a secure area!"), 
                "El mensaje de éxito no es el esperado");
    }

    @Test
    @DisplayName("Manejo de carga dinámica con esperas explícitas")
    void testDynamicLoading() {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");

        DynamicLoadingPage dynamicPage = new DynamicLoadingPage(driver);
        
        dynamicPage.clickStart();
        
        // El test se pausará inteligentemente aquí hasta que aparezca el texto
        String resultado = dynamicPage.getLoadedText();
        
        assertTrue(resultado.equals("Hello World!"), "El texto cargado no es correcto");
    }

    @AfterEach
    void tearDown() {
        // Esto se ejecuta DESPUÉS de cada test, falle o pase
        if (driver != null) {
            driver.quit();
        }
    }
}