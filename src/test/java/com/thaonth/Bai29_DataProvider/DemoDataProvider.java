package com.thaonth.Bai29_DataProvider;

import com.thaonth.dataproviders.DataProviderFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DemoDataProvider {

    @Test(dataProvider = "data_provider_01", dataProviderClass = DataProviderFactory.class)
    public void testDataProvider01(String value){
        System.out.println("Test: " + value);
    }

    @Test(dataProvider = "data_provider_02" , dataProviderClass = DataProviderFactory.class)
    public void testDataProvider02(String a, String b, String c){
        System.out.println("Parameter 1: " + a);
        System.out.println("Parameter 2: " + b);
        System.out.println("Parameter 3: " + c);
    }

    @Test(dataProvider = "data_provider_03", dataProviderClass = DataProviderFactory.class)
    public void testDataProvider03(String name, String phone, String vat, String address){
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phone);
        System.out.println("VAT: " + vat);
        System.out.println("Address: " + address);
    }
}
