package proyectos_gradle;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class App {
    public static void main(String[] args) {
        // 1. Inicializar el WebDriver (Selenium 4 descarga el driver solo)
        WebDriver driver = new ChromeDriver();

        try {
            // 2. Configurar una espera implícita
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            // 3. Navegar a una página
            driver.get("https://www.google.com");

            // 4. Aceptar cookies (común en muchas regiones)
            // Nota: Este paso puede fallar si no aparece el botón, es solo un ejemplo
            System.out.println("Título de la página: " + driver.getTitle());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5. Cerrar el navegador
            driver.quit();
        }
    }
}