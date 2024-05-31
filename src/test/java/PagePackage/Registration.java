package PagePackage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Registration {
    private WebDriver driver;
    //Поле вода для имени
    private List<WebElement> regInfofields;
    private By formTitle = By.xpath(".//*[text()='Регистрация']");
    private By registrationButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private By errorMessage = By.xpath(".//p[text()='Некорректный пароль']");
    private By linkLogin = By.xpath(".//a[@href='/login']");




    public Registration(WebDriver driver) {
        this.driver = driver;
        regInfofields =  driver.findElements(By.xpath(".//*[@type='text']"));
        regInfofields.add(driver.findElement(By.xpath(".//*[@type='password']")));
    }


    public void waitForLoadForm() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(formTitle));
    }

    public void waitClickableElement(){
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(linkLogin));
    }

    public void fillingFields(String name, String email, String password){
          regInfofields.get(0).sendKeys(name);
          regInfofields.get(1).sendKeys(email);
          regInfofields.get(2).sendKeys(password);
    }


    public void waitForLoadErrorText(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
    }

    public void clickLinkLogin(){
        WebElement element =driver.findElement(linkLogin);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
    }

    public void clickOnButtonReg(){
        driver.findElement(registrationButton).click();
    }




}
