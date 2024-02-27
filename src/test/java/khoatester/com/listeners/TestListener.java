package khoatester.com.listeners;

import khoatester.com.helpers.CaptureHelper;
import khoatester.com.helpers.PropertiesHelper;
import khoatester.com.reports.AllureManager;
import khoatester.com.reports.ExtentReportManager;
import khoatester.com.reports.ExtentTestManager;
import khoatester.com.utils.LogUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    @Override
    public void onStart(ITestContext result) {
        PropertiesHelper.loadAllFiles();
        //Init report (Extent and Allure)
    }

    @Override
    public void onFinish(ITestContext result) {
        LogUtils.info("End testing " + result.getName());
//        ExtentReportManager.getExtentReports().flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        LogUtils.info("Running test case " + result.getName());
        //Record video
        CaptureHelper.startRecord(result.getName());

//        ExtentTestManager.saveToReport(getTestName(result), getTestDescription(result));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtils.info("Test case " + result.getName() + " is passed.");
        //Stop record video
        CaptureHelper.stopRecord();

        //Extent Report
        //ExtentTestManager.logMessage(Status.PASS, result.getName() + " is passed.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogUtils.error("Test case " + result.getName() + " is failed.");
//        //Screenshot when fail
//        CaptureHelper.captureScreenshot(result.getName());
//        LogUtils.error(result.getThrowable().toString());
//
//        //Stop record video
//        CaptureHelper.stopRecord();
//
//        //Extent Report
//        ExtentTestManager.addScreenShot(result.getName());
//        ExtentTestManager.logMessage(Status.FAIL, result.getThrowable().toString());
//        ExtentTestManager.logMessage(Status.FAIL, result.getName() + " is failed.");
//
//        //Allure Report
//        //AllureManager.saveTextLog(result.getName() + " is failed.");
//        AllureManager.saveScreenshotPNG();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogUtils.error("Test case " + result.getName() + " is skipped.");
        LogUtils.error(result.getThrowable().toString());

//        //Stop record video
//        CaptureHelper.stopRecord();
//
//        //Extent Report
//        ExtentTestManager.logMessage(Status.SKIP, result.getThrowable().toString());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        LogUtils.error("This is a test case that failed but has a part that succeeded.: " + result.getName());
        LogUtils.error(result.getThrowable().toString());
    }
}
