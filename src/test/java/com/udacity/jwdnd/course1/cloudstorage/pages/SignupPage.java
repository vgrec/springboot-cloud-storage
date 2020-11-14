package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignupPage extends AbstractPage {

    private WebDriver webDriver;

    public SignupPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Override
    public boolean isPageDisplayed() {
        return isElementDisplayed(By.id("signupLabel"))
                && isElementDisplayed(By.id("inputFirstName"))
                && isElementDisplayed(By.id("inputLastName"))
                && isElementDisplayed(By.id("inputUsername"))
                && isElementDisplayed(By.id("inputPassword"))
                && isElementDisplayed(By.id("signupButton"));
    }

    @Override
    WebDriver getWebDriver() {
        return webDriver;
    }

    public void signupNewUser(String userName, String password) {
        setInputText("Test First Name", "inputFirstName");
        setInputText("Test Last Name", "inputLastName");
        setInputText(userName, "inputUsername");
        setInputText(password, "inputPassword");

        click("signupButton");
    }

    public void goToLoginPage() {
        click("loginLink");
    }
}
