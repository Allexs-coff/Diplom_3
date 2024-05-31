package PagePackage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private WebDriver driver;

    private By loginStartButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private By linkPersonalAccount = By.xpath(".//p[text()='Личный Кабинет']");
    private By formTitle = By.xpath(".//h1[text()='Соберите бургер']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }


    public void clickLoginStartButton(){

        WebElement element =driver.findElement(loginStartButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
    }

    public void clickLinkPersonalAccount(){
        driver.findElement(linkPersonalAccount).click();
    }


    public void waitForLoadForm() {
        new WebDriverWait(driver, Duration.ofSeconds(40))
                .until(ExpectedConditions.visibilityOfElementLocated(formTitle));
    }

    public String getToken(){
        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        String accessToken = localStorage.getItem("accessToken");
        return accessToken;
    }

}
