package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResultPage extends AbstractPage {
    private WebDriver webDriver;

    public ResultPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Override
    boolean isPageDisplayed() {
        return isElementDisplayed(By.id("errorMessage")) || isElementDisplayed(By.id("successMessage"));
    }

    @Override
    WebDriver getWebDriver() {
        return webDriver;
    }

    public void goToHomePage() {
        click("goHomeLink");
    }
}
