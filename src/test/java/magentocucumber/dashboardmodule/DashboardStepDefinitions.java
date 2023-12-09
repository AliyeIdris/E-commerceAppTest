package magentocucumber.dashboardmodule;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import magentocucumber.universalfunctions.BasePage;
import org.junit.Assert;

import static magentocucumber.universalfunctions.FileUtility.readConfig;

/**
 * @author : user
 * @created : 9.12.2023,14:43
 * @Email :aliyeidiris@gmail.com
 **/
public class DashboardStepDefinitions extends BasePage {
    AdminLoginPage loginPage=new AdminLoginPage(driver);
    AdminDashboardPage dashboardPage=new AdminDashboardPage(driver);
    String username; String password;
    //*************************** Background ***************************
    @Given("Admin user navigate to the admin login panel")
    public void adminUserNavigateToTheAdminLoginPanel() {
        Assert.assertTrue(loginPage.loginPageTitle.isDisplayed());
    }
    //*************************** SalesManagerLogin ***************************
    @Given("sales manager have valid credentials to login")
    public void salesManagerHaveValidCredentialsToLogin() {
        username= readConfig("salesmanager");
        password=readConfig("password");

    }

    @When("sales manager enter valid username and valid password")
    public void salesManagerEnterValidUsernameAndValidPassword() {
        loginPage.enterUserName(username);
        loginPage.enterPassword(password);
    }
    @And("sales manager click on login button")
    public void salesManagerClickOnLoginButton() {
        loginPage.clickOnLoginButton();
    }
    @Then("sales manager should be on the dashboard page.")
    public void salesManagerShouldBeOnTheDashboardPage() {
        Assert.assertTrue(dashboardPage.verifySalesManagerDashboardPage());
    }
}
