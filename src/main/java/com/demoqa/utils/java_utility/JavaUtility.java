package com.demoqa.utils.java_utility;

public class JavaUtility {

    private JavaUtility() {
    }

    public static String replaceSpaces(String text) {
        return text.replaceAll("\\s", "_");
    }
}
