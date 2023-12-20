package com.seleniummastercucumber.pages.reportingmodule;

import com.seleniummastercucumber.pages.salesmodule.RefundsPage;
import com.seleniummastercucumber.utility.FunctionLibrary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import java.util.logging.Logger;

public class OrderReportPage {
    WebDriver driver;
    FunctionLibrary functionLibrary;
    Logger logger;

    public OrderReportPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        functionLibrary=new FunctionLibrary(driver);
        logger= Logger.getLogger(RefundsPage.class.getName());
    }
    @FindBy(id = "sales_report_from_trig")
    WebElement fromCalender;
    @FindBy(id = "sales_report_to_trig")
    WebElement toCalender;
    @FindBy(xpath = "//*[text()='«']")
    WebElement goLastYearButton;
    @FindBy(xpath = "//*[text()='To ']")
    WebElement toWordPath;
    @FindBy(xpath = "//*[@id=\"html-body\"]/div[5]/table/thead/tr[2]/td[3]/div") //cpfy
    WebElement todayButton;
    @FindBy(xpath = "//*[text()='Show Report']")
    WebElement showReportButton;
    @FindAll (@FindBy(xpath = "//*[@class='data']/tbody/tr"))
    List<WebElement> totalOrderReport;
    public void seeTotalOrderedReport(){
        functionLibrary.waitForElementVisible(fromCalender);
        fromCalender.click();
        functionLibrary.waitForElementVisible(goLastYearButton);
        goLastYearButton.click();
        functionLibrary.waitForElementVisible(toWordPath);
        toWordPath.click();
        functionLibrary.waitForElementVisible(toCalender);
        toCalender.click();
        functionLibrary.waitForElementVisible(todayButton);
        todayButton.click();
        functionLibrary.waitForElementVisible(showReportButton);
        showReportButton.click();


    }
    public boolean verifyDisplayedTotalOrderReport(){
        if (totalOrderReport.size()>=1){
            logger.info("Total "+totalOrderReport.size()+" order displayed");
            return true;
        }else {
            logger.info("No records found ");
            return false;
        }

    }
}
