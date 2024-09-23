package com.thaonth.Bai28_ExcelFileData.testcases;

import com.thaonth.Bai28_ExcelFileData.pages.LoginPage;
import com.thaonth.common.BaseTest;
import com.thaonth.helpers.ExcelHelper;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @Test
    public void testLoginSuccess(){
        loginPage = new LoginPage();

        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/dataTest/Login.xlsx", "Login");

        for (int i = 1; i <= 4; i++) {
            loginPage.loginCRM(
                    excelHelper.getCellData("EMAIL", i),
                    excelHelper.getCellData("PASSWORD", i)
            );
            loginPage.verifyLoginSuccess();
            excelHelper.setCellData("PASSED", "RESULT", i);
            loginPage.logout();
        }
    }

    @Test
    public void testLoginFailWithEmailInvalid(){
        loginPage = new LoginPage();
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/dataTest/Login.xlsx", "Sheet1");

        loginPage.loginCRM(
                excelHelper.getCellData("EMAIL", 2),
                excelHelper.getCellData("PASSWORD", 2)
        );
        loginPage.verifyLoginFail("Invalid email or password");
        excelHelper.setCellData("PASSED", "RESULT", 2);
    }

    @Test
    public void testLoginFailWithPasswordInvalid(){
        loginPage = new LoginPage();

        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/dataTest/Login.xlsx", "Sheet1");

        loginPage.loginCRM(
                excelHelper.getCellData("EMAIL", 3),
                excelHelper.getCellData("PASSWORD", 3)
        );
        loginPage.verifyLoginFail("Invalid email or password");
        excelHelper.setCellData("PASSED", "RESULT", 3);
    }

    @Test
    public void testLoginFailWithEmailBlank(){
        loginPage = new LoginPage();

        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/dataTest/Login.xlsx", "Sheet1");

        loginPage.loginCRM(
                excelHelper.getCellData("EMAIL", 4),
                excelHelper.getCellData("PASSWORD", 4)
        );
        loginPage.verifyLoginFail("The Email Address field is required.");
        excelHelper.setCellData("PASSED", "RESULT", 4);
    }

    @Test
    public void testLoginFailWithPasswordBlank(){
        loginPage = new LoginPage();

        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/dataTest/Login.xlsx", "Sheet1");

        loginPage.loginCRM(
                excelHelper.getCellData("EMAIL", 5),
                excelHelper.getCellData("PASSWORD", 5)
        );
        loginPage.verifyLoginFail("The Password field is required.");
        excelHelper.setCellData("PASSED", "RESULT", 5);
    }
}
