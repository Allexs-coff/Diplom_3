import BrowserFactory.BrowserFactory;
import DataPrepare.DataManager;
import PagePackage.Login;
import PagePackage.MainPage;
import PagePackage.PersonalAccount;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.io.FileNotFoundException;

public class TransitionsTest {
    private WebDriver driver;

    @Before
    public void launchGoogleChrome() throws FileNotFoundException {
        BrowserFactory browserFactory = new BrowserFactory();
        driver = browserFactory.createDriver();
    }

    @Test
    @DisplayName("go to personal account from the main page")
    public void transitToPersonalAccountFromMainPageTest(){
        driver.get("https://stellarburgers.nomoreparties.site/");
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLinkPersonalAccount();
        Login login = new Login(driver);
        DataManager dataManager = new DataManager();
        dataManager.setUp();
        dataManager.createUniqueUser();
        login.waitForLoadForm();
        login.fillingLoginFields(dataManager.getTestData().get("email"),dataManager.getTestData().get("password"));
        login.clickButtonLogin();
        mainPage.waitForLoadForm();
        dataManager.deleteUser(mainPage.getToken());
        mainPage.clickLinkPersonalAccount();
        PersonalAccount personalAccount = new PersonalAccount(driver);
        personalAccount.waitForLoadForm();
    }
    @Test
    @DisplayName("go to the designer from your personal account")
    public void transitToDesignerFromPersonalAccountTest(){
        driver.get("https://stellarburgers.nomoreparties.site/");
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLinkPersonalAccount();
        Login login = new Login(driver);
        DataManager dataManager = new DataManager();
        dataManager.setUp();
        dataManager.createUniqueUser();
        login.waitForLoadForm();
        login.fillingLoginFields(dataManager.getTestData().get("email"),dataManager.getTestData().get("password"));
        login.clickButtonLogin();
        mainPage.waitForLoadForm();
        mainPage.clickLinkPersonalAccount();
        PersonalAccount personalAccount = new PersonalAccount(driver);
        personalAccount.waitForLoadForm();
        personalAccount.clickConstructorLink();
//        mainPage.waitForLoadForm();
        dataManager.deleteUser(mainPage.getToken());
    }

    @Test
    @DisplayName("transition to the designer from your personal account and through the application logo")
    public void transitToDesignerFromPersAccAndAppLogoTest(){
        driver.get("https://stellarburgers.nomoreparties.site/");
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLinkPersonalAccount();
        Login login = new Login(driver);
        DataManager dataManager = new DataManager();
        dataManager.setUp();
        dataManager.createUniqueUser();
        login.waitForLoadForm();
        login.fillingLoginFields(dataManager.getTestData().get("email"),dataManager.getTestData().get("password"));
        login.clickButtonLogin();
        mainPage.waitForLoadForm();
        mainPage.clickLinkPersonalAccount();
        PersonalAccount personalAccount = new PersonalAccount(driver);
        personalAccount.waitForLoadForm();
        personalAccount.clickConstructorLink();
        mainPage.clickLinkPersonalAccount();
        personalAccount.waitForLoadForm();
        personalAccount.clickOnLogo();
        mainPage.waitForLoadForm();
        dataManager.deleteUser(mainPage.getToken());
    }

    @Test
    @DisplayName("Exit from personal account page")
    public void exitFromPersonalAccountTest(){
        driver.get("https://stellarburgers.nomoreparties.site/");
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLinkPersonalAccount();
        Login login = new Login(driver);
        DataManager dataManager = new DataManager();
        dataManager.setUp();
        dataManager.createUniqueUser();
        login.waitForLoadForm();
        login.fillingLoginFields(dataManager.getTestData().get("email"),dataManager.getTestData().get("password"));
        login.clickButtonLogin();
        mainPage.waitForLoadForm();
        dataManager.deleteUser(mainPage.getToken());
        mainPage.clickLinkPersonalAccount();
        PersonalAccount personalAccount = new PersonalAccount(driver);
        personalAccount.waitForLoadForm();
        personalAccount.clickOnExitButton();
    }


    @After
    public void teardown() {
        // Закрытие браузера
        driver.quit();
    }

}
