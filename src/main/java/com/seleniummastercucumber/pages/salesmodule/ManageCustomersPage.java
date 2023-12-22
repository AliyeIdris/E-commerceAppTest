package com.seleniummastercucumber.pages.salesmodule;

import com.seleniummastercucumber.utility.FunctionLibrary;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
    @FindBy(xpath = "(//span[text()='Customers'])[1]")
    WebElement customersMenu;
    @FindBy(xpath = "//span[text()='Manage Customers']")
    WebElement manageCustomersLink;
    @FindBy(id = "customer_info_tabs_cart")
    WebElement shoppingCartLink;
    @FindBy(xpath = "(//tr[@class='even']/td[text()='No records found.'])[3]")
    WebElement noRecordFoundMessage;
    @FindBy(id = "customerGrid_filter_email")
    WebElement emailSearchField;
    @FindBy(xpath = "//span[text()='Search']")
    WebElement searchButton;
    @FindAll(
            @FindBy(linkText = "Delete")
    )
    List<WebElement> deleteLinks;
    int linkSize;
    public ManageCustomersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        functionLibrary = new FunctionLibrary(driver);
        actions = new Actions(driver);
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
        WebElement customerLocation= driver.findElement(By.xpath(String.format("//*[@id=\"customerGrid_table\"]/tbody/tr/td[contains(text(),'%s')]", customerEmail)));
        functionLibrary.waitForElementVisible(customerLocation);
        customerLocation.click();
        functionLibrary.waitForElementVisible(shoppingCartLink);
        shoppingCartLink.click();
    }
    public void emptyShoppingCart() {
        linkSize = deleteLinks.size();
        try {
            if (noRecordFoundMessage.isDisplayed()) {
                System.out.println("Shopping cart is empty!");
            } else {
                deleteLinks.get(new Random().nextInt(deleteLinks.size())).click();
                functionLibrary.waitAlertPresent();
                driver.switchTo().alert().accept();
            }
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }

    public boolean verifyManageShoppingCart() {
        return noRecordFoundMessage.isDisplayed() || linkSize - deleteLinks.size() == 1;
    }
}
