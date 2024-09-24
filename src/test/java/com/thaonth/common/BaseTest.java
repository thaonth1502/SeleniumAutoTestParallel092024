package com.thaonth.common;

import com.thaonth.drivers.DriverManager;
import com.thaonth.helpers.CaptureHelper;
import com.thaonth.helpers.PropertiesHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class BaseTest {

    @BeforeSuite
    public void setupEnvironment(){
        PropertiesHelper.loadAllFiles();
    }

    @BeforeMethod
    @Parameters({"browser"})
    public void createDriver(@Optional("chrome") String browser) {
        WebDriver driver;
        if (PropertiesHelper.getValue("BROWSER") != null){
             driver =  setupDriver(PropertiesHelper.getValue("BROWSER"));
        } else {
            driver = setupDriver(browser);
        }
        DriverManager.setDriver(driver);  //Gan gia tri driver vao trong ThreadLocal
    }

    public WebDriver setupDriver(String browserName) {
        WebDriver driver;

        switch (browserName.trim().toLowerCase()) {
            case "chrome":
                driver = initChromeDriver();
                break;
            case "firefox":
                driver = initFirefoxDriver();
                break;
            case "edge":
                driver = initEdgeDriver();
                break;
            default:
                System.out.println("\uD83C\uDF0E Browser: " + browserName + " is invalid, Launching Chrome as browser of choice...");
                driver = initChromeDriver();
        }
        return driver;
    }

    private WebDriver initChromeDriver() {
        System.out.println("\uD83C\uDF0E Launching Chrome browser...");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    private WebDriver initEdgeDriver() {
        System.out.println("\uD83C\uDF0E Launching Edge browser...");
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    private WebDriver initFirefoxDriver() {
        System.out.println("\uD83C\uDF0E Launching Firefox browser...");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        return driver;
    }

    @AfterMethod
    public void closeDriver(ITestResult iTestResult) {

        if (iTestResult.getStatus() == ITestResult.FAILURE){
            CaptureHelper.screenshot(iTestResult.getName());
        }
            DriverManager.quit();
    }
}
