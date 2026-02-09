package proyectos_gradle;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DynamicLoadingPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Localizadores
    private By startButton = By.cssSelector("#start button");
    private By loadedText = By.id("finish");

    public DynamicLoadingPage(WebDriver driver) {
        this.driver = driver;
        // Configuramos una espera de hasta 10 segundos
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickStart() {
        driver.findElement(startButton).click();
    }

    public String getLoadedText() {
        // AQUÍ ESTÁ EL TRUCO: Esperamos a que el elemento sea visible
        // antes de intentar leer su texto.
        wait.until(ExpectedConditions.visibilityOfElementLocated(loadedText));
        return driver.findElement(loadedText).getText();
    }
}