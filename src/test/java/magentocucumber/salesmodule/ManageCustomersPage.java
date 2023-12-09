package magentocucumber.salesmodule;

import magentocucumber.universalfunctions.FunctionLibrary;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author : user
 * @created : 9.12.2023,16:17
 * @Email :aliyeidiris@gmail.com
 **/
public class ManageCustomersPage {
    WebDriver driver;
    FunctionLibrary functionLibrary;
    Actions actions;
    WebElement deleteLink;
    public ManageCustomersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        functionLibrary=new FunctionLibrary(driver);
        actions=new Actions(driver);
    }
    @FindBy(xpath = "(//span[text()='Customers'])[1]")
    WebElement customersMenu;
    @FindBy(xpath = "//span[text()='Manage Customers']")
    WebElement manageCustomersLink;
    @FindBy(id = "customer_info_tabs_cart")
    WebElement shoppingCartLink;

    public void navigateToCustomerShoppingCartPage(String customerEmail){
        functionLibrary.waitForElementVisible(customersMenu);
        actions.moveToElement(customersMenu).perform();
        functionLibrary.waitForElementVisible(manageCustomersLink);
        manageCustomersLink.click();
        WebElement customerLocation=driver.findElement(By.xpath(
                String.format("//*[@id=\"customerGrid_table\"]/tbody/tr/td[contains(text(),'%s')]",customerEmail)));
        functionLibrary.waitForElementVisible(customerLocation);
        customerLocation.click();
        functionLibrary.waitForElementVisible(shoppingCartLink);
        shoppingCartLink.click();
    }
    public void emptyShoppingCart(String sku){
        deleteLink=driver.findElement(By.xpath(String.format("//td[contains(text(),'%s')]//following-sibling::td/a[text()='Delete']",sku)));
        functionLibrary.waitForElementVisible(deleteLink);
        deleteLink.click();
        functionLibrary.waitAlertPresent();
        driver.switchTo().alert().accept();
    }
    public boolean verifyManageShoppingCart(){
        return !deleteLink.isDisplayed();
    }
}
