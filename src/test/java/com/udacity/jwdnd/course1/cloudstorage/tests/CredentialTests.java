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

        Credential firstCredentialEntered = credentials.get(0);
        Credential secondCredentialEntered = credentials.get(1);

        // Read the credentials from database
        Credential firstCredentialSaved = credentialService.getCredentialById(firstCredentialEntered.getCredentialId());
        Credential secondCredentialSaved = credentialService.getCredentialById(secondCredentialEntered.getCredentialId());

        // Compare the first entered credential with the one retrieved from database
        assertEquals(firstCredentialEntered.getUrl(), firstCredentialSaved.getUrl());
        assertEquals(firstCredentialEntered.getUsername(), firstCredentialSaved.getUsername());
        assertEquals(firstCredentialEntered.getPassword(), firstCredentialSaved.getPassword());

        // Compare the second entered credential with the one retrieved from database
        assertEquals(secondCredentialEntered.getUrl(), secondCredentialSaved.getUrl());
        assertEquals(secondCredentialEntered.getUsername(), secondCredentialSaved.getUsername());
        assertEquals(secondCredentialEntered.getPassword(), secondCredentialSaved.getPassword());
    }

    @Test
    void testEditCredential() {
        driver.get("http://localhost:" + port + "/login");

        signupNewUserAndLogin();
        homePage.clickOnCredentialsTab();

        // Insert credential
        Credential credential = new Credential();
        credential.setUrl("http://google.com");
        credential.setUsername("test");
        credential.setPassword("google");
        homePage.addCredential(credential);
        resultPage.goToHomePage();
        homePage.clickOnCredentialsTab();

        // Read the list of credentials and check the size is 1
        List<Credential> credentials = homePage.listCredentials();
        assertEquals(1, credentials.size());

        // Read the values
        homePage.clickOnEditCredentialButton();
        Credential firstCredential = homePage.getFirstCredential();

        // Check this is what the user entered previously
        assertEquals(credential.getUrl(), firstCredential.getUrl());
        assertEquals(credential.getUsername(), firstCredential.getUsername());
        assertEquals(credential.getPassword(), firstCredential.getPassword());

        // Update the credential
        credential.setUrl("http://facebook.com");
        credential.setUsername("demo");
        credential.setPassword("demo");
        homePage.updateCredential(credential);
        resultPage.goToHomePage();
        homePage.clickOnCredentialsTab();

        // Check after editing the list of credentials is still 1
        List<Credential> editedCredentials = homePage.listCredentials();
        assertEquals(1, editedCredentials.size());

        // Check the changes are displayed
        assertEquals(editedCredentials.get(0).getUrl(), credential.getUrl());
        assertEquals(editedCredentials.get(0).getUsername(), credential.getUsername());
    }

    @Test
    void testRemoveCredential() {
        driver.get("http://localhost:" + port + "/login");

        signupNewUserAndLogin();
        homePage.clickOnCredentialsTab();

        // Insert credential
        Credential credential = new Credential();
        credential.setUrl("http://google.com");
        credential.setUsername("test");
        credential.setPassword("google");
        homePage.addCredential(credential);
        resultPage.goToHomePage();
        homePage.clickOnCredentialsTab();

        // Read the list of credentials and check the size is 1
        List<Credential> credentials = homePage.listCredentials();
        assertEquals(1, credentials.size());

        homePage.deleteFirstCredential();
        resultPage.goToHomePage();
        homePage.clickOnCredentialsTab();

        // Read the list of credentials and check the size is 0
        // because previously inserted credential was removed
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