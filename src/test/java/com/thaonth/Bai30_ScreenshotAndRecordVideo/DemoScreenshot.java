package com.thaonth.Bai30_ScreenshotAndRecordVideo;

import com.thaonth.common.BaseTest;
import com.thaonth.drivers.DriverManager;
import com.thaonth.helpers.CaptureHelper;
import com.thaonth.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.annotations.Test;


public class DemoScreenshot extends BaseTest {

    @Test
    public void testHomePage1() {

        WebUI.openURL("https://anhtester.com");
        WebUI.assertEquals(DriverManager.getDriver().getTitle(), "123 Anh Tester Automation Testing", "\uD83D\uDC1E FAIL!!! The title not match");

        CaptureHelper.screenshot("screenHome");

        WebUI.clickElement(By.xpath("//a[@id='btn-login']"));
        WebUI.waitForPageLoaded();
        CaptureHelper.screenshot("screenLogin");

    }

    @Test
    public void testBlogPage() {

        WebUI.openURL("https://anhtester.com");
        WebUI.assertEquals(DriverManager.getDriver().getTitle(), "Anh Tester Automation Testing", "\uD83D\uDC1E FAIL!!! The title not match");

        WebUI.clickElement(By.xpath("//a[normalize-space()='blog']"));
        WebUI.waitForPageLoaded();
        CaptureHelper.screenshot("screenBlog");

    }
}
