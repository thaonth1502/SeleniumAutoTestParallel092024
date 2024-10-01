package com.thaonth.common;

import com.thaonth.Bai26_PageObjectModel.pages.CommonPage;
import com.thaonth.drivers.DriverManager;

import com.thaonth.helpers.PropertiesHelper;
import com.thaonth.listeners.TestListener;
import com.thaonth.utils.LogUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

@Listeners({TestListener.class})
public class BaseTest extends CommonPage {

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
                LogUtils.info("Browser: " + browserName + " is invalid, Launching Chrome as browser of choice...");
                driver = initChromeDriver();
        }
        return driver;
    }

    private WebDriver initChromeDriver() {
        LogUtils.info("Launching Chrome browser...");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    private WebDriver initEdgeDriver() {
        LogUtils.info("Launching Edge browser...");
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    private WebDriver initFirefoxDriver() {
        LogUtils.info("Launching Firefox browser...");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        return driver;
    }

    @AfterMethod
    public void closeDriver() {
        DriverManager.quit();
    }
}
