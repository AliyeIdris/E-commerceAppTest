package com.seleniummastercucumber.pages.reportingmodule;

import com.seleniummastercucumber.pages.salesmodule.RefundsPage;
import com.seleniummastercucumber.utility.FunctionLibrary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author :merve
 * @created : 07/01/2024,18:55
 * @project : SDET2023Magento_Team1Cucumber
 */
public class InvoicedPage {
    WebDriver driver;
    FunctionLibrary functionLibrary;
    Actions actions;
    ReportingDashboardPage dashboardPage;
    Logger logger;

    public InvoicedPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        functionLibrary = new FunctionLibrary(driver);
        actions = new Actions(driver);
        dashboardPage = new ReportingDashboardPage(driver);
        logger = Logger.getLogger(RefundsPage.class.getName());
    }

    @FindBy(xpath = "//select[@id=\"store_switcher\"]")
    WebElement showReportForSelection;
    @FindBy(xpath = "//select[@id=\"sales_report_report_type\"]")
    WebElement matchPeriodToSelection;
    @FindBy(xpath = "//select[@id=\"sales_report_period_type\"]")
    WebElement periodSelection;
    @FindBy(xpath = "//input[@id=\"sales_report_from\"]")
    WebElement from;
    @FindBy(xpath = "//input[@id=\"sales_report_to\"]")
    WebElement to;
    @FindBy(xpath = "//div[@id=\"anchor-content\"]//*[@title=\"Show Report\"]")
    WebElement showReportButton;
    @FindBy(xpath = "//tr[@class=\"totals\"]")
    WebElement total;
    @FindAll(@FindBy(xpath = "//tr[@class=\"even\"]/td"))
    List<WebElement> noRecordsFound;
    @FindAll(@FindBy(xpath = "//tr[@class=\"totals\"]/th"))
    List<WebElement> totalsRecordsFound;

    public void showReport(String period, String fromDate, String toDate) {
        functionLibrary.waitForElementVisible(showReportForSelection);
        Select select = new Select(showReportForSelection);
        select.selectByVisibleText("All Websites");
        functionLibrary.waitForElementVisible(matchPeriodToSelection);
        Select select1 = new Select(matchPeriodToSelection);
        select1.selectByIndex(0);
        functionLibrary.waitForElementVisible(periodSelection);
        Select select2 = new Select(periodSelection);
        select2.selectByVisibleText(period);
        functionLibrary.waitForElementVisible(from);
        from.sendKeys(fromDate);
        functionLibrary.waitForElementVisible(to);
        to.sendKeys(toDate);
        functionLibrary.waitForElementVisible(showReportButton);
        showReportButton.click();
    }

    public boolean verifyReport() {
        functionLibrary.waitForElementVisible(total);
        if (totalsRecordsFound.size()>0) {
            logger.info("Total Report : " + total.getText());
            return true;
        } else if (noRecordsFound.size()>0) {
            logger.info("No records found, ---Please select correct Report ");
        }
        return false;
    }
}
