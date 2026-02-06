package proyectos_gradle;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

@Epic("Pruebas Avanzadas")
@Feature("Elementos Complejos")
// Heredamos de BaseTest para tener el driver y screenshots automáticos
public class AdvancedElementsTest extends BaseTest {
    
    private WebDriverWait wait;

    @BeforeEach
    void initWait() {
        // El driver ya viene inicializado por BaseTest. 
        // Solo necesitamos configurar el wait aquí.
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    @Story("Manejo de Alertas de JS")
    @DisplayName("Aceptar y validar una alerta de JavaScript")
    void testAlertas() {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

        Alert alerta = wait.until(ExpectedConditions.alertIsPresent());
        assertEquals("I am a JS Confirm", alerta.getText());
        alerta.accept();

        String result = driver.findElement(By.id("result")).getText();
        assertTrue(result.contains("You clicked: Ok"));
    }

    @Test
    @Story("Manejo de Tablas")
    @DisplayName("Extraer datos de una tabla dinámica")
    void testTablas() {
        driver.get("https://the-internet.herokuapp.com/tables");

        WebElement cell = driver.findElement(
            By.xpath("//table[@id='table1']//tr[td[text()='Doe']]/td[4]")
        );

        System.out.println("El cargo pendiente de Doe es: " + cell.getText());
        assertEquals("$100.00", cell.getText());
    }

    @Test
    @Story("Manejo de IFrames")
    @DisplayName("Escribir dentro de un editor en un IFrame")
    void testIFrames() {
        driver.get("https://the-internet.herokuapp.com/tinymce");

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mce_0_ifr"));
        WebElement editor = wait.until(ExpectedConditions.elementToBeClickable(By.id("tinymce")));
        
        editor.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE); 
        editor.sendKeys("¡Logramos dominar el IFrame!");

        driver.switchTo().defaultContent();
        
        String titulo = driver.findElement(By.tagName("h3")).getText();
        assertTrue(titulo.contains("An iFrame containing"));
    }
}