package com.seleniummastercucumber.pages.reportingmodule;

import com.seleniummastercucumber.pages.salesmodule.RefundsPage;
import com.seleniummastercucumber.utility.FunctionLibrary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author :merve
 * @created : 07/01/2024,19:05
 * @project : SDET2023Magento_Team1Cucumber
 */
public class CouponsPage {
    WebDriver driver;
    FunctionLibrary functionLibrary;
    Actions actions;
    ReportingDashboardPage dashboardPage;
    Logger logger;

    public CouponsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        functionLibrary = new FunctionLibrary(driver);
        actions = new Actions(driver);
        dashboardPage = new ReportingDashboardPage(driver);
        logger= Logger.getLogger(RefundsPage.class.getName());
    }
    @FindBy(id = "store_switcher")
    WebElement selectShowReportFor;
    @FindBy(id = "sales_report_from")
    WebElement fromDateField;
    @FindBy(id = "sales_report_to")
    WebElement toDateField;
    @FindBy(xpath = "(//span[contains(text(),'Show Report')])[1]")
    WebElement showReportsButton;
    @FindBy(xpath = "(//h3[text()=\"Coupons Usage Report\"])[1]")
    WebElement couponsUsageReportText;
    @FindAll(@FindBy(xpath = "//tr[@class=\"even\"]/td"))
    List<WebElement> noRecordsFound;

    public boolean isSalesCouponsUsagePageDisplayed() {
        functionLibrary.waitForElementVisible(couponsUsageReportText);
        boolean isDisplayed = couponsUsageReportText.isDisplayed();
        logger.info("is Coupons Usage Report Page Displayed? : " + isDisplayed);
        return isDisplayed;
    }

    public void filterCouponsUsageReports(String websiteName, String fromDate, String toDate) {
        functionLibrary.waitForElementVisible(selectShowReportFor);
        selectShowReportFor.sendKeys(websiteName);
        functionLibrary.waitForElementVisible(fromDateField);
        fromDateField.sendKeys(fromDate);
        functionLibrary.waitForElementVisible(toDateField);
        toDateField.sendKeys(toDate);
        functionLibrary.waitForElementVisible(showReportsButton);
        showReportsButton.click();
    }

    public boolean verifyCouponsUsageReport() {
        if (noRecordsFound.size() > 0) {
            logger.info("No Records Found");
        }
        return true;
    }
}

