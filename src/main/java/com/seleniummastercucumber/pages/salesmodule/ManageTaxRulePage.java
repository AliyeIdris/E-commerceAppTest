package com.seleniummastercucumber.pages.salesmodule;

import com.seleniummastercucumber.utility.FunctionLibrary;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ManageTaxRulePage {
    WebDriver driver;
    FunctionLibrary functionLibrary;
    Logger logger;
    Actions actions;
    Select select;

    @FindBy(xpath = "//span[text()='Sales']")
    WebElement salesTab;
    @FindBy(xpath = "//span[text()='Tax']")
    WebElement taxTab;
    @FindBy(xpath = "//span[contains(text(), 'Manage Tax Rules')]")
    WebElement manageTaxRuleTab;
    @FindBy(xpath = "//div[@class='middle']//button[@title='Add New Tax Rule']")
    WebElement addTaxRuleButton;
    @FindBy(xpath = "//input[@id='code']")
    WebElement nameField;
    @FindBy(xpath = "//select[@id='tax_customer_class']")
    WebElement taxCustomerClassList;
    @FindBy(xpath = "//select[@id='tax_product_class']")
    WebElement taxProductClassList;
    @FindBy(xpath = "//select[@id='tax_rate']")
    WebElement taxRateList;
    @FindBy(xpath = "//input[@id='priority']")
    WebElement priorityField;
    @FindBy(xpath = "//input[@id='calculate_subtotal']")
    WebElement calculateOffSubtotalOnly;
    @FindBy(xpath = "//input[@id='position']")
    WebElement sortOrderField;
    @FindBy(xpath = "//button//span[contains(text(), 'Save Rule')]")
    WebElement saveButton;

    public ManageTaxRulePage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);
        functionLibrary=new FunctionLibrary(driver);
        logger=Logger.getLogger(ManageTaxRulePage.class);
        actions=new Actions(driver);

    }

    public void navigateToManageTaxRulePage() {
        functionLibrary.waitForElementVisible(salesTab);
        actions.click(salesTab).build().perform();
        functionLibrary.waitForElementVisible(taxTab);
        actions.click(taxTab).build().perform();
        functionLibrary.waitForElementVisible(manageTaxRuleTab);
        actions.click(manageTaxRuleTab).build().perform();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void addTaxRule(String taxRuleName,int indexNumber){
        functionLibrary.waitForElementVisible(addTaxRuleButton);
        actions.click(addTaxRuleButton).build().perform();
        functionLibrary.waitForElementVisible(nameField);
        nameField.sendKeys(taxRuleName);
        functionLibrary.waitForElementVisible(taxCustomerClassList);
        select=new Select(taxCustomerClassList);
        select.selectByIndex(indexNumber);
        functionLibrary.waitForElementVisible(taxProductClassList);
        select=new Select(taxProductClassList);
        select.selectByIndex(indexNumber);
        functionLibrary.waitForElementVisible(taxRateList);
        select=new Select(taxRateList);
        select.selectByIndex(indexNumber);
        functionLibrary.waitForElementVisible(priorityField);
        priorityField.sendKeys(String.valueOf(indexNumber));
        functionLibrary.waitForElementVisible(calculateOffSubtotalOnly);
        calculateOffSubtotalOnly.click();
        functionLibrary.waitForElementVisible(sortOrderField);
        sortOrderField.sendKeys(String.valueOf(indexNumber));
        functionLibrary.waitForElementVisible(saveButton);
        actions.click(saveButton).build().perform();

    }

}
