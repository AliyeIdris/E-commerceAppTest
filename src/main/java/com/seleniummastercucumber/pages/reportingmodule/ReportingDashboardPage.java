package com.seleniummastercucumber.pages.reportingmodule;

import com.seleniummastercucumber.pages.salesmodule.RefundsPage;
import com.seleniummastercucumber.utility.FunctionLibrary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.logging.Logger;

public class ReportingDashboardPage {
    WebDriver driver;
    FunctionLibrary functionLibrary;
    Actions actions;
    Logger logger;

    public ReportingDashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        functionLibrary=new FunctionLibrary(driver);
        actions=new Actions(driver);
        logger = Logger.getLogger(ReportingDashboardPage.class.getName());
    }
    @FindBy(xpath = "//span[text()='Reports']")
    WebElement reportsLink;
    @FindBy(xpath = "//span[text()='Sales']")
    WebElement salesLink;
    @FindBy(xpath = "//span[contains(text(),'Shipping')]")
    WebElement shippingLink;
    @FindBy(xpath = "(//span[text()='Products'])[1]")
    WebElement productsLink;
    @FindBy(xpath = "//span[text()='Low stock']")
    WebElement lowStockLink;
    @FindAll(
            @FindBy(xpath = "//div[@class='hor-scroll']//tbody"))
    List<WebElement> lowStockReport;
    public void openShippingPage() {
        functionLibrary.waitForElementVisible(reportsLink);
        actions.moveToElement(reportsLink).moveToElement(salesLink).moveToElement(shippingLink).click().perform();
    }
    public void navigateToLowStockPage(){
        functionLibrary.waitForElementVisible(reportsLink);
        actions.moveToElement(reportsLink).moveToElement(productsLink)
                .moveToElement(lowStockLink).click().perform();
    }
    public boolean viewAllLowStock(){
        if (lowStockReport.size()>=1){
            logger.info("View All low Stock Report successful!!!");
            return true;
        }else {
            logger.info("View All low Stock Report failed!!!");
            return false;
        }
    }
}
