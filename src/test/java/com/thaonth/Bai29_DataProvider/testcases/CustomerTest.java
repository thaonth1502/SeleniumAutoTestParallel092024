package com.thaonth.Bai29_DataProvider.testcases;

import com.thaonth.Bai29_DataProvider.pages.CustomerPage;
import com.thaonth.Bai29_DataProvider.pages.DashboardPage;
import com.thaonth.Bai29_DataProvider.pages.LoginPage;
import com.thaonth.Bai29_DataProvider.pages.ProjectPage;
import com.thaonth.common.BaseTest;
import com.thaonth.dataproviders.DataProviderFactory;
import com.thaonth.helpers.ExcelHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Executable;
import java.util.Hashtable;

public class CustomerTest extends BaseTest {
   private LoginPage loginPage;
   private DashboardPage dashboardPage;
   private CustomerPage customerPage;
   private ProjectPage projectPage;

    @Test (dataProvider = "data_provider_customer_excel_hashtable", dataProviderClass = DataProviderFactory.class)
    public void testAddNewCustomer(Hashtable<String, String> data){
        loginPage = new LoginPage();

        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/dataTest/ExcelData.xlsx", "Login");

        dashboardPage = loginPage.loginCRM(
                excelHelper.getCellData("EMAIL", 1),
                excelHelper.getCellData("PASSWORD", 1)
        );
        customerPage = dashboardPage.clickMenuCustomers();
        int beforeTotalCustomers = Integer.parseInt(customerPage.getTotalCustomers());
        System.out.println("Total Customer before: " + beforeTotalCustomers);
        customerPage.clickAddNewButton();
        customerPage.inputDataInAddNewCustomerForm(data);
        customerPage.clickSaveButton();
        int afterTotalCustomers = Integer.parseInt(customerPage.getTotalCustomers());
        System.out.println("Total Customer after: " + afterTotalCustomers);
        Assert.assertEquals(afterTotalCustomers, beforeTotalCustomers + 1, "FAIL!!! Total Customer not match.");
        customerPage.checkCustomerInTableList(data);
        customerPage.checkCustomerDetail(data);
        projectPage = customerPage.clickMenuProjects();
        projectPage.clickAddNewProject();
        projectPage.checkCustomerDisplayInProjectForm(data.get("CUSTOMER_NAME"));

    }
/*
    @Test
    public void createNewGroupSuccess(){
        loginPage = new LoginPage();
        String GROUP_NAME = "TEST A02";
        dashboardPage = loginPage.loginCRM("admin@example.com", "123456");
        customerPage = dashboardPage.clickMenuCustomers();
        customerPage.clickAddNewButton();
        customerPage.createNewGroup(GROUP_NAME);
        customerPage.verifyCreateNewGroupSuccessful("Customer Group added successfully.");
        customerPage.verifyGroupNameInCustomerForm(GROUP_NAME);
    }

    @Test
    public void createNewGroupFail(){
        loginPage = new LoginPage();
        String GROUP_NAME = "";
        dashboardPage = loginPage.loginCRM("admin@example.com", "123456");
        customerPage = dashboardPage.clickMenuCustomers();
        customerPage.clickAddNewButton();
        customerPage.createNewGroup(GROUP_NAME);
        customerPage.verifyCreateNewGroupFail("This field is required.");
    }

 */
}
