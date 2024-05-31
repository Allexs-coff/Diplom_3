package PagePackage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Constructor {
    private By buns = By.cssSelector(".tab_tab__1SPyG:nth-child(1)");
    private By sauces = By.cssSelector(".tab_tab__1SPyG:nth-child(2)");
    private By fillings = By.cssSelector(".tab_tab__1SPyG:nth-child(3)");
    private By bunsTitle = By.xpath(".//h2[text()='Булки']");
    private By saucesTitle = By.xpath(".//h2[text()='Соусы']");
    private By fillingsTitle = By.xpath(".//h2[text()='Начинки']");

    public void waitForLoadBuns() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(bunsTitle));

    }

    public void waitForLoadSauces() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(saucesTitle));
    }

    public void waitForLoadFillings() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(fillingsTitle));
    }

    public void waitStyleContent(By element){
        new WebDriverWait(driver,Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeContains(element,"class","current"));

    }

    public void waitClickableTab(){
        new WebDriverWait(driver, Duration.ofSeconds(40))
                .until(ExpectedConditions.visibilityOfElementLocated(sauces));
    }

    private WebDriver driver;

    public Constructor(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnBuns(){
        WebElement element =driver.findElement(buns);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
        waitStyleContent(buns);
    }

    public void clickOnSauces(){
        WebElement element =driver.findElement(sauces);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
        waitStyleContent(sauces);
    }

    public void clickOnFillings(){
        WebElement element =driver.findElement(fillings);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
        waitStyleContent(fillings);
    }


}
