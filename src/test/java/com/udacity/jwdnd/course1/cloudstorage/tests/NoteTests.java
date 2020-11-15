package com.udacity.jwdnd.course1.cloudstorage.tests;

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

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertFalse;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoteTests {
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
        homePage.clickOnNotesTab();

        // Insert new note
        Note newNote = createNote();
        homePage.addNote(newNote);
        resultPage.goToHomePage();
        homePage.clickOnNotesTab();

        // Read the values
        homePage.clickOnEditNoteButton();
        Note firstNote = homePage.getFirstNote();

        assertEquals("Note title does not match", newNote.getNoteTitle(), firstNote.getNoteTitle());
        assertEquals("Note description does not match", newNote.getNoteDescription(), firstNote.getNoteDescription());
    }

    @Test
    void testEditNote() {
        driver.get("http://localhost:" + port + "/login");

        signupNewUserAndLogin();

        // Insert new note
        homePage.clickOnNotesTab();
        Note note = createNote();
        homePage.addNote(note);
        resultPage.goToHomePage();
        homePage.clickOnNotesTab();

        // Change note details
        note.setNoteTitle("Note title edited");
        note.setNoteDescription("Note description edited");
        homePage.clickOnEditNoteButton();
        homePage.updateNote(note);
        resultPage.goToHomePage();
        homePage.clickOnNotesTab();

        // Read the values
        homePage.clickOnEditNoteButton();
        Note firstNote = homePage.getFirstNote();

        assertEquals("Note title does not match", note.getNoteTitle(), firstNote.getNoteTitle());
        assertEquals("Note description does not match", note.getNoteDescription(), firstNote.getNoteDescription());
    }

    @Test
    void testRemoveNote() {
        driver.get("http://localhost:" + port + "/login");

        signupNewUserAndLogin();

        // Insert new note
        homePage.clickOnNotesTab();
        Note note = createNote();
        homePage.addNote(note);
        resultPage.goToHomePage();
        homePage.clickOnNotesTab();

        // Remove note
        homePage.deleteFirstNote();
        resultPage.goToHomePage();
        homePage.clickOnNotesTab();

        assertFalse("First note should not be displayed because it was deleted.",
                homePage.isFirstNoteDisplayed());
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

