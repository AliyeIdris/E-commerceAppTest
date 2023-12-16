package com.seleniummastercucumber.pages.salesmodule;

import com.seleniummastercucumber.utility.FunctionLibrary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CouponsPage {
    WebDriver driver;
    FunctionLibrary functionLibrary;
    Actions actions;

    public CouponsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        functionLibrary=new FunctionLibrary(driver);
        actions=new Actions(driver);
    }
    @FindBy(xpath = "//span[text()='Reports']")
    WebElement Reports;
    @FindBy(xpath = "(//span[text()='Sales'])[2]")
    WebElement Sales;
    @FindBy(xpath = "//span[text()='Coupons']")
    WebElement Coupons;
    @FindBy(id = "sales_report_from")
    WebElement fromName;
    @FindBy(id ="sales_report_to")
    WebElement toName;
    @FindBy(xpath = "//span[text()='Show Report']")
    WebElement ShowReport;
    @FindAll(@FindBy(xpath = "(//span[contains(text(),'Show Report')])[1]"))
    public List<WebElement> verifyShowReport1;

    public void openReportsPage(){
        functionLibrary.waitForElementVisible(Reports);
        actions.moveToElement(Reports).click().perform();
        functionLibrary.waitForElementVisible(Sales);
        actions.moveToElement(Sales).click().perform();
        functionLibrary.waitForElementVisible(Coupons);
        Coupons.click();
    }

    public void  viewCouponsReports(String fromDate,String toDame){

        functionLibrary.waitForElementVisible(fromName);
        fromName.sendKeys(fromDate);
        functionLibrary.waitForElementVisible(toName);
        toName.sendKeys(toDame);
        functionLibrary.waitForElementVisible(ShowReport);
        ShowReport.click();
    }
    public boolean verifyCouponsReports(){
        if (verifyShowReport1.size()>=1);
        System.out.println("Coupons Usage Report is disply");
        return true;
    }
}
