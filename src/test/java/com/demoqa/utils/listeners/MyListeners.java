package com.demoqa.utils.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.demoqa.base.BaseTest;
import com.demoqa.utils.extentReport.ExtentReportGenerator;
import com.demoqa.utils.logsUtility.AnsiColorUtils;
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

public class MyListeners extends BaseTest implements ITestListener {

        private static Logger logger = LogManager.getLogger(MyListeners.class);

        ExtentReports report = ExtentReportGenerator.getExtentReport();
        private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

        ExtentTest eTest;

        @Override
        public void onTestStart(ITestResult result) {
            String browser = result.getTestContext().getCurrentXmlTest().getParameter("browser");
            String testName = result.getMethod().getMethodName();
            String divice = System.getProperty("os.name") + "-" + System.getProperty("os.version") + "-"
                    + System.getProperty("os.arch");
            String emule = result.getTestContext().getCurrentXmlTest().getParameter("deviceName");
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
                extentTest.get().assignDevice(divice + "-" + browser+"-"+ emule);
            } else {
                browser = "API";
                extentTest.get().assignDevice(divice + "-" + browser);
            }
            logger.info(AnsiColorUtils.applyPurple("--- Started: " + testName + " brw:" + browser + " ---"));
        }

        @Override
        public void onTestSuccess(ITestResult result) {
            String testName = result.getMethod().getMethodName();
            extentTest.get().log(Status.PASS, testName + " got successfully executed");
            logger.info(AnsiColorUtils.applyGreen("test passed: " + testName));
        }

        @Override
        public void onTestFailure(ITestResult result) {
            if (result.getMethod().getRetryAnalyzer(result) != null) {
                MyRetryAnalyzer retryAnalyzer = (MyRetryAnalyzer) result.getMethod().getRetryAnalyzer(result);
                if (retryAnalyzer.retry(result)) {
                    logger.warn(AnsiColorUtils.applyYellow("Retrying test: " + result.getMethod().getMethodName()));
                    return;
                }
            }
            String testName = result.getMethod().getMethodName();
            String testNameScreen = result.getMethod().getMethodName() + result.getTestContext().getCurrentXmlTest().getParameter("browser");
            String deviceName = result.getMethod().getMethodName() + result.getTestContext().getCurrentXmlTest().getParameter("deviceName");
            if (getDriver() != null) {
                try {
                    File screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
                    File destFile = new File(System.getProperty("user.dir") + "/ExtentReports/Screenshots/" + testNameScreen + deviceName +".png");
                    FileUtils.copyFile(screenshot, destFile);
                    extentTest.get().addScreenCaptureFromPath("Screenshots/" + destFile.getName(), testName);
                } catch (Exception e) {
                    logger.error("Error al agregar captura de pantalla al informe", e);
                }
            }
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
            if (result.getMethod().getRetryAnalyzer(result) != null) {
                MyRetryAnalyzer retryAnalyzer = (MyRetryAnalyzer) result.getMethod().getRetryAnalyzer(result);
                if (retryAnalyzer.retry(result)) {
                    return; // No registrar el test como omitido si se va a reintentar
                }
            } String testName = result.getMethod().getMethodName();
            extentTest.get().log(Status.SKIP, testName + " test skipped");
            extentTest.get().skip(result.getThrowable());
            logger.warn(AnsiColorUtils.applyYellow("test skipped: " + testName + "\n" + result.getThrowable()));
        }

    protected String takesScreenshot(String testName, WebDriver driver) throws Exception {
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File(System.getProperty("user.dir") + "/ExtentReports/Screenshots/" + testName + ".png");
        FileUtils.copyFile(sourceFile, destFile);
        return "Screenshots/" + destFile.getName();
    }
    }
