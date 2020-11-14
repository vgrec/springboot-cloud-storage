package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage {

    private WebDriver webDriver;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    @Override
    public boolean isPageDisplayed() {
        return isElementDisplayed(By.id("loginLabel"))
                && isElementDisplayed(By.id("inputUsername"))
                && isElementDisplayed(By.id("inputPassword"))
                && isElementDisplayed(By.id("loginButton"));
    }

    @Override
    WebDriver getWebDriver() {
        return webDriver;
    }

    public void goToSignupPage() {
        click("signupLink");
    }

    public void login(String randomUserName, String password) {
        setInputText(randomUserName, "inputUsername");
        setInputText(password, "inputPassword");
        click("loginButton");
    }
}
