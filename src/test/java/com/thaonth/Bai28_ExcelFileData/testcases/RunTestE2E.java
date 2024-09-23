package com.thaonth.Bai28_ExcelFileData.testcases;

import com.thaonth.Bai28_ExcelFileData.pages.DashboardPage;
import com.thaonth.Bai28_ExcelFileData.pages.LoginPage;
import com.thaonth.common.BaseTestE2E;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RunTestE2E extends BaseTestE2E {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @Test (priority = 1)
    public void testLoginSuccess(){
        getLoginPage().loginCRM("admin@example.com", "123456");
        getLoginPage().verifyLoginSuccess();
    }

    @Test (priority = 2)
    public void testCheckSectionQuickStatisticsDisplayed(){
        getDashboardPage().clickDashboardOptionButton();
        getDashboardPage().verifyQuickStatisticsSectionDisplayed();
    }

    @Test(priority = 3)
    public void testAddNewCustomer(){
        String CUSTOMER_NAME = "23092024A5 Customer Name";
        getCustomerPage().clickMenuCustomers();
        int beforeTotalCustomers = Integer.parseInt(getCustomerPage().getTotalCustomers());
        System.out.println("\uD83C\uDF40 Total Customer before: " + beforeTotalCustomers);
        getCustomerPage().clickAddNewButton();
        getCustomerPage().inputDataInAddNewCustomerForm(CUSTOMER_NAME);
        getCustomerPage().clickSaveButton();
        int afterTotalCustomers = Integer.parseInt(getCustomerPage().getTotalCustomers());
        System.out.println("\uD83C\uDF40  Total Customer after: " + afterTotalCustomers);
        Assert.assertEquals(afterTotalCustomers, beforeTotalCustomers + 1, "\uD83D\uDC1E FAIL!!! Total Customer not match.");
        getCustomerPage().checkCustomerInTableList(CUSTOMER_NAME);
        getCustomerPage().checkCustomerDetail(CUSTOMER_NAME);
        getCustomerPage().clickMenuProjects();
        getProjectPage().clickAddNewProject();
        getProjectPage().checkCustomerDisplayInProjectForm(CUSTOMER_NAME);

    }
}
