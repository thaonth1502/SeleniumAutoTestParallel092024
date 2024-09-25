package com.thaonth.dataproviders;

import com.thaonth.helpers.ExcelHelper;
import com.thaonth.helpers.SystemHelper;
import com.thaonth.utils.LogUtils;
import org.testng.annotations.DataProvider;

public class DataProviderFactory {
    @DataProvider(name = "data_provider_01")
    public Object[][] dataProvider01(){
        return new Object[][]{{"First-Value"}, {"Second-Value"}};
    }

    @DataProvider (name = "data_provider_02")
    public Object[][] dataProvider02(){
        return new  Object[][]{{"Value1", "Value2", "Value3"}, {"Value4", "Value5", "Value6"}};
    }

    @DataProvider (name = "data_provider_03", parallel = true)
    public Object[][] dataProvider03(){
        return new  Object[][]{
                {"Viettel", "1234567", "10%", "Ha Noi"},
                {"VNPT", "456789", "5%", "HCM"}
        };
    }

    @DataProvider(name = "data_provider_login", parallel = true)
    public Object[][] dataProviderLogin(){
        return new Object[][]{
                {"admin@example.com", "123456"},
                {"admin@example.com", "123456"}
        };
    }

    @DataProvider(name = "data_provider_login_excel", parallel = true)
    public Object[][] dataProviderLoginFromExcel() {
        ExcelHelper excelHelper = new ExcelHelper();
        Object[][] data = excelHelper.getExcelData(SystemHelper.getCurrentDir() + "src/test/resources/dataTest/ExcelData.xlsx", "Login");
        LogUtils.info("Login Data from Excel: " + data);
        return data;
    }

    @DataProvider(name = "data_provider_login_excel_hashtable")
    public Object[][] dataProviderLoginFromExcelHashtable() {
        ExcelHelper excelHelper = new ExcelHelper();
        Object[][] data = excelHelper.getDataHashTable(SystemHelper.getCurrentDir() + "src/test/resources/dataTest/ExcelData.xlsx", "Login", 2, 4);
        LogUtils.info("Login Data from Excel (Hashtable): " + data);
        return data;
    }

    @DataProvider(name = "data_provider_customer_excel_hashtable")
    public Object[][] dataProviderCustomerFromExcelHashtable() {
        ExcelHelper excelHelper = new ExcelHelper();
        Object[][] data = excelHelper.getDataHashTable(SystemHelper.getCurrentDir() + "src/test/resources/dataTest/ExcelData.xlsx", "Customer", 1, 2);
        System.out.println("Login Data from Excel (Hashtable) - Customer: " + data);
        return data;
    }

}
