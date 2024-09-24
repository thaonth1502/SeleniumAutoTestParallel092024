package com.thaonth.Bai30_ScreenshotAndRecordVideo;

import com.thaonth.common.BaseTest;
import com.thaonth.drivers.DriverManager;
import com.thaonth.helpers.CaptureHelper;
import com.thaonth.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class DemoRecordVideo extends BaseTest {


    @Test
    public void testBlogPage() {

        CaptureHelper.startRecord("testBlogPage");
        WebUI.openURL("https://anhtester.com");
        WebUI.assertEquals(DriverManager.getDriver().getTitle(), "Anh Tester Automation Testing", "\uD83D\uDC1E FAIL!!! The title not match");

        WebUI.clickElement(By.xpath("//a[normalize-space()='blog']"));
        WebUI.waitForPageLoaded();
        WebUI.clickElement(By.xpath("//a[normalize-space()='Selenium']"));
        WebUI.clickElement(By.xpath("(//div[@class='card-image'])[1]"));
        WebUI.waitForPageLoaded();
        CaptureHelper.stopRecord();

    }
}
