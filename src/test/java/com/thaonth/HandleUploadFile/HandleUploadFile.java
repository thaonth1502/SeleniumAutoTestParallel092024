package com.thaonth.HandleUploadFile;

import com.thaonth.common.BaseTest;
import com.thaonth.helpers.SystemHelper;
import com.thaonth.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class HandleUploadFile extends BaseTest {

    @Test
    public void testUploadFileWithSendKeys(){
        WebUI.openURL("https://cgi-lib.berkeley.edu/ex/fup.html");
        WebUI.waitForPageLoaded();
        WebUI.sleep(2);
        By inputFileUpload = By.xpath("//input[@name='upfile']");
        By btnPress = By.xpath("//input[@value='Press']");
        String filePath = SystemHelper.getCurrentDir() + "src\\test\\resources\\dataTest\\Login.xlsx";
        WebUI.setText(inputFileUpload,filePath);
        WebUI.sleep(4);
        WebUI.clickElement(btnPress);
        WebUI.sleep(2);
        Assert.assertTrue(WebUI.checkElementExist( By.xpath("//h1[normalize-space()='File Upload Results']")),"FAIL");
    }

    @Test
    public void testUploadFileWithRobot(){
        WebUI.openURL("https://files.fm/");
        WebUI.waitForPageLoaded();

        By textOnPage = By.xpath("//div[@id='file_select_dragndrop_text']");
        By divFileUpload = By.xpath("//div[@id='uploadifive-file_upload']");
        By inputFileUpload = By.xpath("//div[@id='file_select_button']//input[@id='file_upload']");

        String filePath = SystemHelper.getCurrentDir() + "src\\test\\resources\\dataTest\\Login.xlsx";
        WebUI.uploadFileWithRobotClass(divFileUpload, filePath);

        //Verify file upload success
        By fileNameAfterUploadSucess = By.xpath("//span[@class='filename']");
        Assert.assertTrue(WebUI.checkElementExist(fileNameAfterUploadSucess), "Can not upload file.");
        Assert.assertEquals(WebUI.getElementText(fileNameAfterUploadSucess), "Login.xlsx", "File name is wrong");

        }

    }
