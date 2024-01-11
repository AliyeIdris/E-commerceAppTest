package com.seleniummastercucumber.pages.reportingmodule;

import com.seleniummastercucumber.pages.salesmodule.RefundsPage;
import com.seleniummastercucumber.utility.FunctionLibrary;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.logging.Logger;

public class ProductInCartPage {
    WebDriver driver;
    FunctionLibrary functionLibrary;
    Actions actions;
    Logger logger;
    public ProductInCartPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
        functionLibrary=new FunctionLibrary(driver);
        Actions actions=new Actions(driver);
        logger= Logger.getLogger(RefundsPage.class.getName());
    }
    @FindBy(xpath = "//span[text()='Reports']")
    WebElement reportsTab;
    @FindBy(xpath = "//span[text()='Shopping Cart']")
    WebElement shoppingCartLink;
    @FindBy(xpath = "//span[text()='Products in carts']")
    WebElement productsInCartsLink;
    @FindBy(xpath = "//div/div/div/table/tbody/tr/td/h3[text()='Products in carts']")
    WebElement productsInCartsPage;

    public void navigateToProductInCartsPage(){
        functionLibrary.waitForElementVisible(reportsTab);
        actions.moveToElement(reportsTab).build().perform();
        functionLibrary.waitForElementVisible(shoppingCartLink);
        actions.moveToElement(shoppingCartLink).build().perform();
        functionLibrary.waitForElementVisible(productsInCartsLink);
        actions.moveToElement(productsInCartsLink).click().build().perform();
    }
    public void viewProductInCarts(){

    }
}
