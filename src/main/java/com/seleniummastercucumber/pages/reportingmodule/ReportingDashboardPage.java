package com.seleniummastercucumber.pages.reportingmodule;

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
    @FindBy(xpath = "//span[text()='Orders']")
    WebElement ordersLink;
    @FindBy(xpath = "//a/span[text()='Tax']")
    WebElement taxLink;
    @FindBy(xpath = "//span[text()='Customers']")
    WebElement customersLink;
    @FindBy(xpath = "//span[text()='Customers by orders total']")
    WebElement customersByOrderTotalLink;
    @FindBy(xpath = "//span[text()='New Accounts']")
    WebElement newAccountsLink;
    @FindBy(xpath = "//span[text()='Products Ordered']")
    WebElement productsOrderedLink;
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
    public void goToOrderedReportPage(){
        functionLibrary.waitForElementVisible(reportsLink);
        actions.moveToElement(reportsLink).moveToElement(salesLink).moveToElement(ordersLink).click().build().perform();
    }
    public void navigateToTaxReportPage(){
        functionLibrary.waitForElementVisible(reportsLink);
        actions.moveToElement(reportsLink).perform();
        functionLibrary.waitForElementVisible(salesLink);
        actions.moveToElement(salesLink).perform();
        functionLibrary.waitForElementVisible(taxLink);
        taxLink.click();
    }
    public void navigateToCustomersOrderTotalPage(){
        functionLibrary.waitForElementVisible(reportsLink);
        actions.moveToElement(reportsLink).perform();
        functionLibrary.waitForElementVisible(customersLink);
        actions.moveToElement(customersLink).perform();
        functionLibrary.waitForElementVisible(customersByOrderTotalLink);
        customersByOrderTotalLink.click();
    }
    public void navigateToNewAccountsPage(){
        functionLibrary.waitForElementVisible(reportsLink);
        actions.moveToElement(reportsLink).perform();
        functionLibrary.waitForElementVisible(customersLink);
        actions.moveToElement(customersLink).perform();
        functionLibrary.waitForElementVisible(newAccountsLink);
        newAccountsLink.click();
    }
    public void navigateToProductsOrderedPage(){
        functionLibrary.waitForElementVisible(reportsLink);
        actions.moveToElement(reportsLink).perform();
        functionLibrary.waitForElementVisible(productsLink);
        actions.moveToElement(productsLink).moveToElement(productsOrderedLink).click(productsOrderedLink).build().perform();
    }

}
