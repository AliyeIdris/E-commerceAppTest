package magentocucumber.salesmodule;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import magentocucumber.dashboardmodule.AdminDashboardPage;
import magentocucumber.dashboardmodule.AdminLoginPage;
import magentocucumber.dashboardmodule.AdminRole;
import magentocucumber.universalfunctions.BasePage;
import org.junit.Assert;

/**
 * @author : user
 * @created : 9.12.2023,16:08
 * @Email :aliyeidiris@gmail.com
 **/
public class SalesModuleStepDefinitions extends BasePage {
    AdminLoginPage loginPage = new AdminLoginPage(driver);
    AdminDashboardPage dashboardPage = new AdminDashboardPage(driver);
    ManageCustomersPage manageCustomersPage = new ManageCustomersPage(driver);

    CouponsPage couponsPage=new CouponsPage(driver);

    //*************************** Background ***************************
    @Given("sales manager login")
    public void salesManagerLogin() {
        loginPage.login(AdminRole.SALES_MANAGER);
    }

    @And("sales manager on the dashboard page")
    public void salesManagerOnTheDashboardPage() {
        Assert.assertTrue(dashboardPage.verifySalesManagerDashboardPage());
    }

    //*************************** Manage Shopping Cart ***************************
    @Given("sales manager is on customer's shopping cart page {string}")
    public void salesManagerIsOnCustomerSShoppingCartPage(String customerEmail) {
        manageCustomersPage.navigateToCustomerShoppingCartPage(customerEmail);
    }

    @When("sales manager empty Customer's Shopping Cart")
    public void salesManagerEmptyCustomerSShoppingCart() {
        manageCustomersPage.emptyShoppingCart();
    }

    @Then("sales manager should be able to manage customer's shopping cart successfully")
    public void salesManagerShouldBeAbleToManageCustomerSShoppingCartSuccessfully() {
        Assert.assertTrue(manageCustomersPage.verifyManageShoppingCart());
    }

    @Given("manager got to product page")
    public void managerGotToProductPage() {
    }

    @Given("sales manager is on the reports page")
    public void salesManagerIsOnTheReportsPage() {
        couponsPage.openReportsPage();

    }

    @When("Sales manager Click on Reports move to reports and move to Sales and select coupons fill in {string}and{string}field")
    public void salesManagerClickOnReportsMoveToReportsAndMoveToSalesAndSelectCouponsFillInAndField(String arg0, String arg1) {
        couponsPage.viewCouponsReports(arg0,arg1);
    }

    @Then("Total coupons Report page should display with information")
    public void totalCouponsReportPageShouldDisplayWithInformation() {
        couponsPage.verifyCouponsReports();
    }
}

