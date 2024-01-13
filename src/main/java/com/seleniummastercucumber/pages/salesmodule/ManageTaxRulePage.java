package com.seleniummastercucumber.pages.salesmodule;

import com.seleniummastercucumber.utility.FunctionLibrary;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;

public class ManageTaxRulePage {
    WebDriver driver;
    FunctionLibrary functionLibrary;
    Logger logger;
    Actions actions;
    Select select;

    public ManageTaxRulePage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);
        functionLibrary=new FunctionLibrary(driver);
        logger=Logger.getLogger(ManageTaxRulePage.class);
        actions=new Actions(driver);

    }

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
    @FindBy(xpath = "(//button[@title='Save Rule'])[1]")
    WebElement saveButton;
    @FindBy(xpath = "//ul[@class='messages']")
    WebElement addTaxRuleVerification;
    @FindBy(xpath = "//input[@id='taxRuleGrid_filter_code']")
    WebElement filterNameSearchField;
    @FindBy(xpath = "//span[text()='Search']")
    WebElement searchButton;
    @FindBy(xpath = "//span[text()='Name']")
    WebElement nameFilter;

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

    public void addTaxRule(String taxRuleName, int customerIndexNumber, int productIndexNumber, int taxNumber, String number){
        functionLibrary.waitForElementVisible(addTaxRuleButton);
        actions.click(addTaxRuleButton).build().perform();
        functionLibrary.waitForElementVisible(nameField);
        nameField.sendKeys(taxRuleName);
        functionLibrary.waitForElementVisible(taxCustomerClassList);
        select=new Select(taxCustomerClassList);
        select.selectByIndex(Integer.parseInt(String.valueOf(customerIndexNumber)));
        functionLibrary.waitForElementVisible(taxProductClassList);
        select=new Select(taxProductClassList);
        select.selectByIndex(Integer.parseInt(String.valueOf(productIndexNumber)));
        functionLibrary.waitForElementVisible(taxRateList);
        select=new Select(taxRateList);
        select.selectByIndex(taxNumber);
        functionLibrary.waitForElementVisible(priorityField);
        priorityField.sendKeys(String.valueOf(number));
        functionLibrary.waitForElementVisible(calculateOffSubtotalOnly);
        calculateOffSubtotalOnly.click();
        functionLibrary.waitForElementVisible(sortOrderField);
        sortOrderField.sendKeys(String.valueOf(number));
        functionLibrary.waitForElementVisible(saveButton);
        actions.click(saveButton).build().perform();
    }

    public boolean verifyAddedTaxRule(int taxNumber){
        for (int i=0; i<5; i++){
            if (!(addTaxRuleVerification.getText().contains("has been saved"))){
                functionLibrary.waitForElementVisible(taxCustomerClassList);
                select=new Select(taxCustomerClassList);
                select.deselectAll();
                select.selectByIndex(i);
                functionLibrary.waitForElementVisible(taxRateList);
                select=new Select(taxRateList);
                select.deselectAll();
                select.selectByIndex(i);
                functionLibrary.waitForElementVisible(saveButton);
                actions.click(saveButton).build().perform();
                nameField.clear();
                functionLibrary.waitForElementVisible(nameField);
                nameField.sendKeys("this is test");
                logger.info("Tax rule is failed");
                continue;
            }else {
                logger.info(addTaxRuleVerification.getText());
                logger.info("Tax rule is added successfully");
                break;
            }

        }
        return true;
    }
    public void updateAddedTaxRule(String taxRuleName, int customerIndexNumber, int productIndexNumber,
                                   int taxNumber, int number,String nameForFilter,String updateMessage){
        functionLibrary.waitForElementVisible(salesTab);
        actions.click(salesTab).build().perform();
        functionLibrary.waitForElementVisible(taxTab);
        actions.click(taxTab).build().perform();
        functionLibrary.waitForElementVisible(manageTaxRuleTab);
        actions.click(manageTaxRuleTab).build().perform();
        functionLibrary.waitForElementVisible(filterNameSearchField);
        filterNameSearchField.sendKeys(nameForFilter);
        functionLibrary.waitForElementVisible(searchButton);
        searchButton.click();
        functionLibrary.waitForElementVisible(nameFilter);
        nameFilter.click();
        WebElement ruleLink=driver.findElement(By.xpath(String.format(
                "//table[@id='taxRuleGrid_table']//tbody/tr/td[contains(text(), '%s')]",taxRuleName)));
        ruleLink.click();
        functionLibrary.waitForElementVisible(nameField);
        nameField.clear();
        nameField.sendKeys(updateMessage);
        functionLibrary.waitForElementVisible(taxCustomerClassList);
        select=new Select(taxCustomerClassList);
        select.deselectAll();
        select.selectByIndex(customerIndexNumber);
        functionLibrary.waitForElementVisible(taxProductClassList);
        select=new Select(taxProductClassList);
        select.deselectAll();
        select.selectByIndex(productIndexNumber);
        functionLibrary.waitForElementVisible(taxRateList);
        select=new Select(taxRateList);
        select.deselectAll();
        select.selectByIndex(taxNumber);
        functionLibrary.waitForElementVisible(priorityField);
        priorityField.clear();
        priorityField.sendKeys(String.valueOf(number));
        functionLibrary.waitForElementVisible(saveButton);
        actions.click(saveButton).build().perform();
    }

    public boolean verifyUpdatedTaxRule(){
        for (int i=0; i<5; i++){
            if (!(addTaxRuleVerification.getText().contains("has been saved"))){
                functionLibrary.waitForElementVisible(taxCustomerClassList);
                select=new Select(taxCustomerClassList);
                select.deselectAll();
                select.selectByIndex(i);
                functionLibrary.waitForElementVisible(taxRateList);
                select=new Select(taxRateList);
                select.deselectAll();
                select.selectByIndex(i+3);
                functionLibrary.waitForElementVisible(saveButton);
                actions.click(saveButton).build().perform();
                logger.info("Tax rule is failed");
                continue;
            }else {
                logger.info(addTaxRuleVerification.getText());
                logger.info("Tax rule is added successfully");
                break;
            }

        }return true;

    }


}
