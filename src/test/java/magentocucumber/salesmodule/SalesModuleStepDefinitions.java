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
    AdminLoginPage loginPage=new AdminLoginPage(driver);
    AdminDashboardPage dashboardPage=new AdminDashboardPage(driver);
    ManageCustomersPage manageCustomersPage=new ManageCustomersPage(driver);
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

    @When("sales manager empty Customer's Shopping Cart {string}")
    public void salesManagerEmptyCustomerSShoppingCart(String sku) {
        manageCustomersPage.emptyShoppingCart(sku);
    }

    @Then("sales manager should be able to manage customer's shopping cart successfully")
    public void salesManagerShouldBeAbleToManageCustomerSShoppingCartSuccessfully() {
        Assert.assertTrue(manageCustomersPage.verifyManageShoppingCart());
    }

    @Given("manager got to product page")
    public void managerGotToProductPage() {
    }
}
