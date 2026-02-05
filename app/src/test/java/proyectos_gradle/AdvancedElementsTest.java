package proyectos_gradle;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Pruebas Avanzadas")
@Feature("Elementos Complejos")
public class AdvancedElementsTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setup() {
        ChromeOptions options = new ChromeOptions();
        if (System.getenv("GITHUB_ACTIONS") != null) {
            options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage");
        }
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // --- EJERCICIO 1: ALERTAS ---
    @Test
    @Story("Manejo de Alertas de JS")
    @DisplayName("Aceptar y validar una alerta de JavaScript")
    void testAlertas() {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        
        // Hacer clic en el botón que dispara la alerta
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

        // 1. Esperar a que la alerta aparezca
        Alert alerta = wait.until(ExpectedConditions.alertIsPresent());
        
        // 2. Validar el texto de la alerta
        assertEquals("I am a JS Confirm", alerta.getText());

        // 3. Aceptar (OK) o Cancelar (Dismiss)
        alerta.accept();

        // 4. Validar el resultado en la página
        String result = driver.findElement(By.id("result")).getText();
        assertTrue(result.contains("You clicked: Ok"));
    }

    // --- EJERCICIO 2: TABLAS ---
    @Test
    @Story("Manejo de Tablas")
    @DisplayName("Extraer datos de una tabla dinámica")
    void testTablas() {
        driver.get("https://the-internet.herokuapp.com/tables");

        // Supongamos que queremos encontrar el valor de "Due" para "Jason Doe"
        // Usamos un selector dinámico que busca por texto en la fila
        WebElement cell = driver.findElement(
            By.xpath("//table[@id='table1']//tr[td[text()='Doe']]/td[4]")
        );

        System.out.println("El cargo pendiente de Doe es: " + cell.getText());
        assertEquals("$100.00", cell.getText());
    }

    // --- EJERCICIO 3: IFRAMES ---
@Test
    @Story("Manejo de IFrames")
    @DisplayName("Escribir dentro de un editor en un IFrame")
    void testIFrames() {
        driver.get("https://the-internet.herokuapp.com/tinymce");

        // 1. Esperar a que el IFrame esté disponible y cambiarse a él
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mce_0_ifr"));

        // 2. Esperar a que el cuerpo del editor sea visible y clickeable
        WebElement editor = wait.until(ExpectedConditions.elementToBeClickable(By.id("tinymce")));
        
        // 3. En editores complejos, a veces clear() falla. 
        // Usamos una alternativa más segura:
        editor.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE); 
        
        // 4. Escribir el nuevo texto
        editor.sendKeys("¡Logramos dominar el IFrame!");

        // 5. IMPORTANTE: Salir del IFrame
        driver.switchTo().defaultContent();
        
        String titulo = driver.findElement(By.tagName("h3")).getText();
        assertTrue(titulo.contains("An iFrame containing"));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            saveScreenshot();
            driver.quit();
        }
    }

    @Attachment(value = "Evidence", type = "image/png")
    public byte[] saveScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}