package com.demoqa.utils.web_interactions;

//Métodos para navegar entre páginas:
//navigateTo(String url)
//goToPreviousPage()
//goToNextPage()
//reloadPage()
//refreshPage()
//goBackToPreviousPage()
//goForwardToNextPage()

import org.openqa.selenium.*;

public class NavigationActions {
    WebDriver driver;
    private final ElementActions elementActions;
    private final WaitActions wait;

    public NavigationActions(WebDriver driver) {
        this.driver = driver;
        elementActions = new ElementActions(driver);
        wait = new WaitActions(driver);
    }

    public String getCurrentUrl() {
        try {
            return driver.getCurrentUrl();
        } catch (Exception e) {
            throw new WebDriverException(e);
        }
    }

    public String clickAndSwitchToNewTab (WebElement element ) {
        String originalTab = driver.getWindowHandle();
        elementActions.click(element);
        wait.numberOfWindowsToBe();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalTab.equals(windowHandle)) {
                try {
                    driver.switchTo().window(windowHandle);
                } catch (NoSuchWindowException e) {
                    throw new NoSuchWindowException("No such window",e);
                }
            }
        }
        return originalTab;
    }
    public void switchToOriginalTab (String originalTab) {
        try {
            driver.switchTo().window(originalTab);
        } catch (Exception e) {
            throw new NoSuchWindowException("No such window",e);
        }
    }

    public void switchToFrame (WebElement element) {
        try {
            driver.switchTo().frame(wait.visible(element));
        } catch (Exception e) {
            throw new NoSuchFrameException("No switch To Frame",e);
        }
    }

    public void switchToParentFrame () {
        try {
            driver.switchTo().parentFrame();
        } catch (Exception e) {
            throw new NoSuchFrameException("No switch to parent frame",e);
        }
    }

    public void switchToDefaultContent () {
        try {
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            throw new NoSuchFrameException("No switch to default content",e);
        }
    }

    public void goToNewUrl (String url) {
        driver.navigate().to(url);
    }

    public void back () {
        try {
            driver.navigate().back();
        } catch (WebDriverException e) {
            throw new WebDriverException(e);
        }
    }

    public void forward () {
        try {
            driver.navigate().forward();
        } catch (WebDriverException e) {
            throw new WebDriverException(e);
        }
    }

    public void refresh () {
        try {
            driver.navigate().refresh();
        } catch (WebDriverException e) {
            throw new WebDriverException(e);
        }
    }
}
