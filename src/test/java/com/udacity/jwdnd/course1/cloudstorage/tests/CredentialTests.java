package com.udacity.jwdnd.course1.cloudstorage.tests;

import com.udacity.jwdnd.course1.cloudstorage.data.Credential;
import com.udacity.jwdnd.course1.cloudstorage.data.Note;
import com.udacity.jwdnd.course1.cloudstorage.pages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.ResultPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.UUID;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CredentialTests {
    @LocalServerPort
    private Integer port;

    private WebDriver driver;
    private String randomUserName;

    private ResultPage resultPage;
    private HomePage homePage;
    private LoginPage loginPage;
    private SignupPage signupPage;

    @BeforeAll
    void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        resultPage = new ResultPage(driver);
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        signupPage = new SignupPage(driver);
    }

    @AfterAll
    void afterAll() {
        driver.quit();
    }

    @BeforeEach
    void beforeEach() {
        randomUserName = UUID.randomUUID().toString();
    }

    @AfterEach
    void afterEach() {
        logout();
    }

    private void logout() {
        if (homePage.isPageDisplayed()) {
            homePage.logout();
        }
    }

    @Test
    void testCreateNote() {
        driver.get("http://localhost:" + port + "/login");

        signupNewUserAndLogin();
        homePage.clickOnCredentialsTab();

        Credential firstCredential = new Credential();
        firstCredential.setUrl("http://google.com");
        firstCredential.setUsername("test");
        firstCredential.setPassword("google");
        homePage.addCredential(firstCredential);
        resultPage.goToHomePage();
        homePage.clickOnCredentialsTab();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Credential secondCredential = new Credential();
        secondCredential.setUrl("https://facebook.com");
        secondCredential.setUsername("test");
        secondCredential.setPassword("facebook");
        homePage.addCredential(secondCredential);
        resultPage.goToHomePage();
        homePage.clickOnCredentialsTab();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    @Test
    void testEditNote() {

    }

    @Test
    void testRemoveNote() {

    }

    private Note createNote() {
        return new Note("Note title", "Note description");
    }

    private void signupNewUserAndLogin() {
        String password = "test";
        loginPage.goToSignupPage();
        signupPage.signupNewUser(randomUserName, password);
        signupPage.goToLoginPage();
        loginPage.login(randomUserName, password);
    }

}