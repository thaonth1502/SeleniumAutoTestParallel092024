package com.thaonth.keywords;

import com.aventstack.extentreports.Status;
import com.thaonth.contants.DataConfig;
import com.thaonth.drivers.DriverManager;
import com.thaonth.helpers.CaptureHelper;
import com.thaonth.helpers.PropertiesHelper;
import com.thaonth.helpers.SystemHelper;
import com.thaonth.reports.AllureManager;
import com.thaonth.reports.ExtentTestManager;
import com.thaonth.utils.LogUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

public class WebUI {

    private static int EXPLICIT_WAIT_TIMEOUT = DataConfig.EXPLICIT_WAIT_TIMEOUT;
    private static double STEP_TIME = DataConfig.STEP_TIME;
    private static int PAGE_LOAD_TIMEOUT = DataConfig.PAGE_LOAD_TIMEOUT;


    @Step("Check Data \"{1}\" in table by Colum {2}")
    public static void checkDataInTableByColumn_Contains (int column, String value, String columnName) {

        LogUtils.info("Check data: " + value + " in table by column " + columnName);
        ExtentTestManager.logMessage(Status.INFO,"Check data: " + value + " in table by column " + columnName);
        //Xác định số dòng của table sau khi search
        List<WebElement> row = WebUI.getListWebElement(By.xpath("//table//tbody/tr"));
        int rowTotal = row.size(); //Lấy ra số dòng
        LogUtils.info("Rows are found: " + rowTotal);

        //Duyệt từng dòng
        for (int i = 1; i <= rowTotal; i++) {
            WebElement elementCheck = WebUI.getWebElement(By.xpath("//table//tbody/tr[" + i + "]/td[" + column + "]"));

            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
            js.executeScript("arguments[0].scrollIntoView(true);", elementCheck);

            LogUtils.info(value + " - ");
            LogUtils.info(elementCheck.getText());
            Assert.assertTrue(SystemHelper.removeSpecialCharacter(elementCheck.getText()).toUpperCase().contains(SystemHelper.removeSpecialCharacter(value).toUpperCase()), "Dòng số " + i + " không chứa giá trị tìm kiếm.");
        }
    }

    @Step("Check Data \"{1}\" in table by Colum {2}")
    public static void checkDataInTableByColumn_Equals (int column, String value, String columnName) {

        LogUtils.info("Check data: " + value + " in table by column " + columnName);
        ExtentTestManager.logMessage(Status.INFO,"Check data: " + value + " in table by column " + columnName);
        //Xác định số dòng của table sau khi search
        List<WebElement> row = WebUI.getListWebElement(By.xpath("//table//tbody/tr"));
        int rowTotal = row.size(); //Lấy ra số dòng
        System.out.println("Số dòng tìm thấy: " + rowTotal);

        //Duyệt từng dòng
        for (int i = 1; i <= rowTotal; i++) {
            WebElement elementCheck = WebUI.getWebElement(By.xpath("//table//tbody/tr[" + i + "]/td[" + column + "]"));

            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
            js.executeScript("arguments[0].scrollIntoView(true);", elementCheck);

            LogUtils.info(value + " - ");
            LogUtils.info (elementCheck.getText());
            Assert.assertTrue(elementCheck.getText().toUpperCase().equals(value.toUpperCase()), "Dòng số " + i + " không chứa giá trị tìm kiếm.");
        }
    }

    //Upload file
    public static void uploadFileWithRobotClass(By elementFileForm, String filePath ){
        WebUI.clickElement(elementFileForm);
        WebUI.sleep(2);

        // Khởi tạo Robot class
        Robot rb = null;
        try {
            rb = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        // Copy File path vào Clipboard
        StringSelection str = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
        WebUI.sleep(1);

        // Nhấn Control+V để dán
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);

        // Xác nhận Control V trên
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);
        WebUI.sleep(1);

