package PagePackage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ForgotPassword {
    private WebDriver driver;
    private By linkLogin = By.xpath(".//a[@href='/login']");

    public ForgotPassword(WebDriver driver) {
        this.driver = driver;
    }


    public void clickLinkLogin(){
        WebElement element =driver.findElement(linkLogin);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
    }

}
