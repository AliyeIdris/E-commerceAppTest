package com.seleniummastercucumber.pages.reportingmodule;

import com.seleniummastercucumber.utility.FunctionLibrary;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewAccountsPage {
    WebDriver driver;
    FunctionLibrary functionLibrary;
    Logger logger;

    public NewAccountsPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        functionLibrary=new FunctionLibrary(driver);
        logger=Logger.getLogger(CustomersOrdersTotalPage.class.getName());
    }
    @FindBy(id = "period_date_from_trig")
    WebElement calendarIconFrom;
    @FindBy(id = "period_date_to_trig")
    WebElement calendarIconTo;
    @FindBy(id = "report_period")
    WebElement periodDropDown;
    @FindBy(xpath = "//span[text()='Refresh']")
    WebElement refreshButton;
    @FindBy(xpath = "//tfoot/tr/th[text()='Total']")
    WebElement totalNumberOfNewCustomers;

 void enterStartDate(String startDate) {
        String[] splitDate = startDate.split("/");
        int day = Integer.parseInt(splitDate[0]);
        int month = Integer.parseInt(splitDate[1]);
        String monthString = new DateFormatSymbols().getMonths()[month - 1];
        int year = Integer.parseInt(splitDate[2]);
        functionLibrary.waitForElementVisible(calendarIconFrom);
        calendarIconFrom.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement currentMonthYearField;
        String[] yearMonth;
        while (true) {
            currentMonthYearField = driver.findElement(By.xpath("//thead/tr/td[@colspan='6' and @class='title']"));
            functionLibrary.waitForElementVisible(currentMonthYearField);
            yearMonth = currentMonthYearField.getText().split(",");
            int currentYear = Integer.parseInt(yearMonth[1].trim());
            if (currentYear == year) {
                break;
            } else if (currentYear > year) {
                WebElement yearBackwardButton = driver.findElement(By.xpath("//div[text()='«']"));
                functionLibrary.waitForElementVisible(yearBackwardButton);
                yearBackwardButton.click();
            } else {
                WebElement yearForwardButton = driver.findElement(By.xpath("//div[text()='»']"));
                functionLibrary.waitForElementVisible(yearForwardButton);
                yearForwardButton.click();
            }
        }
        while (true) {
            currentMonthYearField = driver.findElement(By.xpath("//thead/tr/td[@colspan='6' and @class='title']"));
            yearMonth = currentMonthYearField.getText().split(",");
            String currentMonth = yearMonth[0].trim();
            if (currentMonth.equalsIgnoreCase(monthString)) {
                break;
            } else {
                int num;
                try {
                    Date date = new SimpleDateFormat("MMM").parse(currentMonth);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    num = cal.get(Calendar.MONTH) + 1;
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                if (num > month) {
                    WebElement monthBackwardButton = driver.findElement(By.xpath("//div[text()='‹']"));
                    functionLibrary.waitForElementVisible(monthBackwardButton);
                    monthBackwardButton.click();
                } else {
                    WebElement monthForwardButton = driver.findElement(By.xpath("//div[text()='›']"));
                    functionLibrary.waitForElementVisible(monthForwardButton);
                    monthForwardButton.click();
                }
            }
        }
        WebElement dayField = driver.findElement(By.xpath(String.format("//td[contains(@class,'day') and text()='%d']", day)));
        functionLibrary.waitForElementVisible(dayField);
        dayField.click();
    }
    public void enterEndDate(String endDate){
        functionLibrary.waitForElementVisible(calendarIconTo);
        calendarIconTo.click();
        WebElement currentDay = driver.findElement(By.xpath("(//div[text()='today'])[2]"));
        functionLibrary.waitForElementVisible(currentDay);
        currentDay.click();
    }

    public void applyFilterToReportNewCustomers(String startDate, String endDate) {
        enterStartDate(startDate);
        enterEndDate(endDate);
        functionLibrary.waitForElementVisible(periodDropDown);
        Select selectPeriod = new Select(periodDropDown);
        selectPeriod.selectByVisibleText("Year");
        functionLibrary.waitForElementVisible(refreshButton);
        refreshButton.click();
    }

    public boolean verifyViewNewCustomerReport(){
       if(totalNumberOfNewCustomers.isDisplayed()){
           logger.info("New Customer is viewed successfully");
           return true;
       }else logger.info("New Customer is not viewed");
       return false;
    }

}
