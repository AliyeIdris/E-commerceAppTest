package com.seleniummastercucumber.pages.salesmodule;

import com.seleniummastercucumber.utility.FunctionLibrary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.logging.Logger;


public class RefundsPage {
    WebDriver driver;
    FunctionLibrary functionLibrary;
    Actions actions;
    Logger logger;

    public RefundsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        functionLibrary = new FunctionLibrary(driver);
        actions = new Actions(driver);
        logger = Logger.getLogger(RefundsPage.class.getName());
    }
    @FindBy(xpath = "//span[text()='Reports']")
    WebElement reportsLink;
    @FindBy(xpath = "(//span[text()='Sales'])[2]")
    WebElement salesLink;
    @FindBy(xpath = "//span[text()='Refunds']")
    WebElement refundsLink;
    @FindBy(xpath = "//img[@id='sales_report_from_trig']")
    WebElement selectData01;
    @FindBy(xpath = "//div[@class='calendar']//tr//div[text()='«']")
    WebElement unSelectTable01;
    @FindBy(xpath = "//*[@id='sales_report_to_trig']")
    WebElement selectDate02;
    @FindBy(css = ".today")
    WebElement unSelectTable02;
    @FindBy(xpath = "//label[@for='sales_report_to']")
    WebElement toDateField;
    @FindBy(xpath = "//span[text()='Show Report']")
    WebElement showReportButton;
    @FindAll(
            @FindBy(xpath = "//table[@class=\"data\"]/tbody"))
    List<WebElement> totalReport;



    public void navigateToRefundsPage(){
        functionLibrary.waitForElementVisible(reportsLink);
        actions.moveToElement(reportsLink).click().perform();
        functionLibrary.waitForElementVisible(salesLink);
        actions.moveToElement(salesLink).click().perform();
        functionLibrary.waitForElementVisible(refundsLink);
        refundsLink.click();
    }
    public void totalRefundedReport(){
        functionLibrary.waitForElementVisible(selectData01);
        selectData01.click();
        functionLibrary.waitForElementVisible(unSelectTable01);
        unSelectTable01.click();
        functionLibrary.waitForElementVisible(toDateField);
        toDateField.click();
        functionLibrary.waitForElementVisible(selectDate02);
        selectDate02.click();
        functionLibrary.waitForElementVisible(unSelectTable02);
        unSelectTable02.click();
        functionLibrary.waitForElementVisible(showReportButton);
        showReportButton.click();

    }
    public boolean viewRefundsInTheReports(){
        if (totalReport.size()>=1){
            logger.info("View Total Refunded Report successful!!!");
            return true;
        }else {
            logger.info("View Total Refunded Report failed!!!");
            return false;
        }
    }
}
