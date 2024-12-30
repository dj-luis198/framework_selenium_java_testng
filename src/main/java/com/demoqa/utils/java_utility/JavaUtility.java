package com.demoqa.utils.java_utility;

public class JavaUtility {

    private JavaUtility() {
    }

    public static String replaceSpaces(String text) {
        return text.replaceAll("\\s", "_");
    }

    public static String replaceBreaks(String text) {
        return text.replaceAll("\\n\\s*", " ");
    }

    public static String[] generateArray(String text) {
        return text.split("\\s+");
    }
}


