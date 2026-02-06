package proyectos_gradle;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class AppTest extends BaseTest { // <--- Herencia mágica

    @Test
    @DisplayName("Login exitoso en Herokuapp")
    void testLoginExitoso() {
        driver.get("https://the-internet.herokuapp.com/login");
        LoginPage loginPage = new LoginPage(driver);

        loginPage.escribirUsuario("tomsmith");
        loginPage.escribirPassword("SuperSecretPassword!");
        loginPage.clickLogin();

        assertTrue(loginPage.obtenerMensaje().contains("You logged into a secure area!"));
    }

    @Test
    @DisplayName("Manejo de carga dinámica con esperas explícitas")
    void testDynamicLoading() {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        DynamicLoadingPage dynamicPage = new DynamicLoadingPage(driver);
        
        dynamicPage.clickStart();
        assertTrue(dynamicPage.getLoadedText().equals("Hello World!"));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/usuarios.csv", numLinesToSkip = 1)
    @DisplayName("Pruebas de Login con archivo CSV externo")
    void testLoginConCSV(String usuario, String password, String mensajeEsperado) {
        driver.get("https://the-internet.herokuapp.com/login");

        driver.findElement(By.id("username")).sendKeys(usuario);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        String mensajeActual = driver.findElement(By.id("flash")).getText();
        assertTrue(mensajeActual.contains(mensajeEsperado));
    }
}