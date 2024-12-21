package com.demoqa.utils.exceptions;

import org.openqa.selenium.WebDriverException;

public class ElementException extends WebDriverException {
    public ElementException(String message, Throwable cause) {
        super(message, cause);
    }
}
