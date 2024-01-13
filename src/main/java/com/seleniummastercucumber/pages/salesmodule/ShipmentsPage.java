package com.seleniummastercucumber.pages.salesmodule;

import com.seleniummastercucumber.utility.FunctionLibrary;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

/**
 * @author :merve
 * @created : 04/01/2024,00:56
 * @project : SDET2023Magento_Team1Cucumber
 */
public class ShipmentsPage {
    WebDriver driver;
    FunctionLibrary functionLibrary;
    Actions actions;
    Logger logger;

    public ShipmentsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        functionLibrary = new FunctionLibrary(driver);
        actions = new Actions(driver);
        logger = Logger.getLogger(ManageCustomersPage.class.getName());
    }

    @FindBy(xpath = "//span[text()=\"Sales\"]")
    WebElement salesTap;
    @FindBy(xpath = "//span[text()=\"Shipments\"]")
    WebElement shipmentsLink;
    @FindAll(@FindBy(xpath = "//td[@class=\" last\"]"))
    List<WebElement> shipments;
    @FindBy(xpath = "//textarea[@id='history_comment']")
    WebElement commentTextField;
    @FindBy(xpath = "//input[@id='history_notify']")
    WebElement notifyCustomerCheckBox;
    @FindBy(xpath = "//input[@id='history_visible']")
    WebElement visibleOnFrontend;
    @FindBy(xpath = "//span[contains(text(),'Submit Comment')]")
    WebElement submitButton;
    @FindAll(@FindBy(xpath = "//select[@class=\"select\"]"))
    WebElement customerValue;
    @FindBy(id = "tracking_number")
    WebElement trackingNumberField;
    @FindBy(xpath = "//span[text()=\"Add\"]")
    WebElement addButton;
    @FindBy(xpath = "//span[contains(text(),'Send Tracking Information')]")
    WebElement sendTrackingInfo;
    @FindBy(xpath = "//span[text()=\"The shipment has been sent.\"]")
    WebElement sentInfoMessage;

    public void navigateToShipmentsMethod(){
        actions.moveToElement(salesTap).moveToElement(shipmentsLink).click().build().perform();
    }
    public void selectShipmentForView(){
        shipments.get(new Random().nextInt(shipments.size())).click();
    }
    public void updateShipmentsMethod(String commentText,String trackingNumber){
        actions.scrollToElement(commentTextField).build().perform();
        actions.click(commentTextField).sendKeys(commentText).build().perform();
        functionLibrary.waitForElementVisible(notifyCustomerCheckBox);
        actions.click(notifyCustomerCheckBox).build().perform();
        functionLibrary.waitForElementVisible(visibleOnFrontend);
        actions.click(visibleOnFrontend).build().perform();
        functionLibrary.waitForElementVisible(submitButton);
        actions.click(submitButton).build().perform();
        Select select=new Select(customerValue);
        select.selectByValue("ups");
        functionLibrary.waitForElementVisible(trackingNumberField);
        trackingNumberField.sendKeys(trackingNumber);
        functionLibrary.waitForElementVisible(addButton);
        functionLibrary.javaScriptClick(addButton);
        functionLibrary.waitForElementVisible(sendTrackingInfo);
        functionLibrary.javaScriptClick(sendTrackingInfo);
        driver.switchTo().alert().accept();
    }
    public boolean verifySentTrackingInfoMessage(){
        if (sentInfoMessage.isDisplayed()){
            logger.info("The shipment has been sent.");
            return true;
        }else
            return false;
    }
}