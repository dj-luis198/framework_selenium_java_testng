package com.demoqa.utils.seleniumWebInteractions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JSExecutor {
    private final Waits wait;
    private final JavascriptExecutor jse;

    public JSExecutor(WebDriver driver) {
        this.jse = (JavascriptExecutor) driver;
        this.wait = new Waits(driver);
    }

    protected void clickJS(WebElement element) {
        wait.elementToBeClickable(element);
        jse.executeScript("arguments[0].click();", element);
    }
    //permite desplazarse a un elemento específico en la página.
    protected void scrollToElement(WebElement element) {
        wait.visible(element);
        jse.executeScript("arguments[0].scrollIntoView();", element);
    }
    //permite desplazarse al principio de la página.
    protected void scrollToTop() {
        jse.executeScript("window.scrollTo(0, 0);");
    }
    //permite desplazarse al final de la página.
    protected void scrollToBottom() {
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }
    //permite borrar el valor de un campo de texto.
    protected void clearInputValue(WebElement element) {
        wait.visible(element);
        jse.executeScript("arguments[0].value = '';", element);
    }
    //permite cerrar una alerta emergente.
    protected void dismissAlert() {
        jse.executeScript("window.alert = function(){};");
    }

    //permite hacer doble clic
    protected void doubleClick(WebElement element) {
        wait.elementToBeClickable(element);
        jse.executeScript("arguments[0].dispatchEvent(new MouseEvent('dblclick', {'view': window, 'bubbles': true, 'cancelable': true}))", element);
    }

    //permite hacer clic secundario
    protected void contextClick(WebElement element) {
        wait.elementToBeClickable(element);
        jse.executeScript("arguments[0].dispatchEvent(new MouseEvent('contextmenu', {'view': window, 'bubbles': true, 'cancelable': true}))", element);
    }
}
