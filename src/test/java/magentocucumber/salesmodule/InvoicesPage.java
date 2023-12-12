package magentocucumber.salesmodule;

import magentocucumber.dashboardmodule.AdminDashboardPage;
import magentocucumber.universalfunctions.FunctionLibrary;
import magentocucumber.universalfunctions.TestDataHolder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 * @author : SalmanUyghur
 * @created : 12/12/2023,12:30 AM
 * @Email : salmanuyghur3@gmail.com
 **/
public class InvoicesPage {
    WebDriver driver;
    FunctionLibrary functionLibrary;
    Actions actions;
    Random random;
    Logger logger;

    public InvoicesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        functionLibrary=new FunctionLibrary(driver);
        actions=new Actions(driver);
        random=new Random();
        logger= Logger.getLogger(InvoicesPage.class.getName());
    }
    @FindBy(xpath = "//a[@class='active']")
    WebElement salesTap;
    @FindBy(xpath = "//span[normalize-space()='Invoices']")
    WebElement invoices;
    @FindAll(@FindBy(xpath = "//td[@class=\" last\"]"))
    List<WebElement> allInvoices;
    @FindBy(xpath = "//*[@id=\"anchor-content\"]")
    WebElement verifyViewingInvoices;
    @FindBy(xpath = "//textarea[@id='history_comment']")
    WebElement commentTextField;
    @FindBy(xpath = "//input[@id='history_notify']")
    WebElement notifyCustomerCheckBox;
    @FindBy(xpath = "//input[@id='history_visible']")
    WebElement visibleOnFrontend;
    @FindBy(xpath = "//span[contains(text(),'Submit Comment')]")
    WebElement submitButton;
    @FindBy(xpath = "//*[@id=\"comments_block\"]/ul/li/small")
    WebElement verifyCommentTextSuccessful;
    int invoiceSize;

    public void viewInvoicesAndAddComments(String commentText){
        functionLibrary.waitForElementVisible(salesTap);
        actions.moveToElement(salesTap).build().perform();
        functionLibrary.waitForElementVisible(invoices);
        actions.moveToElement(invoices).click().build().perform();
        int randomIndex= random.nextInt(allInvoices.size());
        String randomElement= String.valueOf(allInvoices.get(randomIndex));
        actions.click(allInvoices.get(randomIndex));
        System.out.println("clicked random elements are"+randomElement);
        actions.scrollToElement(commentTextField).build().perform();
        actions.click(commentTextField).sendKeys(commentText).build().perform();
        functionLibrary.waitForElementVisible(notifyCustomerCheckBox);
        actions.click(notifyCustomerCheckBox).build().perform();
        functionLibrary.waitForElementVisible(visibleOnFrontend);
        actions.click(visibleOnFrontend).build().perform();
        functionLibrary.waitForElementVisible(submitButton);
        actions.click(submitButton).build().perform();
    }
    public boolean verifyInvoicesAndComment(){
        if (verifyViewingInvoices.isDisplayed()){
            System.out.println("view invoices successfully!!!");
            return true;
        }if (verifyCommentTextSuccessful.isDisplayed()){
            System.out.println("add comment proses is successful!!!");
            return true;
        }else {
            System.out.println("sorry!!! plz try again!!!");
            return false;
        }
    }

}
