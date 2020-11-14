package com.udacity.jwdnd.course1.cloudstorage.tests;

import com.udacity.jwdnd.course1.cloudstorage.pages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.UUID;

import static org.springframework.test.util.AssertionErrors.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UnauthorizedAccessTests {
    @LocalServerPort
    private Integer port;

    private static WebDriver driver;
    private static String randomUserName;
    private static String password = "test";

    @BeforeAll
    static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        randomUserName = UUID.randomUUID().toString();
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }

    @Test
    void testAnonymousUserCanNotAccessHomePage() {
        driver.get("http://localhost:" + port + "/home");

        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        SignupPage signupPage = new SignupPage(driver);

        assertFalse(
                "Home page should not be displayed for logged out user",
                homePage.isPageDisplayed()
        );

        assertTrue("Login page should be displayed", loginPage.isPageDisplayed());

        loginPage.goToSignupPage();

        assertTrue("Singup page should be displayed", signupPage.isPageDisplayed());
    }

    @Test
    void testSignupFlow() {
        driver.get("http://localhost:" + port + "/login");

        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        SignupPage signupPage = new SignupPage(driver);

        assertTrue("Login page should be displayed", loginPage.isPageDisplayed());

        loginPage.goToSignupPage();
        signupPage.signupNewUser(randomUserName, password);

        signupPage.goToLoginPage();
        loginPage.login(randomUserName, password);

        assertTrue("Home page should be displayed", homePage.isPageDisplayed());

        homePage.logout();
        assertTrue("Login page should be displayed", loginPage.isPageDisplayed());

        driver.get("http://localhost:" + port + "/home");

        assertFalse("Home page should not be displayed", homePage.isPageDisplayed());
        assertTrue("Login page should be displayed", loginPage.isPageDisplayed());
    }

}
