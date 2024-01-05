package com.seleniummastercucumber.pages.reportingmodule;

import com.seleniummastercucumber.utility.FunctionLibrary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.logging.Logger;

public class ProductsMostViewedPage {
    WebDriver driver;
    FunctionLibrary functionLibrary;
    Actions actions;
    Logger logger;

    public ProductsMostViewedPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        functionLibrary=new FunctionLibrary(driver);
        actions=new Actions(driver);
        logger = Logger.getLogger(ProductsMostViewedPage.class.getName());
    }
    @FindBy(id = "sales_report_from")
    WebElement reportDateFrom;
    @FindBy(id = "sales_report_to")
    WebElement reportDateTo;
    @FindBy(xpath = "//span[text()='Show Report']")
    WebElement showReportButton;
    @FindBy(xpath = "//tr[@class='totals']")
    WebElement totalShipped;
    @FindBy(xpath = "//span[text()='Reports']")
    WebElement reportsLink;
    @FindBy(xpath = "(//span[text()='Products'])[1]")
    WebElement productsLink;
    @FindBy(xpath = "//span[text()='Most Viewed']")
    WebElement mostViewedLink;

    public void mostViewedProducts(String dateFrom, String dateTo){
        functionLibrary.waitForElementVisible(reportsLink);
        reportsLink.click();
        functionLibrary.waitForElementVisible(productsLink);
        actions.moveToElement(productsLink).moveToElement(mostViewedLink).click(mostViewedLink).build().perform();
        functionLibrary.waitForElementVisible(reportDateFrom);
        reportDateFrom.sendKeys(dateFrom);
        functionLibrary.waitForElementVisible(reportDateTo);
        reportDateTo.sendKeys(dateTo);
        functionLibrary.waitForElementVisible(showReportButton);
        showReportButton.click();
    }

    public boolean checkMostViewedProducts(){
        functionLibrary.waitForElementVisible(totalShipped);
        if (totalShipped.isDisplayed()){
            logger.info("Check Most Viewed Products Success !");
            return true;
        }else {
            logger.info("Check Most Viewed Products Failed !!!");
            return false;
        }
    }
}
