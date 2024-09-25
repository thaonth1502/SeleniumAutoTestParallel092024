package com.thaonth.Bai31_TestListener.testcases;

import com.thaonth.Bai31_TestListener.pages.CustomerPage;
import com.thaonth.Bai31_TestListener.pages.DashboardPage;
import com.thaonth.Bai31_TestListener.pages.LoginPage;
import com.thaonth.Bai31_TestListener.pages.ProjectPage;
import com.thaonth.common.BaseTest;
import com.thaonth.dataproviders.DataProviderFactory;
import com.thaonth.helpers.ExcelHelper;
import com.thaonth.keywords.WebUI;
import com.thaonth.utils.LogUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

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
        LogUtils.info("\uD83C\uDF40 Total Customer before: " + beforeTotalCustomers);
        customerPage.clickAddNewButton();
        customerPage.inputDataInAddNewCustomerForm(data);
        customerPage.clickSaveButton();
        int afterTotalCustomers = Integer.parseInt(customerPage.getTotalCustomers());
        LogUtils.info("\uD83C\uDF40  Total Customer after: " + afterTotalCustomers);
        WebUI.assertEquals(afterTotalCustomers, beforeTotalCustomers + 1, "\uD83D\uDC1E FAIL!!! Total Customer not match.");
        customerPage.checkCustomerInTableList(data);
        customerPage.checkCustomerDetail(data);
        projectPage = customerPage.clickMenuProjects();
        projectPage.clickAddNewProject();
        projectPage.checkCustomerDisplayInProjectForm(data.get("CUSTOMER_NAME"));

    }

    @Test
    public void createNewGroupSuccess(){
        loginPage = new LoginPage();
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/dataTest/ExcelData.xlsx", "Login");

        dashboardPage = loginPage.loginCRM(
                excelHelper.getCellData("EMAIL", 1),
                excelHelper.getCellData("PASSWORD", 1)
        );
        customerPage = dashboardPage.clickMenuCustomers();
        customerPage.clickAddNewButton();
        excelHelper.setExcelFile("src/test/resources/dataTest/ExcelData.xlsx", "Group");
        customerPage.createNewGroup(excelHelper.getCellData("GROUP_NAME", 1));
        customerPage.verifyCreateNewGroupSuccessful("Customer Group added successfully.");
        customerPage.verifyGroupNameInCustomerForm(excelHelper.getCellData("GROUP_NAME", 1));
    }

    @Test
    public void createNewGroupFail(){
        loginPage = new LoginPage();
         ExcelHelper excelHelper = new ExcelHelper();
         excelHelper.setExcelFile("src/test/resources/dataTest/ExcelData.xlsx", "Login");

        dashboardPage = loginPage.loginCRM(
                excelHelper.getCellData("EMAIL", 1),
                excelHelper.getCellData("PASSWORD", 1)
        );
        customerPage = dashboardPage.clickMenuCustomers();
        customerPage.clickAddNewButton();
        excelHelper.setExcelFile("src/test/resources/dataTest/ExcelData.xlsx", "Group");
        customerPage.createNewGroup(excelHelper.getCellData("GROUP_NAME", 2));
        customerPage.verifyCreateNewGroupFail("This field is required.");
    }


}
