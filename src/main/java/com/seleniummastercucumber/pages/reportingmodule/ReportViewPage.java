package com.seleniummastercucumber.pages.reportingmodule;

import com.seleniummastercucumber.utility.FunctionLibrary;
import com.seleniummastercucumber.utility.ScreenShotUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.logging.Logger;

public class ReportViewPage {
    WebDriver driver;
    FunctionLibrary functionLibrary;
    Actions actions;

    public ReportViewPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        functionLibrary=new FunctionLibrary(driver);
        actions= new Actions(driver);
    }
    //Shemshinur
    @FindBy(id = "sales_report_from")
    WebElement shippingReportDateFrom;
    @FindBy(id = "sales_report_to")
    WebElement shippingReportDateTo;
    @FindBy(xpath = "//span[text()='Show Report']")
    WebElement showReportButton;
    @FindBy(xpath = "//tr[@class='totals']")
    WebElement totalShipped;
    // see Abandoned Carts Report
    @FindBy(xpath = "//span[text()='Reports']")
    WebElement ReportsButton;
    @FindBy(xpath ="//span[text()='Shopping Cart']")
    WebElement ShoppingCartButton;
    @FindBy(xpath ="//span[text()='Abandoned carts']")
    WebElement AbandonedCartsButton;

    @FindBy(xpath = "//h1[contains(text(),'Access denied')]")
    WebElement VerifyMassage;
    @FindBy(xpath = "//*[@onchange='gridAbandonedJsObject.loadByElement(this)']")
    WebElement limitDropdown;


    //İhram
    public void navigateToTheReport(){
        ReportsButton.click();
        ShoppingCartButton.click();
        AbandonedCartsButton.click();
    }
    public void clickReportMethod(String reportemail){
        functionLibrary.waitForElementVisible(limitDropdown);
        Select select=new Select(limitDropdown);
        select.selectByValue("200");
        WebElement emailLocation= driver.findElement(By.xpath(String.format("//td[contains(text(),'%s')]",reportemail)));
        functionLibrary.waitForElementVisible(emailLocation);
        emailLocation.click();
    }

    public boolean seeAbandonedCartsVerify() {
        functionLibrary.waitForElementVisible(VerifyMassage);
        if (VerifyMassage.isDisplayed()) {
            System.out.println("There is A Bug");
            return true;
        } else {
            return false;
        }
    }

    public void viewTotalShippedReport(String dateFrom, String dateTo){
        functionLibrary.waitForElementVisible(shippingReportDateFrom);
        shippingReportDateFrom.sendKeys(dateFrom);
        functionLibrary.waitForElementVisible(shippingReportDateTo);
        shippingReportDateTo.sendKeys(dateTo);
        functionLibrary.waitForElementVisible(showReportButton);
        showReportButton.click();
    }
    public boolean confirmIsReportAppeared(){
        functionLibrary.waitForElementVisible(totalShipped);
        return totalShipped.isDisplayed();
    }
}
