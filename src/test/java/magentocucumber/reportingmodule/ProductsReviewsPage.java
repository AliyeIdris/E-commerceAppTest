package magentocucumber.reportingmodule;

import magentocucumber.universalfunctions.FunctionLibrary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 * @author : SalmanUyghur
 * @created : 12/16/2023,1:29 AM
 * @Email : salmanuyghur3@gmail.com
 **/
public class ProductsReviewsPage {
    WebDriver driver;
    FunctionLibrary functionLibrary;
    Actions actions;
    ReportingDashboardPage dashboardPage;
    Logger logger;
    Select select;

    public ProductsReviewsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        functionLibrary=new FunctionLibrary(driver);
        actions=new Actions(driver);
        dashboardPage=new ReportingDashboardPage(driver);

    }
    @FindBy(xpath = "//span[text()=\"Reviews\"]")
    WebElement reviewsButton;
    @FindBy(xpath = "//span[text()=\"Products Reviews\"]")
    WebElement productsReviews;
    @FindBy(xpath = "//select[@name='limit']")
    WebElement selectDropBox;
    @FindAll(@FindBy(xpath = "//table/tbody/tr"))
    List<WebElement> products;
    @FindAll(@FindBy(xpath = "//table/tbody/tr"))
    List<WebElement> reviews;
    public void setProductsReviews(){
        functionLibrary.waitForElementVisible(dashboardPage.reportsLink);
        actions.moveToElement(dashboardPage.reportsLink).build().perform();
        functionLibrary.waitForElementVisible(reviewsButton);
        actions.moveToElement(reviewsButton).build().perform();
        functionLibrary.waitForElementVisible(productsReviews);
        actions.moveToElement(productsReviews).click().build().perform();
        functionLibrary.waitForElementVisible(selectDropBox);
        actions.click(selectDropBox).build().perform();
        select=new Select(selectDropBox);
        select.selectByValue("200");
        if (products.size()==0){
            logger.info("sorry there are not any products!!!");
        } else if (products.size()>1) {
            int i=(int)(Math.random()*products.size());
            products.get(i).click();
        }
        functionLibrary.waitForElementVisible(selectDropBox);
        actions.click(selectDropBox).build().perform();
        select.selectByValue("200");
    }
    public boolean verifyProductsReviews(){
        if (reviews.size()>1){
            System.out.println("reviews are already displayed successfully!!!");
          //  logger.info("reviews are already displayed successfully!!!");
            return true;
        } else if (reviews.size()==0) {
            logger.info("sorry ! there are not any reviews added");

        }return false;
    }

}
