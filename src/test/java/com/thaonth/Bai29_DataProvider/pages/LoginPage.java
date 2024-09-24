package com.thaonth.Bai29_DataProvider.pages;

import com.thaonth.contants.DataConfig;
import com.thaonth.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class LoginPage extends CommonPage {


    //Khai báo biến tooàn cục
    SoftAssert softAssert = new SoftAssert();
    //Khai báo các element dạng đối tượng By
    private By headerPage = By.xpath("//h1[normalize-space()='Login']");
    private By inputEmail = By.xpath("//input[@id='email']");
    private By inputPassword = By.xpath("//input[@id='password']");
    private By buttonLogin = By.xpath("//button[normalize-space()='Login']");
    private By errorMessage = By.xpath("//div[contains(@class,'alert')]");
    private By menuDashboard = By.xpath("//span[normalize-space()='Dashboard']");


    //Khai báo các hàm xử lý thuộc trang Login
    public void enterEmail(String email){
        WebUI.setText(inputEmail, email);
    }

    public void enterPassword(String password){
        WebUI.setText(inputPassword, password);

    }

    public void clickLoginButton(){
        WebUI.clickElement(buttonLogin);
    }

    public DashboardPage loginCRM(String email, String password){
        WebUI.openURL(DataConfig.URL);
        WebUI.waitForPageLoaded();
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();

        return new DashboardPage();
    }

    public void verifyLoginSuccess(){
        WebUI.waitForPageLoaded();
        softAssert.assertTrue(WebUI.checkElementExist(menuDashboard), "\uD83D\uDC1E FAIL!!! Can not redirect to Dashboard page.");
        WebUI.assertEquals(WebUI.getCurrentURL(), "https://crm.anhtester.com/admin/", "\uD83D\uDC1E FAIL!!! Current page not match.");
        softAssert.assertAll();
    }

    public void verifyLoginFail(String messageExpected){
        WebUI.waitForPageLoaded();
        softAssert.assertTrue(WebUI.checkElementExist(errorMessage), "\uD83D\uDC1E  FAIL!!! Error message not displayed.");
        WebUI.assertEquals(WebUI.getElementText(errorMessage),messageExpected, "\uD83D\uDC1E FAIL!!! The content message not match.");
        softAssert.assertAll();
    }
}
