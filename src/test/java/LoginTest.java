import BrowserFactory.BrowserFactory;
import DataPrepare.DataManager;
import PagePackage.ForgotPassword;
import PagePackage.Login;
import PagePackage.MainPage;
import PagePackage.Registration;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import java.io.FileNotFoundException;

public class LoginTest {
    private WebDriver driver;

    @Before
    public void launchGoogleChrome() throws FileNotFoundException {
        BrowserFactory browserFactory = new BrowserFactory();
        driver = browserFactory.createDriver();
    }

    @Test
    @DisplayName("Login from the main page")
    public void loginFromMainPageTest(){
        driver.get("https://stellarburgers.nomoreparties.site/");
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginStartButton();
        DataManager dataManager = new DataManager();
        dataManager.setUp();
        dataManager.createUniqueUser();
        Login login = new Login(driver);
        login.waitForLoadForm();
        login.fillingLoginFields(dataManager.getTestData().get("email"),dataManager.getTestData().get("password"));
        login.clickButtonLogin();
        mainPage.waitForLoadForm();
        dataManager.deleteUser(mainPage.getToken());
    }



    @Test
    @DisplayName("Go to the login page via the \"Personal Account\" link")
    public void loginFromLinkPersonalAccountPageTest(){
        driver.get("https://stellarburgers.nomoreparties.site/");
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLinkPersonalAccount();
        DataManager dataManager = new DataManager();
        dataManager.setUp();
        dataManager.createUniqueUser();
        Login login = new Login(driver);
        login.waitForLoadForm();
        login.fillingLoginFields(dataManager.getTestData().get("email"),dataManager.getTestData().get("password"));
        login.clickButtonLogin();
        mainPage.waitForLoadForm();
        dataManager.deleteUser(mainPage.getToken());    }

    @Test
    @DisplayName("Login from the \"registration\" page")
    public void loginFromRegisterPageTest(){
        driver.get("https://stellarburgers.nomoreparties.site/register");
        Registration registration = new Registration(driver);
        MainPage mainPage = new MainPage(driver);
        registration.waitClickableElement();
        registration.clickLinkLogin();
        DataManager dataManager = new DataManager();
        dataManager.setUp();
        dataManager.createUniqueUser();
        Login login = new Login(driver);
        login.waitForLoadForm();
        login.fillingLoginFields(dataManager.getTestData().get("email"),dataManager.getTestData().get("password"));
        login.clickButtonLogin();
        mainPage.waitForLoadForm();
        dataManager.deleteUser(mainPage.getToken());    }

    @Test
    @DisplayName("Login from the \"Password recovery\" page")
    public void loginFromPassRecTest(){
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
        ForgotPassword forgotPassword = new ForgotPassword(driver);
        MainPage mainPage = new MainPage(driver);
        forgotPassword.clickLinkLogin();
        DataManager dataManager = new DataManager();
        dataManager.setUp();
        dataManager.createUniqueUser();
        Login login = new Login(driver);
        login.waitForLoadForm();
        login.fillingLoginFields(dataManager.getTestData().get("email"),dataManager.getTestData().get("password"));
        login.clickButtonLogin();
        mainPage.waitForLoadForm();
        dataManager.deleteUser(mainPage.getToken());
    }


    @After
    public void teardown() {
        // Закрытие браузера
        driver.quit();
    }

}
