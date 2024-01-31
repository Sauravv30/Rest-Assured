package com.test.booking.api.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager implements ITestListener {

    private static ExtentReports extent;
    private ExtentSparkReporter extentSparkReporter;
    private ExtentTest test;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private String repName;

//    public static ExtentReports getInstance() {
//        if (extent == null) {
//            createInstance("reports/ExtentReport.html");
//        }
//        return extent;
//    }
//
//    private static ExtentReports createInstance(String fileName) {
//        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(fileName);
//        extent = new ExtentReports();
//        extent.attachReporter(sparkReporter);
//        return extent;
//    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getName());
        test.createNode(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " - Test Case Passed", ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getName());
        test.createNode(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
        test.fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getName());
        test.createNode(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.YELLOW));
        test.skip(result.getThrowable());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Do nothing for now
    }

    @Override
    public void onStart(ITestContext context) {
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timestamp + ".html";
        extentSparkReporter = new ExtentSparkReporter(".\\reports" + repName);
        extentSparkReporter.config().setDocumentTitle("RestAssuredAutomation");
        extentSparkReporter.config().setReportName("Hotel Booking User Api");
        extentSparkReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(extentSparkReporter);
        extent.setSystemInfo("Application", "Hotel Booking User Api");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("User", System.getProperty("Saurav Verma"));
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}

