package proyectos_gradle;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    // 1. Localizadores (Los guardamos como variables privadas)
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.cssSelector("button[type='submit']");
    private By messageArea = By.id("flash");

    // 2. Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // 3. Acciones (Métodos que describen lo que hace un humano)
    public void escribirUsuario(String user) {
        driver.findElement(usernameField).sendKeys(user);
    }

    public void escribirPassword(String pass) {
        driver.findElement(passwordField).sendKeys(pass);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public String obtenerMensaje() {
        return driver.findElement(messageArea).getText();
    }
}