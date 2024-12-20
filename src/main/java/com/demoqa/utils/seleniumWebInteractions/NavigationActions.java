package com.demoqa.utils.seleniumWebInteractions;

//Métodos para navegar entre páginas:
//navigateTo(String url)
//goToPreviousPage()
//goToNextPage()
//reloadPage()
//refreshPage()
//goBackToPreviousPage()
//goForwardToNextPage()

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NavigationActions {
    WebDriver driver;
    private final JSExecutor js;
    private final ElementActions elementActions;
    private final WaitActions wait;

    public NavigationActions(WebDriver driver) {
        this.driver = driver;
        js = new JSExecutor(driver);
        elementActions = new ElementActions(driver);
        wait = new WaitActions(driver);
    }

    public void getCurrentUrl(String url) {
        driver.getCurrentUrl();
    }

    public String clickAndSwitchToNewTab (WebElement element ) {
        String originalUrl = driver.getCurrentUrl();
        String originalTab = driver.getWindowHandle();
        elementActions.click(element);
        wait.numberOfWindowsToBe();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalTab.equals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        return originalTab;
    }
    public void switchToOriginalTab (String originalTab) {
        driver.switchTo().window(originalTab);
    }

    public void switchToFrame (WebElement element) {
        driver.switchTo().frame(wait.visible(element));
    }

    public void switchToParentFrame () {
        driver.switchTo().parentFrame();
    }

    public void switchToDefaultContent () {
        driver.switchTo().defaultContent();
    }

    public void goToNewUrl (String url) {
        driver.navigate().to(url);
    }

    public void back () {
        driver.navigate().back();
    }

    public void forward () {
        driver.navigate().forward();
    }

    public void refresh () {
        driver.navigate().refresh();
    }
}
