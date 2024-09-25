package com.thaonth.listeners;

import com.aventstack.extentreports.Status;
import com.thaonth.helpers.CaptureHelper;
import com.thaonth.helpers.PropertiesHelper;
import com.thaonth.reports.ExtentReportManager;
import com.thaonth.reports.ExtentTestManager;
import com.thaonth.utils.LogUtils;
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
    public void onStart(ITestContext iTestContext) {
        LogUtils.info("⭐\uFE0F ********** START TESTING **********");
        PropertiesHelper.loadAllFiles();
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        LogUtils.info("⭐\uFE0F ********** END TESTING **********");
        //Kết thúc và thực thi Extents Report
        ExtentReportManager.getExtentReports().flush();

    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        LogUtils.info("▶\uFE0F Starting test case " + iTestResult.getName());
        //Bắt đầu ghi 1 TCs mới vào Extent Report
        ExtentTestManager.saveToReport(getTestName(iTestResult), getTestDescription(iTestResult));
        if (PropertiesHelper.getValue("RECORD_VIDEO").equals("true")){
            CaptureHelper.startRecord(iTestResult.getName());
        }

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        LogUtils.info("☑\uFE0F Test case " + iTestResult.getName() + "is passed");
        //Extent Report
        ExtentTestManager.logMessage(Status.PASS, iTestResult.getName() + " is passed.");

        if (PropertiesHelper.getValue("SCREENSHOT_STEP_PASS").equals("true")){
            CaptureHelper.screenshot(iTestResult.getName());
        }

        if (PropertiesHelper.getValue("RECORD_VIDEO").equals("true")){
            CaptureHelper.stopRecord();
        }
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        LogUtils.error("Test case " + iTestResult.getName() + "failed");
        LogUtils.error(iTestResult.getThrowable());

        //Extent Report
        ExtentTestManager.addScreenshot(getTestName(iTestResult));
        ExtentTestManager.logMessage(Status.FAIL, iTestResult.getThrowable().getMessage());
        ExtentTestManager.logMessage(Status.FAIL, iTestResult.getThrowable().toString());
        ExtentTestManager.logMessage(Status.FAIL, iTestResult.getName() + " is failed.");

        if (PropertiesHelper.getValue("SCREENSHOT_STEP_FAIL").equals("true")){
            CaptureHelper.screenshot(iTestResult.getName());
        }

        if (PropertiesHelper.getValue("RECORD_VIDEO").equals("true")){
            CaptureHelper.stopRecord();
        }



    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        LogUtils.warn("⏭\uFE0F Skip test case " + iTestResult.getName());

        //Extent Report
        ExtentTestManager.logMessage(Status.SKIP, iTestResult.getThrowable().toString());

        if (PropertiesHelper.getValue("RECORD_VIDEO").equals("true")){
            CaptureHelper.stopRecord();
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        LogUtils.info("onTestFailedButWithinSuccessPercentage");
    }

}
