package com.demoqa.utils.web_interactions;

import com.demoqa.utils.exceptions.ElementException;
import com.demoqa.utils.external_file_utility.PropertyUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WaitActions {
    private static final Logger logger = LogManager.getLogger(WaitActions.class);
    private static final String NO_SUCH_ELEMENT = "No Such Element Exception __";
    private final WebDriverWait webDriverWait;

    public WaitActions(WebDriver driver) {
        PropertyUtility prop = new PropertyUtility();
        long waitDuration = Long.parseLong(prop.initProperties("general").getProperty("wait.duration"));
        this.webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(waitDuration));
    }

    public WebElement presenceOfElementLocated(By locator) {
        try {
            return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (NoSuchElementException e) {
            logger.error(NO_SUCH_ELEMENT, e);
            throw e;
        }
    }

    public WebElement visible(WebElement element) {
        try {
            return webDriverWait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException ex) {
            logger.error("Timeout Exception element not visible ", ex);
            throw ex;
        }
    }

    public Boolean invisible(WebElement element) {
        try {
            return webDriverWait.until(ExpectedConditions.invisibilityOf(element));
        } catch (NoSuchElementException e) {
            logger.error(NO_SUCH_ELEMENT, e);
            throw e;
        } catch (TimeoutException ex) {
            logger.error("Timeout Exception element not invisible ", ex);
            throw ex;
        }
    }

    public WebElement elementToBeClickable(WebElement element) {
        try {
            return webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (NoSuchElementException e) {
            logger.error(NO_SUCH_ELEMENT, e);
            throw e;
        } catch (TimeoutException ex) {
            logger.error("Timeout Exception element not Clickable ", ex);
            throw ex;
        }
    }

    public Boolean elementToBeSelected(WebElement element) {
        try {
            return webDriverWait.until(ExpectedConditions.elementToBeSelected(element));
        } catch (NoSuchElementException e) {
            logger.error(NO_SUCH_ELEMENT, e);
            throw e;
        } catch (TimeoutException ex) {
            logger.error("Timeout exception, element not selectable ", ex);
            throw ex;
        }
    }

    public Boolean textToBePresentInElement(WebElement element, String text) {
        try {
            return webDriverWait.until(ExpectedConditions.textToBePresentInElement(element, text));
        } catch (NoSuchElementException e) {
            logger.error(NO_SUCH_ELEMENT, e);
            throw e;
        } catch (TimeoutException ex) {
            logger.error("Timeout exception, text is not present in the element ", ex);
            throw ex;
        }
    }

    public Boolean titleContains(String text) {
        try {
            return webDriverWait.until(ExpectedConditions.titleContains(text));
        } catch (NoSuchElementException e) {
            logger.error(NO_SUCH_ELEMENT, e);
            throw e;
        } catch (TimeoutException ex) {
            logger.error("Timeout exception, element does not contain the specified title ", ex);
            throw ex;
        }
    }

    public Boolean elementSelectionStateToBe(WebElement element, Boolean state) {
        try {
            return webDriverWait.until(ExpectedConditions.elementSelectionStateToBe(element, state));
        } catch (NoSuchElementException e) {
            logger.error(NO_SUCH_ELEMENT, e);
            throw e;
        } catch (TimeoutException ex) {
            logger.error("Timeout exception, element does not have the specified state ", ex);
            throw ex;
        }
    }

    public Boolean urlToBe(String urlPage) {
        try {
            return webDriverWait.until(ExpectedConditions.urlToBe(urlPage));
        } catch (NoSuchElementException e) {
            logger.error(NO_SUCH_ELEMENT, e);
            throw e;
        } catch (TimeoutException ex) {
            logger.error("Timeout exception, url is not as specified ", ex);
            throw ex;
        }
    }

    public List<WebElement> presenceOfAllElementsLocatedBy(By locator) {
        try {
            return webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        } catch (NoSuchElementException e) {
            logger.error(NO_SUCH_ELEMENT, e);
            throw e;
        } catch (InvalidSelectorException ex) {
            logger.error("Invalid selector exception ", ex);
            throw ex;
        }
    }

    public void numberOfWindowsToBe() {
        try {
            webDriverWait.until(ExpectedConditions.numberOfWindowsToBe(2));
        } catch (TimeoutException e) {
            throw new ElementException("Timeout Exception or window exception ", e);
        } catch (WebDriverException ex) {
            logger.error("WebDriverException ", ex);
            throw ex;
        }
    }

    public Alert alertIsPresent() {
        try {
            return webDriverWait.until(ExpectedConditions.alertIsPresent());
        } catch (TimeoutException e) {
            logger.error("Alert no present ", e);
            throw e;
        }
    }

}
