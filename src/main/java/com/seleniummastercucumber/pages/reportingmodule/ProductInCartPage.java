package com.seleniummastercucumber.pages.reportingmodule;

import com.seleniummastercucumber.pages.salesmodule.RefundsPage;
import com.seleniummastercucumber.utility.FunctionLibrary;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
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
        actions=new Actions(driver);
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
    @FindBy(xpath = "//select[@name='limit']")
    WebElement viewPerPageSelection;
    @FindBy(xpath = "//select/option[text()='200']")
    WebElement view200option;
    @FindAll(@FindBy(xpath = "//table[@id=\"gridProducts_table\"]/tbody/tr"))
    List<WebElement> productsList;

    public void navigateToProductInCartsPage(){
        functionLibrary.waitForElementVisible(reportsTab);
        actions.moveToElement(reportsTab).build().perform();
        functionLibrary.waitForElementVisible(shoppingCartLink);
        actions.moveToElement(shoppingCartLink).build().perform();
        functionLibrary.waitForElementVisible(productsInCartsLink);
        actions.moveToElement(productsInCartsLink).click().build().perform();
    }
    public void openProductInCartPage(){
        functionLibrary.waitForElementVisible(productsInCartsPage);
        productsInCartsPage.isDisplayed();
        functionLibrary.waitForElementVisible(viewPerPageSelection);
        actions.moveToElement(viewPerPageSelection).click().build().perform();
        functionLibrary.waitForElementVisible(view200option);
        actions.moveToElement(view200option).click().build();
    }
    public boolean verifyViewProductsInShoppingCartsReport(){
        if (productsList.size()>=1){
            logger.info("View products in carts is successfully!");
            return true;
        }else{
            logger.info("There are no products in carts.");
        }
        return false;
    }
}
