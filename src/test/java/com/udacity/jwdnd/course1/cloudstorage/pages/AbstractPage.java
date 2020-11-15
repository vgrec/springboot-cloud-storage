package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {

    abstract boolean isPageDisplayed();

    abstract WebDriver getWebDriver();

    boolean isElementDisplayed(By criteria) {
        try {
            return getWebDriver().findElement(criteria).isDisplayed();
        } catch (Exception e) {
            System.out.println("Element with criteria: " + criteria + " not displayed");
            return false;
        }
    }

    void setInputText(String text, String elementId) {
        WebElement webElement = getWebDriver().findElement(By.id(elementId));

        WebDriverWait wait = new WebDriverWait(getWebDriver(), 10);
        wait.until(ExpectedConditions.visibilityOf(webElement));

        ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].value='" + text + "';", webElement);
    }

    void click(String elementId) {
        WebElement webElement = getWebDriver().findElement(By.id(elementId));
        ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].click();", webElement);
    }

    String getInputText(String elementId) {
        WebElement webElement = getWebDriver().findElement(By.id(elementId));
        return webElement.getAttribute("value");
    }

    String getInputText(WebElement parentElement, String elementId) {
        WebElement webElement = parentElement.findElement(By.id(elementId));
        return webElement.getAttribute("value");
    }

    String getInnerText(WebElement parentElement, String innerElement) {
        WebElement webElement = parentElement.findElement(By.id(innerElement));
        return webElement.getAttribute("innerHTML");
    }

}
