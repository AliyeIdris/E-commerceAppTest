package com.seleniummastercucumber.pages.reportingmodule;

import com.seleniummastercucumber.utility.FunctionLibrary;
import org.apache.log4j.Logger;
import org.bouncycastle.asn1.cmp.Challenge;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author : user
 * @created : 26.12.2023,13:31
 * @Email :aliyeidiris@gmail.com
 **/
public class TaxReportPage {
    WebDriver driver;
    FunctionLibrary functionLibrary;
    Logger logger;
    @FindBy(id = "sales_report_period_type")
    WebElement periodDropdown;
    @FindBy(xpath = "//span[text()='Show Report']")
    WebElement showReportButton;
    @FindBy(xpath = "//tr[@class='headings']/th/span[text()='Rate']")
    WebElement rateColumn;
    @FindAll(
            @FindBy(xpath = "//tbody/tr/td[3]")
    )
    List<WebElement> rateColumnElements;
    @FindBy(xpath = "//tr[@class='headings']/th/span[text()='Number of Orders']")
    WebElement numberOfOrdersColumn;
    @FindAll(
            @FindBy(xpath = "//tbody/tr/td[4]")
    )
    List<WebElement> numberOfOrdersColumnElements;
    @FindBy(xpath = "//tr[@class='headings']/th/span[text()='Tax Amount']")
    WebElement taxAmountColumn;
    @FindAll(
            @FindBy(xpath = "//tbody/tr/td[5]")
    )
    List<WebElement> taxAmountColumnElements;
    @FindBy(xpath = "//tfoot/tr[@class='totals']/th[contains(text(),'$')]")
    WebElement totalTaxAmount;
    @FindBy(css = "#sales_report_from_trig")
    WebElement calendarIconFrom;
    @FindBy(css = "#sales_report_to_trig")
    WebElement calendarIconTo;

    public TaxReportPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        functionLibrary = new FunctionLibrary(driver);
        logger = Logger.getLogger(TaxReportPage.class.getName());
    }

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

    public void enterEndDate() {
        functionLibrary.waitForElementVisible(calendarIconTo);
        calendarIconTo.click();
        WebElement currentDay = driver.findElement(By.xpath("(//div[text()='today'])[2]"));
        functionLibrary.waitForElementVisible(currentDay);
        currentDay.click();
    }

    public void filterTaxReportDate(String startDate, String endDate) {
        functionLibrary.waitForElementVisible(periodDropdown);
        Select selectPeriod = new Select(periodDropdown);
        List<WebElement> options = selectPeriod.getOptions();
        options.get(new Random().nextInt(options.size())).click();
        enterStartDate(startDate);
        enterEndDate();
        functionLibrary.waitForElementVisible(showReportButton);
        showReportButton.click();
    }

    public boolean verifyTaxReportViewedSuccessfully() {
        if (rateColumn.isDisplayed() && numberOfOrdersColumn.isDisplayed() && taxAmountColumn.isDisplayed()) {
            if (rateColumnElements.size() > 0 && numberOfOrdersColumnElements.size() > 0 && taxAmountColumnElements.size() > 0) {
                return true;
            }
        } else
            return false;
        return totalTaxAmount.isDisplayed();
    }
}
