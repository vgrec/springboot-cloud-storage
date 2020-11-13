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

    public void signupNewUser(String userName) {
        webDriver.findElement(By.id("inputFirstName")).sendKeys("First name");
        webDriver.findElement(By.id("inputLastName")).sendKeys("Last name");
        webDriver.findElement(By.id("inputUsername")).sendKeys(userName);
        webDriver.findElement(By.id("inputPassword")).sendKeys("1");

        webDriver.findElement(By.id("signupButton")).click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
