package magentocucumber.dashboardmodule;

import magentocucumber.universalfunctions.FunctionLibrary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static magentocucumber.universalfunctions.FileUtility.readConfig;

/**
 * @author : user
 * @created : 9.12.2023,14:29
 * @Email :aliyeidiris@gmail.com
 **/
public class AdminLoginPage {
    WebDriver driver;
    FunctionLibrary functionLibrary;

    public AdminLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        functionLibrary = new FunctionLibrary(driver);
    }
    @FindBy(xpath = "//h2[text()='Log in to Admin Panel']")
    WebElement loginPageTitle;
    @FindBy(css = "#username")
    WebElement usernameField;
    @FindBy(css = "#login")
    WebElement passwordField;
    @FindBy(xpath = "//input[@value='Login']")
    WebElement loginButton;
    @FindBy(xpath = "//span[text()='You did not sign in correctly or your account is temporarily disabled.']")
    WebElement invalidCredentialMessage;
    @FindBy(xpath = "//div[text()='This is a required field.']")
    WebElement requiredFieldMessage;
    @FindBy(linkText = "Forgot your password?")
    WebElement forgotPasswordLink;
    @FindBy(xpath = "//span[text()='Retrieve Password']")
    WebElement retrievePasswordButton;
    public void enterUserName(String username){
        functionLibrary.waitForElementVisible(usernameField);
        usernameField.sendKeys(username);
    }
    public void enterPassword(String password){
        functionLibrary.waitForElementVisible(passwordField);
        passwordField.sendKeys(password);
    }
    public void clickOnLoginButton(){
        functionLibrary.waitForElementVisible(loginButton);
        loginButton.click();
    }
    public void login(AdminRole adminRole){
        switch (adminRole){
            case SALES_MANAGER -> {
                enterUserName(readConfig("salesmanager"));
            }
            case REPORTINGMANAGER -> {
                enterUserName(readConfig("reportingmanager"));
            }
        }
        enterPassword(readConfig("password"));
        clickOnLoginButton();
    }

}
