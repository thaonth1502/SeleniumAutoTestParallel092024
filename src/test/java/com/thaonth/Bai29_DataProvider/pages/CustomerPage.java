package com.thaonth.Bai29_DataProvider.pages;

import com.thaonth.drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import java.util.Hashtable;

import static com.thaonth.keywords.WebUI.*;


public class CustomerPage extends CommonPage {


    //Elements
    private By headerCustomerPage = By.xpath("//span[normalize-space()='Customers Summary']");
    private By inputSearchBox = By.xpath("//div[@id='clients_filter']/descendant::input");
    private By buttonNewCustomer = By.xpath("//a[normalize-space()='New Customer']");
    private By firstItemCustomerName = By.xpath("//tbody/tr[1]/td[3]/a");
    private By inputCompany = By.xpath("//input[@id='company']");
    private By inputVAT = By.xpath("//input[@id='vat']");
    private By inputPhone = By.xpath("//input[@id='phonenumber']");
    private By inputWebsite = By.xpath("//input[@id='website']");
    private By dropdownGroups = By.xpath("//button[@data-id='groups_in[]']");
    private By searchGroup = By.xpath("//button[@data-id='groups_in[]']/following-sibling::div//input");
    private By dropdownCurrency = By.xpath("//button[@data-id='default_currency']");
    private By searchCurrency = By.xpath("//button[@data-id='default_currency']/following-sibling::div//input");
    private By dropdownDefaultLanguage = By.xpath("//button[@data-id='default_language']");
    private By inputAddress = By.xpath("//textarea[@id='address']");
    private By inputCity = By.xpath("//input[@id='city']");
    private By inputState = By.xpath("//input[@id='state']");
    private By inputZipCode = By.xpath("//input[@id='zip']");
    private By dropdownCountry = By.xpath("//button[@data-id='country']");
    private By searchCountry = By.xpath("//button[@data-id='country']/following-sibling::div//input");
    private By buttonSaveCustomer = By.xpath("//div[@id='profile-save-section']//button[normalize-space()='Save']");
    private By alterMessage = By.xpath("//span[@class='alert-title']");
    private By totalCustomers = By.xpath("//span[normalize-space()='Total Customers']/preceding-sibling::span");


    //Create Group
    private By buttonCreateGroup = By.xpath("//div[@class='input-group-btn']");
    private By inputNameGroup = By.xpath("//input[@id='name']");
    private By errorMessage = By.xpath("//p[@id='name-error']");
    private By buttonSaveGroup = By.xpath("//div[@id='customer_group_modal']//button[normalize-space()='Save']");




    //Hàm xử lý cho trang Customer
    //Create new Groups
    public String getTotalCustomers(){
        clickElement(menuCustomers);
        return getElementText(totalCustomers);
    }

    public void createNewGroup(String groupName){
        clickElement(buttonCreateGroup);
        DriverManager.getDriver().switchTo().activeElement();
        setText(inputNameGroup, groupName);
        clickElement(buttonSaveGroup);
        sleep(2);
    }

    public void verifyCreateNewGroupSuccessful(String message){
        Assert.assertTrue(checkElementExist(alterMessage), "\uD83D\uDC1E FAIL!!! Alert message not displayed.");
        assertEquals(getElementText(alterMessage), message, "\uD83D\uDC1E FAIL!!! The content message not match.");

    }
    public void verifyCreateNewGroupFail(String message){
        Assert.assertTrue(checkElementExist(errorMessage), "\uD83D\uDC1E FAIL!!! Error message not displayed.");
        assertEquals(getElementText(errorMessage), message, "\uD83D\uDC1E FAIL!!! The content error message not match.");
    }

    public void verifyGroupNameInCustomerForm(String groupName){
        selectGroup(groupName);
        assertEquals(getElementAttribute(dropdownGroups,"title"), groupName, "FAIL!!! The Groups not match.");
    }

    //Click button Save in Create new Customer form
    public void clickSaveButton(){
        clickElement(buttonSaveCustomer);
        sleep(2);
//        Assert.assertTrue(WebUI.getWebElement(alterMessage).isDisplayed(),"\uD83D\uDC1E FAIL!!! The alter Message not display.");
        Assert.assertTrue(isElementDisplayed(alterMessage), "FAIL!!! The alter Message not display.");
        assertEquals(getElementText(alterMessage).trim(), "Customer added successfully.", "FAIL!!! The content message not match.");
    }

