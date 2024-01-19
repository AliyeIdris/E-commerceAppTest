package com.seleniummastercucumber.pages.reportingmodule;

import com.seleniummastercucumber.utility.FunctionLibrary;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class NewAccountsPage {
    WebDriver driver;
    FunctionLibrary functionLibrary;
    Logger logger;
    Actions actions;
    public NewAccountsPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
        functionLibrary = new FunctionLibrary(driver);
        logger = Logger.getLogger(CustomersOrdersTotalPage.class.getName());
        actions=new Actions(driver);

    }

    @FindBy(xpath = "//input[@id='period_date_from']")
    WebElement calendarFieldFrom;
    @FindBy(xpath = "//input[@id='period_date_to']")
    WebElement calendarFieldTo;
    @FindBy(id = "report_period")
    WebElement periodDropDown;
    @FindBy(xpath = "//span[text()='Refresh']")
    WebElement refreshButton;
    @FindBy(xpath = "//tfoot/tr/th[text()='Total']")
    WebElement totalNumberOfNewCustomers;
    @FindBy(xpath = "//th[@class=' a-right last']")
    WebElement foundAccountsNumber;

    public void applyFilterToReportNewAccounts(String startDate,String endDate) {
        functionLibrary.waitForElementVisible(calendarFieldFrom);
        calendarFieldFrom.clear();
        calendarFieldFrom.sendKeys(String.valueOf(startDate));
        try {
            Thread.sleep(Long.parseLong("2000"));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        functionLibrary.waitForElementVisible(calendarFieldTo);
        calendarFieldTo.click();
        calendarFieldTo.clear();
        calendarFieldTo.sendKeys(endDate);
        functionLibrary.waitForElementVisible(periodDropDown);
        Select selectPeriod = new Select(periodDropDown);
        selectPeriod.selectByVisibleText("Month");
        functionLibrary.waitForElementVisible(refreshButton);
        refreshButton.click();
    }

    public boolean verifyViewNewCustomerReport(){
        functionLibrary.waitForElementVisible(totalNumberOfNewCustomers);
       if(totalNumberOfNewCustomers.isDisplayed()){
           logger.info("New Customer is viewed successfully " +foundAccountsNumber.getText()+ " accounts are created within time range");
           return true;
       }else logger.info("No records are found");
       return false;
    }

}
