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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotesTest {
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
    void testCreateNote() {
        driver.get("http://localhost:" + port + "/login");
        signupNewUserAndLogin();

        HomePage homePage = new HomePage(driver);
        homePage.clickOnNotesTab();

        homePage.addNewNote("Note title", "Note description");



        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void signupNewUserAndLogin() {
        LoginPage loginPage = new LoginPage(driver);
        SignupPage signupPage = new SignupPage(driver);

        loginPage.goToSignupPage();
        signupPage.signupNewUser(randomUserName, password);
        signupPage.goToLoginPage();
        loginPage.login(randomUserName, password);
    }

}

