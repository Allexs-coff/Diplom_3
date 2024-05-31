package BrowserFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BrowserFactory {
    private static WebDriver driver;


    public WebDriver createDriver() throws FileNotFoundException {
        if (getProperty().contains("Chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

        else if(getProperty().contains("FireFox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        return driver;
    }

    private String getProperty() throws FileNotFoundException {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("src/test/resources/config.properties")){
            properties.load(input);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return  properties.getProperty("browser.name");

    }


}
