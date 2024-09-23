package com.thaonth.Bai28_ExcelFileData;

import com.thaonth.helpers.ExcelHelper;
import org.testng.annotations.Test;

public class DemoExcelFile {
    @Test
    public void getDataFromExcel(){
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/dataTest/Login.xlsx", "Sheet1");

        //Get simple data
//        System.out.println(excelHelper.getCellData("EMAIL", 1));
//        System.out.println(excelHelper.getCellData("PASSWORD", 1));

        //Get multi data
        for (int i = 1; i <= 5; i++){
            System.out.println(excelHelper.getCellData("EMAIL", i));
            System.out.println(excelHelper.getCellData("PASSWORD", i));
        }

        System.out.println("**********************");
        //Set data to excel RESULT
        excelHelper.setCellData("PASSED", "RESULT", 1);
        excelHelper.setCellData("FAILED", "RESULT", 2);
    }
}
