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

/**
 * @author : user
 * @created : 27.12.2023,16:26
 * @Email :aliyeidiris@gmail.com
 **/
public class CustomersOrdersTotalPage {
    WebDriver driver;
    FunctionLibrary functionLibrary;
    Logger logger;

    public CustomersOrdersTotalPage(WebDriver driver) {
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
    @FindBy(xpath = "//span[text()='Customer Name']")
    WebElement customerNameColumn;
    @FindBy(xpath = "//tfoot/tr/th[text()='Total']//following-sibling::th[2]")
    WebElement totalNumberOfOrdersField;
    @FindBy(xpath = "//tfoot/tr/th[text()='Total']//following-sibling::th[3]")
    WebElement averageOrderAmountField;
    @FindBy(xpath = "//tfoot/tr/th[text()='Total']//following-sibling::th[4]")
    WebElement totalOrderAmountField;
    public void enterStartDate(String startDate) {
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
    public void enterEndDate(){
        functionLibrary.waitForElementVisible(calendarIconTo);
        calendarIconTo.click();
        WebElement currentDay = driver.findElement(By.xpath("(//div[text()='today'])[2]"));
        functionLibrary.waitForElementVisible(currentDay);
        currentDay.click();
    }
    public void applyFilterToReport(String startDate){
        enterStartDate(startDate);
        enterEndDate();
        functionLibrary.waitForElementVisible(periodDropDown);
        Select selectPeriod=new Select(periodDropDown);
        selectPeriod.selectByVisibleText("Year");
        functionLibrary.waitForElementVisible(refreshButton);
        refreshButton.click();
    }
    public boolean verifyViewCustomersByTotalOrdersReport(){
        int numberOfOrder=Integer.parseInt(totalNumberOfOrdersField.getText());
        double averageOrderAmount=Double.parseDouble(averageOrderAmountField.getText().substring(1));
        int expectedTotalOrderAmount=(int)(numberOfOrder*averageOrderAmount);
        int actualTotalOrderAmount=(int)(Double.parseDouble(totalOrderAmountField.getText().substring(1).replace(",","")));
        return customerNameColumn.isDisplayed() &&expectedTotalOrderAmount==actualTotalOrderAmount;
    }
}
