package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage {
    @FindBy(id = "signupLink")
    private WebElement singupLink;

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
        singupLink.click();
    }
}
