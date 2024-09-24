package com.thaonth.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    @Override
    public void onStart(ITestContext arg0) {
        System.out.println("onStart");
    }

    @Override
    public void onFinish(ITestContext arg0) {
        System.out.println("onFinish");

    }

    @Override
    public void onTestStart(ITestResult arg0) {
        System.out.println("onTestStart");

    }

    @Override
    public void onTestSuccess(ITestResult arg0) {
        System.out.println("onTestSuccess");

    }

    @Override
    public void onTestFailure(ITestResult arg0) {
        System.out.println("onTestFailure");

    }

    @Override
    public void onTestSkipped(ITestResult arg0) {
        System.out.println("onTestSkipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("onTestFailedButWithinSuccessPercentage");
    }

}
