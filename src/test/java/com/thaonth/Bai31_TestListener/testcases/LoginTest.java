package com.thaonth.Bai31_TestListener.testcases;

import com.thaonth.Bai31_TestListener.pages.LoginPage;
import com.thaonth.common.BaseTest;
import com.thaonth.dataproviders.DataProviderFactory;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @Test (description = "Login Success", dataProvider = "data_provider_login", dataProviderClass = DataProviderFactory.class)
    public void testLoginSuccess(String email, String password){
        loginPage = new LoginPage();
        loginPage.loginCRM(email, password);
        loginPage.verifyLoginSuccess();
    }

    @Test (dataProvider = "data_provider_login_excel", dataProviderClass = DataProviderFactory.class)
    public void testLoginSuccessDataProviderFromExcel(String email, String password){
        loginPage = new LoginPage();
        loginPage.loginCRM(email, password);
        loginPage.verifyLoginSuccess();
    }

    @Test (dataProvider = "data_provider_login_excel_hashtable", dataProviderClass = DataProviderFactory.class)
    public void testLoginSuccessDataProviderFromExcelHashtable(Hashtable<String, String> data){
        loginPage = new LoginPage();
        loginPage.loginCRM(data.get("EMAIL"), data.get("PASSWORD"));
        loginPage.verifyLoginSuccess();
    }

    @Test (description = "Login Fail - Email Invalid")
    public void testLoginFailWithEmailInvalid(){
        loginPage = new LoginPage();
        loginPage.loginCRM("admin1@example.com", "123456");
        loginPage.verifyLoginFail("Invalid email or password");
    }

    @Test
    public void testLoginFailWithPasswordInvalid(){
        loginPage = new LoginPage();

        loginPage.loginCRM("admin@example.com", "1234567");
        loginPage.verifyLoginFail("Invalid email or password");
    }

    @Test
    public void testLoginFailWithPasswordBlank(){
        loginPage = new LoginPage();

        loginPage.loginCRM("admin@example.com", "");
        loginPage.verifyLoginFail("The Password field is required.");
    }

    @Test
    public void testLoginFailWithEmailBlank(){
        loginPage = new LoginPage();

        loginPage.loginCRM("", "12345678");
        loginPage.verifyLoginFail("The Email Address field is required.");
    }

}
