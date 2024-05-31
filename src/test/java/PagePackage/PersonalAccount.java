package PagePackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAccount {
    public PersonalAccount(WebDriver driver) {
        this.driver = driver;
    }

    private WebDriver driver;
    private By constructorLink = By.xpath(".//p[text()='Конструктор']");
    private By formTitle = By.xpath(".//a[@href='/account/profile']");
    private By logo = By.xpath(".//div/a[@href='/']");
    private By exitButton = By.xpath(".//button[text()='Выход']");

    public void waitForLoadForm() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(formTitle));
    }

    public void clickOnExitButton(){
        driver.findElement(exitButton).click();
    }

    public void clickConstructorLink(){
        driver.findElement(constructorLink).click();
    }

    public void clickOnLogo(){
        driver.findElement(logo).click();
    }

}
