package com.demoqa.utils.extentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;

import java.io.File;

public class ExtentReportGenerator {
        static ExtentReports report;

        public static ExtentReports getExtentReport(){
            report = new ExtentReports();
            File extentReportFile= new File(System.getProperty("user.dir")+"/ExtentReports/index.html");
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
            sparkReporter.viewConfigurer().viewOrder().as(new ViewName[]{ViewName.DASHBOARD, ViewName.TEST, ViewName.EXCEPTION, ViewName.CATEGORY, ViewName.LOG, ViewName.DEVICE, ViewName.AUTHOR}).apply();
            sparkReporter.config().setDocumentTitle("DEMOQA");
            sparkReporter.config().setReportName("DEMOQA Reports");
            report.attachReporter(sparkReporter);
            report.setSystemInfo("Tester","Daniel Farias");
            report.setSystemInfo("os", "windows");
            report.setSystemInfo("browser", "chrome, firefox, edge");
            return report;

        }

    }
