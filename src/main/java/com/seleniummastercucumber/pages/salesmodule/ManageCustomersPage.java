package com.seleniummastercucumber.pages.salesmodule;

import com.seleniummastercucumber.utility.FunctionLibrary;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

/**
 * @author : user
 * @created : 9.12.2023,16:17
 * @Email :aliyeidiris@gmail.com
 **/
public class ManageCustomersPage {
    WebDriver driver;
    FunctionLibrary functionLibrary;
    Actions actions;
    Logger logger;
    Select select;
    public ManageCustomersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        functionLibrary = new FunctionLibrary(driver);
        actions = new Actions(driver);
        logger = Logger.getLogger(ManageCustomersPage.class.getName());
    }
    @FindBy(xpath = "(//span[text()='Customers'])[1]")
    WebElement customersMenu;
    @FindBy(xpath = "//span[text()='Manage Customers']")
    WebElement manageCustomersLink;
    @FindBy(id = "customer_info_tabs_cart")
    WebElement shoppingCartLink;
    @FindAll(
            @FindBy(xpath = "(//tr[@class='even']/td[text()='No records found.'])[3]")
    )
    List<WebElement> noRecordFoundMessage;
    @FindBy(id = "customerGrid_filter_email")
    WebElement emailSearchField;
    @FindBy(xpath = "//span[text()='Search']")
    WebElement searchButton;
    @FindAll(
            @FindBy(xpath = "//a[text()='Delete']")
    )
    List<WebElement> deleteLinks;
    @FindBy(xpath = "//*[text()='Edit']")
    WebElement editIcon;
    @FindBy(xpath = "//td/a[text()='Configure']")
    WebElement configureIcon;
    @FindBy(id = "attribute180")
    WebElement sizeSelectField;
    @FindBy(xpath = "//span[text()='OK']")
    WebElement okButton;
    @FindBy(id = "product_composite_configure_input_qty")
    WebElement quantity;
    @FindAll(@FindBy(xpath = "//dd[@class='last']/div/select/option"))
    List<WebElement> sizeSelectOptions;
    String eachSize;

    int linkSize;

    public int getLinkSize() {
        return linkSize;
    }

    public void setLinkSize(int linkSize) {
        this.linkSize = linkSize;
    }

    public void navigateToCustomerShoppingCartPage(String customerEmail) {
        functionLibrary.waitForElementVisible(customersMenu);
        actions.moveToElement(customersMenu).perform();
        functionLibrary.waitForElementVisible(manageCustomersLink);
        manageCustomersLink.click();
        functionLibrary.waitForElementVisible(emailSearchField);
        emailSearchField.sendKeys(customerEmail);
        functionLibrary.waitForElementVisible(searchButton);
        searchButton.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement customerLocation = driver.findElement(By.xpath(String.format("//*[@id=\"customerGrid_table\"]/tbody/tr/td[contains(text(),'%s')]", customerEmail)));
        functionLibrary.waitForElementVisible(customerLocation);
        customerLocation.click();
        functionLibrary.waitForElementVisible(shoppingCartLink);
        shoppingCartLink.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void emptyShoppingCart() {
        setLinkSize(deleteLinks.size());
        if (noRecordFoundMessage.size() == 1) {
            logger.info("Shopping cart is empty!");
        } else {
            deleteLinks.get(new Random().nextInt(deleteLinks.size())).click();
            functionLibrary.waitAlertPresent();
            driver.switchTo().alert().accept();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean verifyManageShoppingCart() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<WebElement> deleteLinks = driver.findElements(By.xpath("//a[text()='Delete']"));
        return getLinkSize() > deleteLinks.size() || noRecordFoundMessage.size() > 0;
    }

    public void navigateToShoppingCartPage(String customerEmail) {
        functionLibrary.waitForElementVisible(customersMenu);
        actions.moveToElement(customersMenu).perform();
        functionLibrary.waitForElementVisible(manageCustomersLink);
        manageCustomersLink.click();
        functionLibrary.waitForElementVisible(emailSearchField);
        emailSearchField.sendKeys(customerEmail);
        functionLibrary.waitForElementVisible(searchButton);
        searchButton.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        editIcon.click();
        functionLibrary.waitForElementVisible(shoppingCartLink);
        shoppingCartLink.click();
    }

    public void updateShoppingCart(int quantityCount) {
        functionLibrary.waitForElementVisible(configureIcon);
        configureIcon.click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        quantity.clear();
        quantity.sendKeys(String.valueOf(quantityCount));
        functionLibrary.waitForElementVisible(okButton);
        okButton.click();
        driver.navigate().refresh();
    }

    public boolean verifyUpdatedShoppingCart(int quantityCount) {
        functionLibrary.waitForElementVisible(shoppingCartLink);
        shoppingCartLink.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement updatedQuantityCount=driver.findElement(By.xpath(String.format("//table[@class='data']/tbody/tr/td[contains(text(),'%d')]",quantityCount)));
        if (updatedQuantityCount.isDisplayed()) {
            logger.info("Quantity of product updated to " +quantityCount);
               return true;
        } else {
            logger.info("Update failed ");
            return false;
        }
    }
}





