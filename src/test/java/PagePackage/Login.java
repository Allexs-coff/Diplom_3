package PagePackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.Augmenter;
import java.time.Duration;

public class Login {

    private By formTitle = By.xpath(".//*[text()='Вход']");
    private By email = By.xpath(".//input[@name='name']");
    private By password = By.xpath(".//input[@name='Пароль']");
    private By buttonLogin = By.xpath(".//button[text()='Войти']");
    private String token ="";


    private WebDriver driver;

    public Login(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadForm() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(formTitle));
    }

    public void fillingLoginFields(String email, String password){
        driver.findElement(this.email).sendKeys(email);
        driver.findElement(this.password).sendKeys(password);

    }

    public void clickButtonLogin(){
        driver.findElement(buttonLogin).click();
    }




}
