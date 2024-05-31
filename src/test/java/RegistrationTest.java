import BrowserFactory.BrowserFactory;
import DataPrepare.DataManager;
import PagePackage.Login;
import PagePackage.MainPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import java.io.FileNotFoundException;


public class RegistrationTest {
    private WebDriver driver;


    @Before
    public void launchGoogleChrome() throws FileNotFoundException {
        BrowserFactory browserFactory = new BrowserFactory();
        driver = browserFactory.createDriver();
    }

    @Test
    @DisplayName("Registration with correct data")
    public void registrationTest() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
        PagePackage.Registration registration = new PagePackage.Registration(driver);
        registration.waitForLoadForm();
        MainPage mainPage = new MainPage(driver);
        DataManager dataManager = new DataManager();
        Login login = new Login(driver);
        dataManager.setUp();
        dataManager.testDataGen();
        registration.fillingFields(dataManager.getTestData().get("name"),dataManager.getTestData().get("email"),dataManager.getTestData().get("password"));
        registration.clickOnButtonReg();
        login.waitForLoadForm();
        login.fillingLoginFields(dataManager.getTestData().get("email"),dataManager.getTestData().get("password"));
        login.clickButtonLogin();
        mainPage.waitForLoadForm();
        dataManager.deleteUser(mainPage.getToken());
    }

    @Test
    @DisplayName("Registration with incorrect password")
    public void registrationWithWrongDataTest() throws Exception {
        driver.get("https://stellarburgers.nomoreparties.site/register");
        PagePackage.Registration registration = new PagePackage.Registration(driver);
        registration.waitForLoadForm();
        DataManager dataManager = new DataManager();
        dataManager.testWrongDataGen();
        registration.fillingFields(dataManager.getTestData().get("name"),dataManager.getTestData().get("email"),dataManager.getTestData().get("password"));
        registration.clickOnButtonReg();
        try {
            registration.waitForLoadErrorText();
        }catch (TimeoutException e){throw new Exception("не отобразилось оповещение об ошибке: \"Некорректный пароль\"");}
    }


    @After
    public void teardown() {
        // Закрытие браузера
        driver.quit();
    }










}
