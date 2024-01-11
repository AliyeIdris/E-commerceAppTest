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
        Actions actions=new Actions(driver);
        logger= Logger.getLogger(RefundsPage.class.getName());
    }
    @FindBy(xpath = "//span[text()='Reports']")
    WebElement reportsTab;
    @FindBy(xpath = "//span[text()='Tags']")
    WebElement tagsLink;
    @FindBy(xpath = "//div/ul/li/ul/li/a/span[text()='Customers']")
    WebElement customersLink;
    @FindBy(xpath = "//div/div/div/table/tbody/tr/td/h3[text()='Customers Tags']")
    WebElement customersTagsPage;
    @FindBy(xpath = "//a[text()='Show Tags']")
    WebElement showTagsLink;

    public void navigateToCustomersTagsPage(){
        functionLibrary.waitForElementVisible(reportsTab);
        actions.moveToElement(reportsTab).build().perform();
        functionLibrary.waitForElementVisible(tagsLink);
        actions.moveToElement(tagsLink).build().perform();
        functionLibrary.waitForElementVisible(customersLink);
        actions.moveToElement(customersLink).click().build().perform();
    }
    public void viewTagsCustomersReport(){

    }


}
