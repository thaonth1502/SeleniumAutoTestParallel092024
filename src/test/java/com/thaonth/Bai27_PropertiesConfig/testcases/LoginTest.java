package com.thaonth.Bai27_PropertiesConfig.testcases;

import com.thaonth.Bai27_PropertiesConfig.pages.LoginPage;
import com.thaonth.common.BaseTest;
import com.thaonth.contants.DataConfig;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @Test

    public void testLoginSuccess(){
        loginPage = new LoginPage();
        loginPage.loginCRM("admin@example.com","123456");
        loginPage.verifyLoginSuccess();
    }

    @Test
    public void testLoginFailWithEmailInvalid(){
        loginPage = new LoginPage();
        loginPage.loginCRM("admin111@example.com", "123456");
        loginPage.verifyLoginFail("Invalid email or password");
    }

    @Test
    public void testLoginFailWithPasswordInvalid(){
        loginPage = new LoginPage();

        loginPage.loginCRM("admin@example.com", "12345678");
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
