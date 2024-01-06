package com.seleniummastercucumber.pages.reportingmodule;

import com.seleniummastercucumber.utility.FunctionLibrary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.logging.Logger;

public class ReportViewPage {
    WebDriver driver;
    FunctionLibrary functionLibrary;
    Actions actions;
    Logger logger;

    public ReportViewPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        functionLibrary=new FunctionLibrary(driver);
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
@FindBy(xpath = "//tr[@class='pointer']/td[@class='nikosh vikosh']\n")
   WebElement customer;
    @FindBy(xpath = "/html/body/div[1]/div[3]/div")
    WebElement VerifyMassage;

    public void navigateToTheReport(){
ReportsButton.click();
ShoppingCartButton.click();
AbandonedCartsButton.click();
        //actions.moveToElement(ReportsButton).moveToElement(ShoppingCartButton).moveToElement(AbandonedCartsButton).click();
    }
    public void ChooseOneOfTheReport(){
        functionLibrary.waitForElementVisible(customer);
        customer.click();
    }

    public boolean seeAbandonedCartsVerify() {
        functionLibrary.waitForElementVisible(VerifyMassage);
        if (VerifyMassage.isDisplayed()) {
            logger.info("There İs A Mİstake");
            return false;
        } else {
            logger.info("Successful!!!");
            return  true;
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
        if (totalShipped.isDisplayed())
            return true;
        else return false;
    }
}
