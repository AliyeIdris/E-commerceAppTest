package com.seleniummastercucumber.pages.reportingmodule;

import com.seleniummastercucumber.pages.salesmodule.RefundsPage;
import com.seleniummastercucumber.utility.FunctionLibrary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.nio.file.Watchable;
import java.util.logging.Logger;

public class CustomersTagsPage {
    WebDriver driver;
    FunctionLibrary functionLibrary;
    Actions actions;
    Logger logger;
    public CustomersTagsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
        functionLibrary=new FunctionLibrary(driver);
        actions=new Actions(driver);
        logger= Logger.getLogger(RefundsPage.class.getName());
    }
    @FindBy(xpath = "//span[text()='Reports']")
    WebElement reportsTab;
    @FindBy(xpath = "//span[text()='Tags']")
    WebElement tagsLink;
    @FindBy(xpath = "//*[@id=\"nav\"]/li[2]/ul/li[5]/ul/li/a/span[text()='Customers']")
    WebElement customersTags;
    @FindBy(xpath = "//div/div/div/table/tbody/tr/td/h3[text()='Customers Tags']")
    WebElement customersTagsPage;
    @FindBy(xpath = "//select[@name='limit']")
    WebElement viewSelection;
    @FindBy(xpath = "//option[text()='200']")
    WebElement view200;
    @FindBy(xpath = "//a[text()='Show Tags']")
    WebElement showTagsLink;
    @FindBy(xpath = "//span[text()='Tag Name']")
    WebElement tagName;

    public void navigateToCustomersTagsPage(){
        functionLibrary.waitForElementVisible(reportsTab);
        actions.pause(2000).perform();
        actions.moveToElement(reportsTab).build().perform();
        functionLibrary.waitForElementVisible(tagsLink);
        actions.moveToElement(tagsLink).build().perform();
        functionLibrary.waitForElementVisible(customersTags);
        actions.pause(2000).perform();
        actions.moveToElement(customersTags).build().perform();
        customersTags.click();
    }
    public void viewTagsCustomersReport(){
        functionLibrary.waitForElementVisible(customersTagsPage);
        customersTagsPage.isDisplayed();
        functionLibrary.waitForElementVisible(viewSelection);
        actions.moveToElement(viewSelection).click().build().perform();
        functionLibrary.waitForElementVisible(view200);
        actions.moveToElement(view200).click().build();
        functionLibrary.waitForElementVisible(showTagsLink);
        actions.moveToElement(showTagsLink).click().build().perform();
    }
    public boolean verifyViewCustomersTagsReport() {
        if (tagName.isDisplayed()) {
            logger.info("See customers tags report successfully!");
            return true;
        } else
            logger.info("There are no customers tags !");
        return false;
    }
}
