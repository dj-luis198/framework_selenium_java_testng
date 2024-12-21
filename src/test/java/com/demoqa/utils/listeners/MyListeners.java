package com.demoqa.utils.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.demoqa.base.Base;
import com.demoqa.utils.extent_report.ExtentReportGenerator;
import com.demoqa.utils.java_utility.JavaUtility;
import com.demoqa.utils.logs_utility.AnsiColorUtils;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

public class MyListeners extends Base implements ITestListener {

        private final Logger logger = LogManager.getLogger(MyListeners.class);
        private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

        ExtentReports report = ExtentReportGenerator.getExtentReport();
        private final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

        ExtentTest eTest;

        @Override
        public void onTestStart(ITestResult result) {
            WebDriver webDriver = getDriver();
            driver.set(webDriver);
            String browser = result.getTestContext().getCurrentXmlTest().getParameter("browser");
            String testName = result.getMethod().getMethodName();
            String device = System.getProperty("os.name") + "-" + System.getProperty("os.version") + "-"
                    + System.getProperty("os.arch");
            String emulate = result.getTestContext().getCurrentXmlTest().getParameter("deviceName");
            eTest = report.createTest(testName);
            extentTest.set(eTest);
            extentTest.get().assignCategory(result.getMethod().getGroups());
            extentTest.get().info(result.getMethod().getDescription());

            boolean flag = false;
            for (String group : result.getMethod().getGroups()) {
                if (group.equals("API")) {
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                extentTest.get().assignDevice(device + "-" + browser+"-"+ JavaUtility.replaceSpaces(emulate));
            } else {
                browser = "API";
                extentTest.get().assignDevice(device + "-" + browser);
            }
            logger.info(AnsiColorUtils.applyPurple("--- Started: " + testName + " brw:" + browser + " ---"));
        }

        @Override
        public void onTestSuccess(ITestResult result) {
            String testName = result.getMethod().getMethodName();
            takeScreenshot(result, testName);
            extentTest.get().log(Status.PASS, testName + " got successfully executed");
            logger.info(AnsiColorUtils.applyGreen("test passed: " + testName));
        }

        @Override
        public void onTestFailure(ITestResult result) {
            String testName = result.getMethod().getMethodName();
            takeScreenshot(result, testName);
            extentTest.get().log(Status.FAIL, testName + " test failed");
            extentTest.get().fail(result.getThrowable());
            logger.error(AnsiColorUtils.applyRed("test failed: " + testName + "\n" + result.getThrowable()));
        }

        @Override
        public void onFinish(ITestContext context) {
            report.flush();
        }


        @Override
        public void onTestFailedWithTimeout(ITestResult result) {
            extentTest.get().fail(result.getThrowable());
            logger.error(AnsiColorUtils.applyRed(
                    "test failed with timeout: " + result.getMethod().getMethodName() + "\n" + result.getThrowable()));
        }

        @Override
        public void onTestSkipped(ITestResult result) {
            String testName = result.getMethod().getMethodName();
            extentTest.get().log(Status.SKIP, testName + " test skipped");
            extentTest.get().skip(result.getThrowable());
            logger.warn(AnsiColorUtils.applyYellow("test skipped: " + testName + "\n" + result.getThrowable()));
        }

        private void takeScreenshot(ITestResult result, String testName) {
            String testStatus = result.getStatus() == 1 ? "PASSED" : "FAILED";
            String testTimeOut = String.valueOf(result.getEndMillis());
            String testNameScreen = result.getMethod().getMethodName() + result.getTestContext().getCurrentXmlTest().getParameter("browser");
            String deviceName = result.getMethod().getMethodName() + result.getTestContext().getCurrentXmlTest().getParameter("deviceName");
            if (driver.get() != null) {
                try {
                    File screenshot = ((TakesScreenshot) driver.get()).getScreenshotAs(OutputType.FILE);
                    File destFile = new File(System.getProperty("user.dir") + "/ExtentReports/Screenshots/"+testStatus+"_"+ testTimeOut+"_"+testNameScreen +"_"+ JavaUtility.replaceSpaces(deviceName)+".png");
                    FileUtils.copyFile(screenshot, destFile);
                    extentTest.get().addScreenCaptureFromPath("Screenshots/"+ destFile.getName(), testName);
                } catch (Exception e) {
                    logger.error("Error Screenshots", e);
                }
            }
        }

}
