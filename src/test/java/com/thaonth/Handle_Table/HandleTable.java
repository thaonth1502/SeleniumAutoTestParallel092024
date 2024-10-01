package com.thaonth.Handle_Table;

import com.thaonth.common.BaseTest;
import com.thaonth.drivers.DriverManager;
import com.thaonth.keywords.WebUI;
import com.thaonth.utils.LogUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.thaonth.keywords.WebUI.sleep;
import static com.thaonth.keywords.WebUI.waitForPageLoaded;

public class HandleTable extends BaseTest {
    @Test
    public void testSearchDataInTable(){
        getLoginPage().loginCRM("admin@example.com", "123456");
        getDashboardPage().clickMenuCustomers();
        getCustomerPage().searchAndCheckDataInTable(1, "Tester", "Company");
    }

    @Test
    public void testCheckPaginationOnTable() {

        //Data search read from Properties
        String searchValue ="CustomerName";
//       WebElement dropdown =  DriverManager.getDriver().findElement(By.name("clients_length"));
//        //Get item on One Page
//        Select select = new Select(dropdown);
//        select.selectByVisibleText("10");
//        sleep(1);


        Select select2 = new Select(WebUI.getWebElement(By.xpath("//select[@name='clients_length']")));
        LogUtils.info(select2.getFirstSelectedOption().getText());

        int itemTotalOnePage = Integer.parseInt(select2.getFirstSelectedOption().getText());
        System.out.println("Tổng số item / trang: " + itemTotalOnePage);

        //Set Text on Search input
        getCustomerPage().searchDataCustomer(searchValue);
        waitForPageLoaded();
        sleep(2);

        //Get total item
        String strTotal = WebUI.getElementText(By.xpath("//div[@id='clients_info']"));
        ArrayList<String> list = new ArrayList<String>();

        //Tách chuỗi theo khoảng trắng, sau đó cho vào ArrayList
        for (String strItem : strTotal.split("\\s")) {
            list.add(strItem);
        }

        System.out.println(list);

        // Lấy phần tử thứ 6

        int itemTotal = Integer.parseInt(list.get(5));
        System.out.println("Tổng số item: " + itemTotal);
        int pageTotal = itemTotal / itemTotalOnePage;
        int sodu = itemTotal % itemTotalOnePage;
        System.out.println("Tổng số nguyên: " + pageTotal);
        System.out.println("Tổng số dư: " + sodu);

        if (sodu > 0) {
            pageTotal = pageTotal + 1;
        }

        System.out.println("Tổng số Page: " + pageTotal);

        for (int i = 1; i <= pageTotal; i++) {
            WebUI.checkDataInTableByColumn_Contains(3, searchValue, "Company");

            //Nhấn nút Next để đến trang tiếp theo
            if (i < pageTotal) {
                WebUI.clickElement(By.xpath("//a[normalize-space()='Next']"));
                sleep(2);
            }
        }

    }

}
