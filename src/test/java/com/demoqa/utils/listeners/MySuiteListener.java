package com.demoqa.utils.listeners;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ISuite;
import org.testng.ISuiteListener;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MySuiteListener implements ISuiteListener {
    private final Logger logger = LogManager.getLogger(MyListeners.class);
    @Override
    public void onFinish(ISuite suite) {
        String reportFolder = createNewFolder();
        copyHTML(reportFolder);
        File screenshotFolder = copyImages(reportFolder);
        deleteImages(screenshotFolder);
        deleteHTML();
    }

    private String createNewFolder() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String formattedDate = formatter.format(LocalDateTime.now());
        String reportFolder = System.getProperty("user.dir") + "/ExtentReports/" + formattedDate;
        File reportDir = new File(reportFolder);
        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }
        return reportFolder;
    }

    private void copyHTML(String reportFolder) {
        File indexFile = new File(System.getProperty("user.dir") + "/ExtentReports/index.html");
        File destIndexFile = new File(reportFolder + "/index.html");
        try {
            FileUtils.copyFile(indexFile, destIndexFile);
        } catch (IOException e) {
            logger.error("Error copy HTML", e);
        }
    }

    private File copyImages(String reportFolder) {
        File screenshotFolder = new File(System.getProperty("user.dir") + "/ExtentReports/Screenshots");
        File newScreenshotFolder = new File(reportFolder + "/Screenshots");
        try {
            FileUtils.copyDirectory(screenshotFolder, newScreenshotFolder);
        } catch (IOException e) {
            logger.error("Error copy images", e);
        }
        return screenshotFolder;
    }

    private void deleteImages(File screenshotFolder) {
        File deleteScreenshotFolder = new File(System.getProperty("user.dir") + "/ExtentReports/Screenshots");
        if (screenshotFolder.exists()) {
            try {
                FileUtils.deleteDirectory(deleteScreenshotFolder);
            } catch (IOException e) {
                logger.error("Error delete images", e);
            }
        }
    }

    private void deleteHTML() {
        File reportFile = new File(System.getProperty("user.dir") + "/ExtentReports/index.html");
        if (reportFile.exists()) {
            try {
                FileUtils.delete(reportFile);
            } catch (IOException e) {
                logger.error("Error  delete HTML", e);
            }
        }
    }
}

