package com.demoqa.utils.exceptions;

import java.io.IOException;

public class FileException extends IOException {
    public FileException(String message, Throwable cause) {
        super(message, cause);
    }
}