    public void clickAddNewButton(){
        clickElement(buttonNewCustomer);

    }

    public void inputDataInAddNewCustomerForm(Hashtable<String, String> data){
        setText(inputCompany, data.get("CUSTOMER_NAME"));
        setText(inputVAT, data.get("VAT"));
        setText(inputPhone, data.get("PHONE"));
        setText(inputWebsite, data.get("WEBSITE"));
        selectGroup(data.get("GROUP"));
        selectCurrency(data.get("CURRENCY"));
        selectLanguage(data.get("LANGUAGE"));
        sleep(1);
        setText(inputAddress, data.get("ADDRESS"));
        setText(inputCity, data.get("CITY"));
        setText(inputState, data.get("STATE"));
        setText(inputZipCode, data.get("ZIP_CODE"));
        selectCountry(data.get("COUNTRY"));
    }

    public void selectCurrency(String currency){
        clickElement(dropdownCurrency);
        sleep(1);
        setText(searchCurrency, currency);
        sleep(1);
        setKeys(searchCurrency, Keys.ENTER);
        sleep(1);
    }

    public void selectGroup(String groupName){
        clickElement(dropdownGroups);
        setTextAndKey(searchGroup,groupName,Keys.ENTER);
        sleep(1);
        clickElement(dropdownGroups);
    }

    public void selectLanguage(String languageName){
        clickElement(dropdownDefaultLanguage);
        clickElement(By.xpath("//span[normalize-space()='"+languageName+"']"));
    }

    public void selectCountry(String countryName){
        clickElement(dropdownCountry);
        setTextAndKey(searchCountry,countryName,Keys.ENTER);
    }
    public void checkCustomerInTableList(Hashtable<String, String> data){
        clickElement(menuCustomers);
        setText(inputSearchBox, data.get("CUSTOMER_NAME"));
        sleep(2);
        Assert.assertTrue(checkElementExist(firstItemCustomerName), "FAIL!!! The customer name not display in table");
        assertEquals(getElementText(firstItemCustomerName), data.get("CUSTOMER_NAME"), "FAIL!!! The customer not match");
    }
    public void checkCustomerDetail(Hashtable<String, String> data){
        clickElement(firstItemCustomerName);
        //Check content customer detail

        assertEquals(getElementAttribute(inputCompany,"value"), data.get("CUSTOMER_NAME"), "\uD83D\uDC1E FAIL!!! The customer Name not match.");
        assertEquals(getElementAttribute(inputVAT,"value"), data.get("VAT"), "\uD83D\uDC1E FAIL!!! The VAT not match.");
        assertEquals(getElementAttribute(inputPhone,"value"), data.get("PHONE"), "\uD83D\uDC1E FAIL!!! The Phone not match.");
        assertEquals(getElementAttribute(inputWebsite,"value"), data.get("WEBSITE"), "\uD83D\uDC1E FAIL!!! The Website not match.");
        assertEquals(getElementAttribute(dropdownGroups,"title"), data.get("GROUP"), "\uD83D\uDC1E FAIL!!! The Groups not match.");
        assertEquals(getElementAttribute(dropdownCurrency,"title"), data.get("CURRENCY"), "\uD83D\uDC1E FAIL!!! The Currency not match.");
        assertEquals(getElementAttribute(dropdownDefaultLanguage,"title"), data.get("LANGUAGE"), "\uD83D\uDC1E FAIL!!! The Language not match.");
        assertEquals(getElementAttribute(inputAddress,"value"), data.get("ADDRESS"), "\uD83D\uDC1E FAIL!!! The Address not match.");
        assertEquals(getElementAttribute(inputCity,"value"), data.get("CITY"), "\uD83D\uDC1E FAIL!!! The City not match.");
        assertEquals(getElementAttribute(inputState,"value"), data.get("STATE"), "\uD83D\uDC1E FAIL!!! The State not match.");
        assertEquals(getElementAttribute(inputZipCode,"value"), data.get("ZIP_CODE"), "\uD83D\uDC1E FAIL!!! The Zip Code not match.");
        assertEquals(getElementAttribute(dropdownCountry,"title"), data.get("COUNTRY"), "\uD83D\uDC1E FAIL!!! The Country not match.");
    }
}
