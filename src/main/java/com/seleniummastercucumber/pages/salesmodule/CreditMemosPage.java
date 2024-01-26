package com.seleniummastercucumber.pages.salesmodule;

import com.seleniummastercucumber.utility.FunctionLibrary;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;
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
    @FindBy(name = "comment[comment]")
    WebElement commentLink;
    @FindBy(xpath = "//span[text()='Submit Comment']")
    WebElement submitCommentButton;
    @FindBy(xpath = "//p[@class='form-buttons']/button[@title='Send Email']")
    WebElement sendEmailButton;
    @FindBy(xpath = "//span[text()='The message was sent.']")
    WebElement commentSentSuccess;


    public void navigateToCreditMemosPage(){
        functionLibrary.waitForElementVisible(salesTab);
        actions.moveToElement(salesTab).build().perform();
        functionLibrary.waitForElementVisible(creditMemos);
        actions.moveToElement(creditMemos).click().build().perform();
    }
    public void clickOnResetFilterAndViewsTab(){
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

    public void addCreditMemosComment(String commentText){
        functionLibrary.waitForElementVisible(commentLink);
        commentLink.sendKeys(commentText);
        functionLibrary.waitForElementVisible(submitCommentButton);
        submitCommentButton.click();
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        functionLibrary.waitForElementVisible(sendEmailButton);
        sendEmailButton.click();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Alert alert =driver.switchTo().alert();
        alert.accept();
    }
    public boolean verifyAddedCreditMemos(){
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(commentSentSuccess.isDisplayed()){
            logger.info(commentSentSuccess.getText());
            return true;
        }else {
            logger.info("Credit Memos Comment False !!!");
            return false;
        }
    }


}
