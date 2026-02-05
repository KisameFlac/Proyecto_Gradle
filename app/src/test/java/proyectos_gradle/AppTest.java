package proyectos_gradle;

import io.qameta.allure.Attachment; // Importación clave
import org.junit.jupiter.api.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest {
    private WebDriver driver;

    @BeforeEach
    void setup() {
        org.openqa.selenium.chrome.ChromeOptions options = new org.openqa.selenium.chrome.ChromeOptions();
        
        // Si el test corre en GitHub Actions, no hay monitor, usamos modo headless
        if (System.getenv("GITHUB_ACTIONS") != null) {
            options.addArguments("--headless=new");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
        }

        driver = new org.openqa.selenium.chrome.ChromeDriver(options);
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
        if (driver != null) {
            saveScreenshot(driver); // Toma la foto antes de cerrar
            driver.quit();
        }
    }

    // MÉTODO PARA ALLURE
    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] saveScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}