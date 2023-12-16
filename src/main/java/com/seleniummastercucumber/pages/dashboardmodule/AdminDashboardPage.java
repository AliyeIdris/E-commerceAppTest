package com.seleniummastercucumber.pages.dashboardmodule;

import com.seleniummastercucumber.utility.FunctionLibrary;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.logging.Logger;

/**
 * @author : user
 * @created : 9.12.2023,15:07
 * @Email :aliyeidiris@gmail.com
 **/
public class AdminDashboardPage {
    WebDriver driver;
    FunctionLibrary functionLibrary;
    Logger logger;

    public AdminDashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        functionLibrary=new FunctionLibrary(driver);
        logger= Logger.getLogger(AdminDashboardPage.class.getName());
    }
    @FindBy(css = ".link-logout")
    WebElement logoutLink;
    @FindBy(xpath = "//p[contains(text(),'Logged in as salesmanager')]")
    WebElement salesManagerDashboardText;
    @FindBy(xpath = "//p[contains(text(),'Logged in as reportingmanager')]")
    WebElement reportingManagerDashboardText;
    public void logout(){
        functionLibrary.waitForElementVisible(logoutLink);
        logoutLink.click();
        WebElement adminPanel=driver.findElement(By.xpath("//h2[text()='Log in to Admin Panel']"));
        if (adminPanel.isDisplayed()){
            System.out.println("Logout successfully");
        }
    }
    public boolean verifySalesManagerDashboardPage(){
        functionLibrary.waitForElementVisible(logoutLink);
        functionLibrary.waitForElementVisible(salesManagerDashboardText);
        if (salesManagerDashboardText.isDisplayed() &&logoutLink.isDisplayed()) {
            System.out.println("Sales Manager logged in successfully");
            return true;
        }else
            return false;
    }
    public boolean verifyReportingManagerDashboardPage(){
        functionLibrary.waitForElementVisible(logoutLink);
        functionLibrary.waitForElementVisible(reportingManagerDashboardText);
        if (reportingManagerDashboardText.isDisplayed() &&logoutLink.isDisplayed()) {
            System.out.println("Reporting Manager logged in successfully");
            return true;
        }else
            return false;
    }
}
