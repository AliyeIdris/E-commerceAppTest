package com.seleniummastercucumber.pages.reportingmodule;

import com.seleniummastercucumber.utility.FunctionLibrary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * @author :
 * @created : 1/4/2024,9:07 AM
 * @Email : mukeremilyas@gmail.com.com
 **/
public class SeeTagsForProductPage {
    WebDriver driver;
    FunctionLibrary functionLibrary;

    public SeeTagsForProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        functionLibrary = new FunctionLibrary(driver);
    }
    @FindBy(xpath = "//span[text()='Reports']")
    WebElement reports;
    @FindBy(xpath = "//span[text()='Tags']")
    WebElement tags;
    @FindBy(xpath = "//span[text()='Products']")
    WebElement products;
    @FindAll(
            @FindBy(xpath = "//td[@class=' last']")
    )
    List<WebElement> listNumber;
    public void seeProductsTag(){
       Actions actions = new Actions(driver);
        functionLibrary.waitForElementVisible(reports);
        actions.moveToElement(reports).click().build().perform();
        functionLibrary.waitForElementVisible(tags);
        actions.moveToElement(tags).click().build().perform();
        functionLibrary.waitForElementVisible(products);
        products.click();
    }
    public boolean verifyProductsTags(){

        boolean flag;
        if (listNumber.isEmpty()){
            System.out.println("no any filter result");

            flag = false;
        }
        else {
            int listNum = listNumber.size();

            System.out.println("there are " + listNum + " tags name be found");

            flag = true;
        }
        return flag;
    }

}
