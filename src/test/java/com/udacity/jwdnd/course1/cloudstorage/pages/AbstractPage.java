package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
        ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].setAttribute('value', '" + text + "');", webElement);
    }

    void click(String elementId) {
        WebElement webElement = getWebDriver().findElement(By.id(elementId));
        ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].click();", webElement);
    }
}
