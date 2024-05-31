import BrowserFactory.BrowserFactory;
import DataPrepare.DataManager;
import PagePackage.Constructor;
import PagePackage.Login;
import PagePackage.MainPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.io.FileNotFoundException;

public class ConstructorTest {

    private WebDriver driver;

    @Before
    public void launchGoogleChrome() throws FileNotFoundException {
        BrowserFactory browserFactory = new BrowserFactory();
        driver = browserFactory.createDriver();
        driver.get("https://stellarburgers.nomoreparties.site/");

    }
    @Test
    @DisplayName("go to the \"Buns\" section")
    public void transitToBuns(){
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForLoadForm();
        mainPage.clickLoginStartButton();
        DataManager dataManager = new DataManager();
        dataManager.setUp();
        dataManager.createUniqueUser();
        Login login = new Login(driver);
        login.waitForLoadForm();
        login.fillingLoginFields(dataManager.getTestData().get("email"),dataManager.getTestData().get("password"));
        login.clickButtonLogin();
        Constructor constructor = new Constructor(driver);
        mainPage.waitForLoadForm();
        constructor.clickOnBuns();
        constructor.waitForLoadBuns();
        mainPage.waitForLoadForm();
        dataManager.deleteUser(mainPage.getToken());
    }

    @Test
    @DisplayName("go to the \"Sauces\" section")
    public void transitToSauces(){
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForLoadForm();
        mainPage.clickLoginStartButton();
        DataManager dataManager = new DataManager();
        dataManager.setUp();
        dataManager.createUniqueUser();
        Login login = new Login(driver);
        login.waitForLoadForm();
        login.fillingLoginFields(dataManager.getTestData().get("email"),dataManager.getTestData().get("password"));
        login.clickButtonLogin();
        Constructor constructor = new Constructor(driver);
        constructor.waitClickableTab();
        constructor.clickOnSauces();
        constructor.waitForLoadSauces();
        mainPage.waitForLoadForm();
        dataManager.deleteUser(mainPage.getToken());
    }
    @Test
    @DisplayName("go to the \"Fillings\" section")
    public void transitToFillings(){
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForLoadForm();
        mainPage.clickLoginStartButton();
        DataManager dataManager = new DataManager();
        dataManager.setUp();
        dataManager.createUniqueUser();
        Login login = new Login(driver);
        login.waitForLoadForm();
        login.fillingLoginFields(dataManager.getTestData().get("email"),dataManager.getTestData().get("password"));
        login.clickButtonLogin();
        Constructor constructor = new Constructor(driver);
        mainPage.waitForLoadForm();
        constructor.clickOnFillings();
        constructor.waitForLoadFillings();
        mainPage.waitForLoadForm();
        dataManager.deleteUser(mainPage.getToken());
    }

    @After
    public void teardown() {
        // Закрытие браузера
        driver.quit();
    }
}
