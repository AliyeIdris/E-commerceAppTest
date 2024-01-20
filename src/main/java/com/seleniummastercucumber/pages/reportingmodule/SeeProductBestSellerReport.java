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

/**
 * @author :
 * @created : 1/13/2024,7:18 AM
 * @Email : mukeremilyas@gmail.com.com
 **/
public class SeeProductBestSellerReport {
    WebDriver driver;
    FunctionLibrary functionLibrary;
    Actions actions;
    Logger logger;

    public SeeProductBestSellerReport(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        functionLibrary=new FunctionLibrary(driver);
        actions= new Actions(driver);
        logger = Logger.getLogger(ReportingDashboardPage.class.getName());
    }

    @FindBy(xpath = "//span[text()='Reports']")
    WebElement reportsLink;
    @FindBy(xpath = "(//span[text()='Products'])[1]")
    WebElement productsLink;
    @FindBy(xpath = "//span[text()='Bestsellers']")
    WebElement bestSellers;
    @FindBy(xpath = "//span[contains(text(), 'This report depends on timezone configuration. Once timezone is changed, the lifetime statistics need to be refreshed.')]")
    WebElement productBestSellersText;

    public void clickOnProducts() {
        logger.info("go to Reports then click on Products");
        functionLibrary.waitForElementVisible(reportsLink);
        reportsLink.click();
        functionLibrary.waitForElementVisible(productsLink);
        productsLink.click();

    }

    public void openBestSellersReportPage() {
        logger.info("Click on Best Sellers under the products dropdown menu to open product bestsellers page");
        functionLibrary.waitForElementVisible(bestSellers);
        bestSellers.click();
    }

    public boolean isProductBestSellersPageDisplayed() {
        functionLibrary.waitForElementVisible(productBestSellersText);
        boolean isDisplayed = productBestSellersText.isDisplayed();
        logger.info("Is Product Best Sellers Page Displayed ? : " + isDisplayed);
        return isDisplayed;
    }}