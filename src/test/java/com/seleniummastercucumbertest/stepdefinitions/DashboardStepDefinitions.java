package com.seleniummastercucumbertest.stepdefinitions;

import com.seleniummastercucumber.pages.dashboardmodule.AdminDashboardPage;
import com.seleniummastercucumber.pages.dashboardmodule.AdminLoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.seleniummastercucumber.utility.BasePage;
import org.junit.Assert;

import static com.seleniummastercucumber.utility.FileUtility.readConfig;

/**
 * @author : user
 * @created : 9.12.2023,14:43
 * @Email :aliyeidiris@gmail.com
 **/
public class DashboardStepDefinitions extends BasePage {
    AdminLoginPage loginPage = new AdminLoginPage(driver);
    AdminDashboardPage dashboardPage = new AdminDashboardPage(driver);
    String username; String password;

    //*************************** Background ***************************
    @Given("Admin user navigate to the admin login panel")
    public void admin_user_navigate_to_the_admin_login_panel() {
        Assert.assertTrue(loginPage.loginPageTitle.isDisplayed());
    }
    //*************************** SalesManagerLogin ***************************
    @Given("sales manager have valid credentials to login")
    public void sales_manager_have_valid_credentials_to_login() {
        username= readConfig("salesmanager");
        password=readConfig("password");

    }
    @When("sales manager enter valid username and valid password")
    public void sales_manager_enter_valid_username_and_valid_password() {
        loginPage.enterUserName(username);
        loginPage.enterPassword(password);
    }
    @When("sales manager click on login button")
    public void sales_manager_click_on_login_button() {
        loginPage.clickOnLoginButton();
    }
    @Then("sales manager should be on the dashboard page.")
    public void sales_manager_should_be_on_the_dashboard_page()  {
        Assert.assertTrue(dashboardPage.verifySalesManagerDashboardPage());
    }
}