        // Nhấn Enter
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
        WebUI.sleep(4);
    }

    //Assert and Verify
    public static boolean verifyEquals(Object actual, Object expected) {
        waitForPageLoaded();
        LogUtils.info("Verify equals: " + actual + " \uD83D\uDFF0 " + expected);
        ExtentTestManager.logMessage(Status.PASS,"Verify equals: " + actual + " \uD83D\uDFF0 " + expected );
        boolean check = actual.equals(expected);
        return check;
    }

    public static void assertEquals(Object actual, Object expected, String message) {
        waitForPageLoaded();
        LogUtils.info("Assert equals: " + actual + " \uD83D\uDFF0 " + expected);
        ExtentTestManager.logMessage(Status.PASS,"Assert equals: " + actual + " \uD83D\uDFF0 " + expected);
        Assert.assertEquals(actual, expected, message);
    }

    public static boolean verifyContains(String actual, String expected) {
        waitForPageLoaded();
        LogUtils.info("Verify contains: " + actual + " and " + expected);
        ExtentTestManager.logMessage(Status.PASS,"Verify contains: " + actual + " and " + expected);
        boolean check = actual.contains(expected);
        return check;
    }

    public static void assertContains(String actual, String expected, String message) {
        waitForPageLoaded();
        LogUtils.info("Assert contains: " + actual + " and " + expected);
        ExtentTestManager.logMessage(Status.PASS,"Assert contains: " + actual + " and " + expected);
        boolean check = actual.contains(expected);
        Assert.assertTrue(check, message);
    }


    //Wait for Element

    public static void waitForElementVisible(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(DataConfig.EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            LogUtils.error("Timeout waiting for the element Visible. " + by.toString());
            Assert.fail("Timeout waiting for the element Visible. " + by.toString());
        }
    }

    public static void waitForElementVisible(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            LogUtils.error("Timeout waiting for the element Visible. " + by.toString());
            Assert.fail("Timeout waiting for the element Visible. " + by.toString());
        }
    }

    public static void waitForElementPresent(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(DataConfig.EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            LogUtils.error("Element not exist. " + by.toString());
            Assert.fail("Element not exist. " + by.toString());
        }
    }

    public static void waitForElementPresent(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            LogUtils.error("Element not exist. " + by.toString());
            Assert.fail("Element not exist. " + by.toString());
        }
    }

    public static void waitForElementToBeClickable(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(DataConfig.EXPLICIT_WAIT_TIMEOUT));
            wait.until(ExpectedConditions.elementToBeClickable(getWebElement(by)));
        } catch (Throwable error){
            LogUtils.error("Timeout waiting for the element ready to click. " + by.toString());
            Assert.fail("Timeout waiting for the element ready to click. " + by.toString());
        }
    }

    public static void waitForElementToBeClickable(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(getWebElement(by)));
        } catch (Throwable error){
            LogUtils.error("Timeout waiting for the element ready to click. " + by.toString());
            Assert.fail("Timeout waiting for the element ready to click. " + by.toString());
        }
    }

    public static void waitForElementToBeSelected(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(DataConfig.EXPLICIT_WAIT_TIMEOUT));
            wait.until(ExpectedConditions.elementToBeSelected(getWebElement(by)));
        } catch (Throwable error){
            LogUtils.error("Timeout waiting for the element ready to click. " + by.toString());
            Assert.fail("Timeout waiting for the element ready to click. " + by.toString());
        }
    }

    public static void waitForElementSelected(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut));
            wait.until(ExpectedConditions.elementToBeSelected(getWebElement(by)));
        } catch (Throwable error){
            LogUtils.error("Timeout waiting for the element ready to selected. " + by.toString());
            Assert.fail("Timeout waiting for the element ready to selected. " + by.toString());
        }
    }

    public static void waitForAlertIsPresent() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
    }



    //Các hàm xử lý cơ bản
    public static void sleep(double second) {
        try {
            Thread.sleep((long)(1000 * second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void logConsole(Object message) {
        LogUtils.info(message);
    }

    public static WebElement getWebElement(By by) {
        return DriverManager.getDriver().findElement(by);
    }
    public static List<WebElement> getListWebElement(By by){
        return DriverManager.getDriver().findElements(by);
    }

    public static Boolean checkElementExist(By by) {
        List< WebElement> listElement = DriverManager.getDriver().findElements(by);

        if (listElement.size() > 0) {
            LogUtils.info("Element " + by + " existing");
            ExtentTestManager.logMessage(Status.INFO,"Element" + by + " existing");
            return true;
        } else {
            LogUtils.error("Element  " + by + "NOT exist");
            ExtentTestManager.logMessage(Status.INFO,"Element  " + by + " NOT exist");
            return false;
        }
    }

    @Step(" Open URL \"{0}\"")
    public static void openURL(String url) {
        DriverManager.getDriver().get(url);
        sleep(DataConfig.STEP_TIME);
        LogUtils.info("Open URL: " + url);
        ExtentTestManager.logMessage(Status.PASS, "Open: " + url);
        if (PropertiesHelper.getValue("SCREENSHOT_STEP_ALL").equals("true")){
            CaptureHelper.screenshot(SystemHelper.makeSlug("openURL_" + url));
            ExtentTestManager.addScreenshot(SystemHelper.makeSlug("openURL_" + url));
            AllureManager.saveScreenshotPNG();

        }
    }

    @Step("Click element \"{0}\"")
    public static void clickElement(By by) {
        waitForElementToBeClickable(by);
        sleep(DataConfig.STEP_TIME);
        getWebElement(by).click();
        LogUtils.info("Click element: " + by);
        ExtentTestManager.logMessage(Status.PASS, "Click element: " + by);
        if(PropertiesHelper.getValue("SCREENSHOT_STEP_ALL").equals("true")){
            CaptureHelper.screenshot(SystemHelper.makeSlug("clickElement_" + by.toString()));
            ExtentTestManager.addScreenshot(SystemHelper.makeSlug("clickElement_" + by.toString()));
            AllureManager.saveScreenshotPNG();
        }
    }

    public static boolean isSelectedElement(By by){
        waitForElementToBeSelected(by);
        return getWebElement(by).isSelected();
    }

    @Step("Set text \"{1}\" on element \"{0}\"")
    public static void setText(By by, String value) {
        waitForElementPresent(by);
        sleep(DataConfig.STEP_TIME);
        getWebElement(by).sendKeys(value);
        LogUtils.info("Set text: " + value + " on element " + by);
        ExtentTestManager.logMessage(Status.PASS, "Set text: " + value + " on element " + by);
        if(PropertiesHelper.getValue("SCREENSHOT_STEP_ALL").equals("true")){
            CaptureHelper.screenshot(SystemHelper.makeSlug("setText_" + by.toString()));
            ExtentTestManager.addScreenshot(SystemHelper.makeSlug("setText_" + by.toString()));
            AllureManager.saveScreenshotPNG();
        }
    }

    @Step("Set key \"{1}\" on element \"{0}\"")
    public static void setKeys(By by, Keys key){
        getWebElement(by).sendKeys(key);
        LogUtils.info("Set Keys: " + key + " on element " + by);
        ExtentTestManager.logMessage(Status.PASS, "Set Keys: " + key + " on element " + by);

        if(PropertiesHelper.getValue("SCREENSHOT_STEP_ALL").equals("true")){
            CaptureHelper.screenshot(SystemHelper.makeSlug("setText_" + by.toString()));
            ExtentTestManager.addScreenshot(SystemHelper.makeSlug("setKeys_" + by.toString()));
            AllureManager.saveScreenshotPNG();
        }
    }

    @Step("Set text \"{1}\" and key \"{2}\" on element \"{0}\"")
    public static void setTextAndKey(By by, String value, Keys key) {
        waitForPageLoaded();
        getWebElement(by).sendKeys(value, key);
        LogUtils.info("Set text: " + value + " Set Keys: " + key + " on element " + by);
        ExtentTestManager.logMessage(Status.PASS, "Set text: " + value + " Set Keys: " + key + " on element " + by);

        if(PropertiesHelper.getValue("SCREENSHOT_STEP_ALL").equals("true")){
            CaptureHelper.screenshot(SystemHelper.makeSlug("setTextAndKey_" + by.toString()));
            ExtentTestManager.addScreenshot(SystemHelper.makeSlug("setTextAndKey_" + by.toString()));
            AllureManager.saveScreenshotPNG();
        }
    }

    public static void clearElementText(By by){
        waitForPageLoaded();
        getWebElement(by).clear();
        LogUtils.info("Clear text on element " + by);
    }

    @Step("Get text of element \"{0}\"")
    public static String getElementText(By by) {
        waitForElementVisible(by);
        sleep(DataConfig.STEP_TIME);
        String text = getWebElement(by).getText();
        LogUtils.info("Get text: " + text);
        ExtentTestManager.logMessage(Status.PASS, "Get text: " + text);
        AllureManager.saveTextLog("Get text: " + text);
        AllureManager.saveScreenshotPNG();
        return text; //Trả về một giá trị kiểu String
    }

    @Step("Get Attribute value of element \"{0}\"")
    public static String getElementAttribute(By by, String attributeName){
        waitForElementVisible(by);
        sleep(DataConfig.STEP_TIME);
        String text = getWebElement(by).getAttribute(attributeName);
        LogUtils.info("Attribute value: " + text);
        ExtentTestManager.logMessage(Status.PASS, "Attribute value: " + text);
        AllureManager.saveTextLog("Attribute value: " + text);
        AllureManager.saveScreenshotPNG();
        return text;
    }

    public static boolean isElementDisplayed(By by){
        waitForPageLoaded();
        sleep(DataConfig.STEP_TIME);
        return getWebElement(by).isDisplayed();
    }

    public static String getCurrentURL(){
        waitForPageLoaded();
        sleep(DataConfig.STEP_TIME);
        return DriverManager.getDriver().getCurrentUrl();
    }


    public static void scrollToElement(By element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", getWebElement(element));
    }

    /**
     * Scroll to position element
     * @param element
     * @param position if value is 'true' then scroll top. If value is 'fail' then scroll bottom
     */
    public static void scrollToElement(By element, String position) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView("+ position +");", getWebElement(element));
    }

    public static void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void scrollToPosition(int X, int Y) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("window.scrollTo(" + X + "," + Y + ");");
    }

    public static boolean moveToElement(By toElement) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveToElement(getWebElement(toElement)).perform();
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    public static boolean moveToOffset(int X, int Y) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveByOffset(X, Y).build().perform();
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    public static boolean hoverElement(By by) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveToElement(getWebElement(by)).perform();
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    public static boolean mouseHover(By by) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveToElement(getWebElement(by)).perform();
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    public static boolean dragAndDrop(By fromElement, By toElement) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.dragAndDrop(getWebElement(fromElement), getWebElement(toElement)).perform();
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    public static boolean dragAndDropElement(By fromElement, By toElement) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.clickAndHold(getWebElement(fromElement)).moveToElement(getWebElement(toElement)).release(getWebElement(toElement)).build().perform();
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    public static boolean dragAndDropOffset(By fromElement, int X, int Y) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            //Tính từ vị trí click chuột đầu tiên (clickAndHold)
            action.clickAndHold(getWebElement(fromElement)).pause(1).moveByOffset(X, Y).release().build().perform();
            return true;
        } catch (Exception e) {
            LogUtils.info(e.getMessage());
            return false;
        }
    }

    public static boolean pressENTER() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    public static boolean pressESC() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.keyRelease(KeyEvent.VK_ESCAPE);
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    public static boolean pressF11() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_F11);
            robot.keyRelease(KeyEvent.VK_F11);
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    /**
     * @param by truyền vào đối tượng element dạng By
     * @return Tô màu viền đỏ cho Element trên website
     */
    public static WebElement highLightElement(By by) {
        // Tô màu border ngoài chính element chỉ định - màu đỏ (có thể đổi màu khác)
        if (DriverManager.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].style.border='3px solid red'", getWebElement(by));
            sleep(1);
        }
        return getWebElement(by);
    }

    public static WebElement highLightElement(By by, String colorName) {
        // Tô màu border ngoài chính element chỉ định - màu đỏ (có thể đổi màu khác)
        if (DriverManager.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].style.border='3px solid "+ colorName +"'", getWebElement(by));
            sleep(1);
        }
        return getWebElement(by);
    }

    //Chờ đợi trang load xong mới thao tác
    //Waiting page load finish then take action
    public static void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(DataConfig.PAGE_LOAD_TIMEOUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        //Wait for Javascript to load
        ExpectedCondition < Boolean > jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        //Check JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            System.out.println("Javascript is NOT Ready.");
            //Wait for Javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                LogUtils.error("FAILED!!! Timeout waiting for page load.");
                Assert.fail("FAILED!!! Timeout waiting for page load.");
            }
        }
    }

    public static void waitForPageLoaded(int timeout) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        //Wait for Javascript to load
        ExpectedCondition < Boolean > jsLoad = new ExpectedCondition< Boolean >() {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        //Check JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            System.out.println("Javascript is NOT Ready.");
            //Wait for Javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                LogUtils.error("FAILED!!! Timeout waiting for page load.");
                Assert.fail("FAILED!!! Timeout waiting for page load.");
            }
        }
    }
}
