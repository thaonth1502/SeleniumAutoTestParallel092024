package com.thaonth.Handle_Table;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCreateDriver {

    WebDriver driver;
    @BeforeMethod
    public void createDriver(){
        driver = new ChromeDriver();
        driver.get("https://anhtester.com/");
        driver.manage().window().maximize();
    }
    @Test
    public void getCurrentULR(){
       String currentURL =  driver.getCurrentUrl();
        System.out.println(currentURL);
    }


    @AfterMethod
    public void closeDriver(){
        driver.quit();
    }


}
