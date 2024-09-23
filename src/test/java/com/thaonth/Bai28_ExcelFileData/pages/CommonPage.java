package com.thaonth.Bai28_ExcelFileData.pages;

import com.thaonth.keywords.WebUI;
import org.openqa.selenium.By;

public class CommonPage {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private CustomerPage customerPage;
    private ProjectPage projectPage;

    public By menuDashboard = By.xpath("//span[normalize-space()='Dashboard']");
    public By menuCustomers = By.xpath("//span[normalize-space()='Customers']");
    public By menuSales = By.xpath("//li[@class='menu-item-sales']");
    public By menuProjects = By.xpath("//span[normalize-space()='Projects']");
    public By menuTasks = By.xpath("//span[normalize-space()='Tasks']");

    public LoginPage getLoginPage() {
        if (loginPage == null){
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public DashboardPage getDashboardPage() {
        if (dashboardPage == null){
            dashboardPage = new DashboardPage();
        }
        return dashboardPage;
    }

    public CustomerPage getCustomerPage() {
        if (customerPage == null){
            customerPage = new CustomerPage();
        }
        return customerPage;
    }

    public ProjectPage getProjectPage() {
        if (projectPage == null){
            projectPage = new ProjectPage();
        }
        return projectPage;
    }

    public DashboardPage clickMenuDashboard(){
        WebUI.clickElement(menuDashboard);
        return new DashboardPage();
    }
    public CustomerPage clickMenuCustomers(){
        WebUI.clickElement(menuCustomers);
        return new CustomerPage();
    }

    public ProjectPage clickMenuProjects(){
        WebUI.clickElement(menuProjects);
        return new ProjectPage();
    }

}
