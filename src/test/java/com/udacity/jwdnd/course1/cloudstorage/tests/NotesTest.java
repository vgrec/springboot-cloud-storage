package com.udacity.jwdnd.course1.cloudstorage.tests;

import com.udacity.jwdnd.course1.cloudstorage.data.Note;
import com.udacity.jwdnd.course1.cloudstorage.pages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.ResultPage;
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

import static org.springframework.test.util.AssertionErrors.assertEquals;

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

        ResultPage resultPage = new ResultPage(driver);
        HomePage homePage = new HomePage(driver);
        homePage.clickOnNotesTab();

        Note newNote = new Note("Note title", "Note description");

        homePage.addNewNote(newNote);
        resultPage.goToHomePage();
        homePage.clickOnNotesTab();
        homePage.clickOnEditNoteButton();

        Note firstNote = homePage.getFirstNote();

        assertEquals("Note title does not match", newNote.getNoteTitle(), firstNote.getNoteTitle());
        assertEquals("Note description does not match", newNote.getNoteDescription(), firstNote.getNoteDescription());
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

