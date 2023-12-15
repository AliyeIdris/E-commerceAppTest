package magentocucumber.reportingmodule;

import magentocucumber.universalfunctions.FunctionLibrary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 * @author : SalmanUyghur
 * @created : 12/13/2023,12:01 PM
 * @Email : salmanuyghur3@gmail.com
 **/
public class DownloadsPage {
    WebDriver driver;
    FunctionLibrary functionLibrary;
    Actions actions;
    ReportingDashboardPage dashboardPage;
    Logger logger;

    public DownloadsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        functionLibrary=new FunctionLibrary(driver);
        actions=new Actions(driver);
        dashboardPage=new ReportingDashboardPage(driver);

    }
    @FindBy(xpath = "//a[@href='#']//span[contains(text(),'Products')]")
    WebElement productsLink;
    @FindBy(xpath = "//span[normalize-space()='Downloads']")
    WebElement downloadsLink;
    @FindBy(xpath = "//span[contains(text(),'Export')]")
    WebElement exportButton;
    @FindBy(xpath = "//*[@id=\"downloadsGrid_export\"]")
    WebElement CSV;
    @FindAll(@FindBy(xpath = "//*[@id=\"downloadsGrid_export\"]/option"))
    List<WebElement> selectOptions;
    @FindAll(@FindBy(xpath = "//*[@title=\"#\"]"))
    List<WebElement> reports;

    public void downloadProducts(){
        functionLibrary.waitForElementVisible(dashboardPage.reportsLink);
        actions.moveToElement(dashboardPage.reportsLink).build().perform();
        functionLibrary.waitForElementVisible(productsLink);
        actions.moveToElement(productsLink).moveToElement(downloadsLink).click().build().perform();
        functionLibrary.waitForElementVisible(CSV);
        actions.click(CSV).build().perform();
        selectOptions.get(new Random().nextInt(selectOptions.size())).click();
        functionLibrary.waitForElementVisible(exportButton);
        actions.click(exportButton).build().perform();
    }
    public boolean verifyProductsDownloadsReport(){
     if (reports.size()==0){
         logger.info("there are not any downloads reports,please add reports");
         return false;
     }else if (reports.size()==1){
         logger.info("the products downloads reports are already seen successful!!!");
     }
     return true;
    }
}
