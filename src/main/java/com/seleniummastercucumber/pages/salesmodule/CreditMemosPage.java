package com.seleniummastercucumber.pages.salesmodule;

import com.seleniummastercucumber.utility.FunctionLibrary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.logging.Logger;

public class CreditMemosPage {
    WebDriver driver;
    FunctionLibrary functionLibrary;
    Actions actions;
    Logger logger;

    public CreditMemosPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
        functionLibrary = new FunctionLibrary(driver);
        actions = new Actions(driver);
        logger = Logger.getLogger(RefundsPage.class.getName());
    }
    @FindBy(xpath = "//div/ul/li/a/span[text()='Sales']")
    WebElement salesTab;
    @FindBy(xpath = "//span[text()='Credit Memos']")
    WebElement creditMemos;
    @FindBy(xpath = "//span[text()='Reset Filter']")
    WebElement resetFilterTab;
    @FindBy(xpath = "//a[text()='View']")
    WebElement views;
    @FindBy(xpath = "//h4[text()='Credit Memo Totals']")
    WebElement creditMemosTotals;

    public void navigateToCreditMemosPage(){
        functionLibrary.waitForElementVisible(salesTab);
        actions.moveToElement(salesTab).build().perform();
        functionLibrary.waitForElementVisible(creditMemos);
        actions.moveToElement(creditMemos).click().build().perform();
    }
    public void viewCreditMemos(){
        functionLibrary.waitForElementVisible(resetFilterTab);
        actions.moveToElement(resetFilterTab).click().build().perform();
        functionLibrary.waitForElementVisible(views);
        actions.moveToElement(views).click().build().perform();
    }
    public boolean verifyCreditMemos(){
        if (creditMemosTotals.isDisplayed()){
            logger.info("view credit memos successfully!!!");
            return true;
        }else {
            logger.info("sorry!!! plz try again!!!");
            return false;
        }
    }
}
