package com.seleniummastercucumber.pages.reportingmodule;

import com.seleniummastercucumber.utility.FunctionLibrary;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.tracing.opentelemetry.SeleniumSpanExporter;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsOrderedPage {
    WebDriver driver;
    FunctionLibrary functionLibrary;
    Logger logger;

    public ProductsOrderedPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        functionLibrary=new FunctionLibrary(driver);
        logger=Logger.getLogger(ProductsOrderedPage.class.getName());
    }

    @FindBy(id = "period_date_from")
    WebElement reportDateFrom;
    @FindBy(id = "period_date_to")
    WebElement reportDateTo;
    @FindBy(xpath = "//span[text()='Refresh']")
    WebElement refreshButton;
    @FindBy(css = "#gridProductsSold_table > tfoot > tr > th:nth-child(1)")
    WebElement totalIcon;

    public void viewProductsOrderedReport(String dateFrom,String dateTo){
        functionLibrary.waitForElementVisible(reportDateFrom);
        reportDateFrom.sendKeys(dateFrom);
        functionLibrary.waitForElementVisible(reportDateTo);
        reportDateTo.sendKeys(dateTo);
        functionLibrary.waitForElementVisible(refreshButton);
        refreshButton.click();
    }
    public boolean verifyProductsOrderedReportDisplayed(){
        if (totalIcon.isDisplayed()){
            logger.info("Products Ordered Report successfully viewed !");
        return true;
        }else
            logger.info("Products Ordered Report not Displayed !!!");
        return false;
    }
}
