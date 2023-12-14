package magentocucumber.reportingmodule;

import magentocucumber.universalfunctions.FunctionLibrary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReportViewPage {
    WebDriver driver;
    FunctionLibrary functionLibrary;

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
