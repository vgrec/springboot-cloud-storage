package com.udacity.jwdnd.course1.cloudstorage.tests;

import com.udacity.jwdnd.course1.cloudstorage.data.Credential;
import com.udacity.jwdnd.course1.cloudstorage.pages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.ResultPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.SignupPage;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Autowired
    private CredentialService credentialService;

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
    void testCreateCredential() {
        driver.get("http://localhost:" + port + "/login");

        signupNewUserAndLogin();
        homePage.clickOnCredentialsTab();

        // Insert first credential
        Credential firstCredential = new Credential();
        firstCredential.setUrl("http://google.com");
        firstCredential.setUsername("test");
        firstCredential.setPassword("google");
        homePage.addCredential(firstCredential);
        resultPage.goToHomePage();
        homePage.clickOnCredentialsTab();

        // Insert second credential
        Credential secondCredential = new Credential();
        secondCredential.setUrl("https://facebook.com");
        secondCredential.setUsername("test");
        secondCredential.setPassword("facebook");
        homePage.addCredential(secondCredential);
        resultPage.goToHomePage();
        homePage.clickOnCredentialsTab();

        // Read the list of credentials and check the size is 2
        List<Credential> credentials = homePage.listCredentials();
        assertEquals(2, credentials.size());

        // Read the credentials from database
        Credential firstCredentialSaved = credentialService.getCredentialById(1);
        Credential secondCredentialSaved = credentialService.getCredentialById(2);

        // Compare the first credential with the one retrieved from database
        assertEquals(credentials.get(0).getUrl(), firstCredentialSaved.getUrl());
        assertEquals(credentials.get(0).getUsername(), firstCredentialSaved.getUsername());
        assertEquals(credentials.get(0).getPassword(), firstCredentialSaved.getPassword());

        // Compare the second credential with the one retrieved from database
        assertEquals(credentials.get(1).getUrl(), secondCredentialSaved.getUrl());
        assertEquals(credentials.get(1).getUsername(), secondCredentialSaved.getUsername());
        assertEquals(credentials.get(1).getPassword(), secondCredentialSaved.getPassword());
    }

    @Test
    void testEditCredential() {

    }

    @Test
    void testRemoveCredential() {
        driver.get("http://localhost:" + port + "/login");

        signupNewUserAndLogin();
        homePage.clickOnCredentialsTab();

        // Insert credential
        Credential firstCredential = new Credential();
        firstCredential.setUrl("http://google.com");
        firstCredential.setUsername("test");
        firstCredential.setPassword("google");
        homePage.addCredential(firstCredential);
        resultPage.goToHomePage();
        homePage.clickOnCredentialsTab();

        // Read the list of credentials and check the size is 1
        List<Credential> credentials = homePage.listCredentials();
        assertEquals(1, credentials.size());

        homePage.deleteFirstCredential();
        resultPage.goToHomePage();
        homePage.clickOnCredentialsTab();

        // Read the list of credentials and check the size is 0
        // because previously inserted credential was remoted
        credentials = homePage.listCredentials();
        assertEquals(0, credentials.size());
    }

    private void signupNewUserAndLogin() {
        String password = "test";
        loginPage.goToSignupPage();
        signupPage.signupNewUser(randomUserName, password);
        signupPage.goToLoginPage();
        loginPage.login(randomUserName, password);
    }

}