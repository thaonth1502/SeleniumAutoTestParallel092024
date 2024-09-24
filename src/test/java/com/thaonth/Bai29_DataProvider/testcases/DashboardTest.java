package com.thaonth.Bai29_DataProvider.testcases;

import com.thaonth.Bai29_DataProvider.pages.DashboardPage;
import com.thaonth.Bai29_DataProvider.pages.LoginPage;
import com.thaonth.common.BaseTest;
import org.testng.annotations.Test;

public class DashboardTest extends BaseTest {

   private LoginPage loginPage;
   private DashboardPage dashboardPage;

    @Test
    public void testCheckQuickStatisticsSection(){
           loginPage = new LoginPage();
           dashboardPage = loginPage.loginCRM("admin@example.com", "123456");

           dashboardPage.checkTotalInvoicesAwaitingPayment("2 / 5");
           dashboardPage.checkTotalConvertedLeads("1 / 22");
           dashboardPage.checkTotalProjectsInProgress("0 / 6");
           dashboardPage.checkTotalTasksNotFinished("7 / 8");
    }

    @Test
    public void testCheckSectionQuickStatisticsDisplayed(){
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM("admin@example.com", "123456");

        dashboardPage.clickDashboardOptionButton();
        dashboardPage.verifyQuickStatisticsSectionDisplayed();
    }
}
