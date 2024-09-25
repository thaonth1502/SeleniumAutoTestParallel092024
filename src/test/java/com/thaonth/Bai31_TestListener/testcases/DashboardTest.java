package com.thaonth.Bai31_TestListener.testcases;

import com.thaonth.Bai31_TestListener.pages.DashboardPage;
import com.thaonth.Bai31_TestListener.pages.LoginPage;
import com.thaonth.common.BaseTest;
import com.thaonth.listeners.TestListener;
import org.testng.annotations.Listeners;
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
           dashboardPage.checkTotalTasksNotFinished("8 / 9");
    }

    @Test
    public void testCheckSectionQuickStatisticsDisplayed(){
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM("admin@example.com", "123456");

        dashboardPage.clickDashboardOptionButton();
        dashboardPage.verifyQuickStatisticsSectionDisplayed();
    }
}
