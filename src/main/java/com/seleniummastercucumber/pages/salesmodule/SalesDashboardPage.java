package com.seleniummastercucumber.pages.salesmodule;

import com.seleniummastercucumber.utility.FunctionLibrary;
import org.apache.logging.log4j.core.util.Loader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.logging.Logger;

public class SalesDashboardPage {
    WebDriver driver;
    FunctionLibrary functionLibrary;
    Logger logger;
    Actions actions;

    public SalesDashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        functionLibrary=new FunctionLibrary(driver);
        logger=Logger.getLogger(SalesDashboardPage.class.getName());
        actions=new Actions(driver);
    }
    @FindBy(xpath = "(//span[text()='Customers'])[1]")
    WebElement customersLink;
    @FindBy(xpath = "//span[text()='Manage Customers']")
    WebElement manageCustomersLink;
    public void navigateToManageCustomersPage(){
        functionLibrary.waitForElementVisible(customersLink);
        actions.moveToElement(customersLink).click(manageCustomersLink).build().perform();
    }
}
