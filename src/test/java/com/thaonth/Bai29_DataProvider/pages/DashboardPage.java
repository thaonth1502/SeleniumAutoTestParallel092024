package com.thaonth.Bai29_DataProvider.pages;

import com.thaonth.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class DashboardPage extends CommonPage {


    SoftAssert softAssert = new SoftAssert();


    private By menuDashboard = By.xpath("//span[normalize-space()='Dashboard']");
    private By buttonDashboardOptions = By.xpath("//div[@class='screen-options-btn']");
    private By totalInvoicesAwaitingPayment = By.xpath("(//span[normalize-space()='Invoices Awaiting Payment']/parent::div)/following-sibling::span");
    private By totalConvertedLeads = By.xpath("(//span[normalize-space()='Converted Leads']/parent::div)/following-sibling::span");
    private By totalProjectsInProgress = By.xpath("(//span[normalize-space()='Projects In Progress']/parent::div)/following-sibling::span");
    private By totalTasksNotFinished = By.xpath("(//span[normalize-space()='Tasks Not Finished']/parent::div)/following-sibling::span");
    private By checkboxQuickStatistics = By.xpath("//input[@id='widget_option_top_stats']");

    public void checkTotalInvoicesAwaitingPayment(String total){
        softAssert.assertTrue(WebUI.isElementDisplayed(totalInvoicesAwaitingPayment), "\uD83D\uDC1E FAIL!!! totalInvoicesAwaitingPayment not displayed.");
        softAssert.assertEquals(WebUI.getElementText(totalInvoicesAwaitingPayment), total, "\uD83D\uDC1E FAIL!!! Total InvoicesAwaitingPayment not match.");
    }

    public void checkTotalConvertedLeads(String total){
        softAssert.assertTrue(WebUI.isElementDisplayed(totalConvertedLeads), "\uD83D\uDC1E FAIL!!! total ConvertedLeads not displayed.");
        softAssert.assertEquals(WebUI.getElementText(totalConvertedLeads), total, "\uD83D\uDC1E FAIL!!! Total ConvertedLeads not match.");
    }

    public void checkTotalProjectsInProgress(String total){
        softAssert.assertTrue(WebUI.isElementDisplayed(totalProjectsInProgress), "\uD83D\uDC1E FAIL!!! ProjectsInProgress not displayed.");
        softAssert.assertEquals(WebUI.getElementText(totalProjectsInProgress), total, "\uD83D\uDC1E FAIL!!! Total ProjectsInProgress not match.");
    }

    public void checkTotalTasksNotFinished(String total){
        softAssert.assertTrue(WebUI.isElementDisplayed(totalTasksNotFinished), "\uD83D\uDC1E FAIL!!! TasksNotFinished not displayed.");
        softAssert.assertEquals(WebUI.getElementText(totalTasksNotFinished), total, "\uD83D\uDC1E FAIL!!! Total TasksNotFinished not match.");
        softAssert.assertAll();
    }

    public void clickDashboardOptionButton(){
        WebUI.waitForElementClickable(buttonDashboardOptions);
        WebUI.clickElement(buttonDashboardOptions);

    }

    public void verifyQuickStatisticsSectionDisplayed(){
        WebUI.isElementDisplayed(checkboxQuickStatistics);
        Assert.assertTrue(WebUI.isSelectedElement(checkboxQuickStatistics),"\uD83D\uDC1E FAIL!!! Checkbox Quick Statistics not selected.");
        softAssert.assertTrue(WebUI.isElementDisplayed(totalInvoicesAwaitingPayment), "\uD83D\uDC1E FAIL!!! totalInvoicesAwaitingPayment not displayed.");
        softAssert.assertTrue(WebUI.isElementDisplayed(totalConvertedLeads), "\uD83D\uDC1E FAIL!!! total ConvertedLeads not displayed.");
        softAssert.assertTrue(WebUI.isElementDisplayed(totalProjectsInProgress), "\uD83D\uDC1E FAIL!!! ProjectsInProgress not displayed.");
        softAssert.assertTrue(WebUI.isElementDisplayed(totalTasksNotFinished), "\uD83D\uDC1E FAIL!!! TasksNotFinished not displayed.");
        softAssert.assertAll();
    }
}
