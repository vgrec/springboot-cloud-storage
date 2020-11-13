package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class AbstractPage {

    abstract boolean isPageDisplayed();

    abstract WebDriver getWebDriver();

    public boolean isElementDisplayed(By criteria) {
        try {
            return getWebDriver().findElement(criteria).isDisplayed();
        } catch (Exception e) {
            System.out.println("Element with criteria: " + criteria + " not displayed");
            return false;
        }
    }
}
