package com.seleniummastercucumbertest.stepdefinitions;

import com.github.javafaker.Faker;
import com.seleniummastercucumber.pages.dashboardmodule.AdminDashboardPage;
import com.seleniummastercucumber.pages.dashboardmodule.AdminLoginPage;
import com.seleniummastercucumber.pages.dashboardmodule.AdminRole;
import com.seleniummastercucumber.pages.salesmodule.*;
import com.seleniummastercucumber.utility.BasePage;
import com.seleniummastercucumber.utility.TestDataHolder;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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
    InvoicesPage invoicesPage = new InvoicesPage(driver);
    CouponsPage couponsPage = new CouponsPage(driver);
    RefundsPage refundsPage = new RefundsPage(driver);
    ManageTaxRulePage manageTaxRulePage=new ManageTaxRulePage(driver);
    ShipmentsPage shipmentsPage =new ShipmentsPage(driver);
    CreditMemosPage creditMemosPage=new CreditMemosPage(driver);
    int quantity=(int)((Math.random())*50);

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
    public void salesManagerIsOnCustomerSShoppingCartPage(String email) {
        manageCustomersPage.navigateToCustomerShoppingCartPage(email);
    }

    @When("sales manager empty Customer's Shopping Cart")
    public void salesManagerEmptyCustomerSShoppingCart() {
        manageCustomersPage.emptyShoppingCart();
    }

    @Then("sales manager managed shopping cart successfully")
    public void salesManagerManagedShoppingCartSuccessfully() {
        manageCustomersPage.verifyManageShoppingCart();
    }

    @Given("sales manager is on the reports page")
    public void salesManagerIsOnTheReportsPage() {
        couponsPage.openReportsPage();

    }

    @When("Sales manager Click on Reports move to reports and move to Sales and select coupons fill in {string}and{string}field")
    public void salesManagerClickOnReportsMoveToReportsAndMoveToSalesAndSelectCouponsFillInAndField(String arg0, String arg1) {
        couponsPage.viewCouponsReports(arg0, arg1);
    }

    @Then("Total coupons Report page should display with information")
    public void totalCouponsReportPageShouldDisplayWithInformation() {
        couponsPage.verifyCouponsReports();
    }

    @Given("Sales manager is on the dashboard page and invoices are should be existing")
    public void salesManagerIsOnTheDashboardPageAndInvoicesAreShouldBeExisting() {
        Assert.assertTrue(dashboardPage.verifySalesManagerDashboardPage());
    }

    @When("Sales manager views invoices and adds comments")
    public void salesManagerViewsInvoicesAndAddsComments() {
        invoicesPage.viewInvoicesAndAddComments(TestDataHolder.comments);
    }

    @Then("Sales manager should be able to view invoices and add comments successfully")
    public void salesManagerShouldBeAbleToViewInvoicesAndAddCommentsSuccessfully() {
        invoicesPage.verifyInvoicesAndComment();
    }


    @Given("sales manager is on the refunds page")
    public void salesManagerIsOnTheRefundsPage() {
        refundsPage.navigateToRefundsPage();
    }

    @When("sales manager select on from and to date field and click on show report")
    public void salesManagerSelectOnFromAndToDateFillAndClickOnShowReport() {
        refundsPage.totalRefundedReport();
    }

    @Then("total refunded report should be displayed with information")
    public void totalRefundedReportShouldBeDisplayWithInformation() {
        Assert.assertTrue(refundsPage.viewRefundsInTheReports());
    }

    @Given("sales manager go to the shopping cart page with email {string}")
    public void salesManagerGoToTheShoppingCartPageWithEmail(String email) {
        manageCustomersPage.navigateToShoppingCartPage(email);
    }

    @When("sales manager update the existing shopping cart")
    public void salesManagerUpdateTheExistingShoppingCart() {
        manageCustomersPage.updateShoppingCart(quantity);
    }

    @Then("updated shopping cart info should be displayed")
    public void updatedShoppingCartInfoShouldBeDisplayed() {
        Assert.assertTrue("shopping cart does not updated ",manageCustomersPage.verifyUpdatedShoppingCart(quantity));
    }

    @Given("sales manager is on Manage Tax Rules page")
    public void salesManagerIsOnManageTaxRulesPage() {
        manageTaxRulePage.navigateToManageTaxRulePage();
    }

    @When("sales manager adds tax rule")
    public void salesManagerAddsTaxRule() {
        manageTaxRulePage.addTaxRule(TestDataHolder.taxRuleName,TestDataHolder.customerIndexNumber,
                TestDataHolder.productIndexNumber
                ,TestDataHolder.taxIndexNumber,String.valueOf(TestDataHolder.number));
    }
    @And("sales manager updates added tax rule")
    public void salesManagerUpdatesAddedTaxRule() {
        manageTaxRulePage.updateAddedTaxRule(TestDataHolder.taxRuleName,TestDataHolder.customerIndexNumber,
                TestDataHolder.productIndexNumber, TestDataHolder.taxIndexNumber,TestDataHolder.number,
                TestDataHolder.taxRuleName,TestDataHolder.taxRuleName+" updated");
    }
    @Then("tax rule should be added and updated successfully")
    public void salesManagerAddedAndUpdatedTaxRuleSuccessfully() {
        Assert.assertTrue(manageTaxRulePage.verifyAddedTaxRule());
        Assert.assertTrue(manageTaxRulePage.verifyUpdatedTaxRule());
    }

//abdugeni
    @Given("sales manager on the dashboard page and can navigate to shipments page")
    public void salesManagerOnTheDashboardPageAndCanNavigateToShipmentsPage() {
        shipmentsPage.navigateToShipmentsMethod();
    }

    @When("sales manager can view shipmentsList and random select one for update")
    public void salesManagerCanViewShipmentsListAndRandomSelectOneForUpdate() {
        shipmentsPage.selectShipmentForView();
    }

    @And("sales manager update shipments and send tracking information")
    public void salesManagerUpdateShipmentsAndSendTrackingInformation() {
        shipmentsPage.updateShipmentsMethod("this is istanbul team",
                TestDataHolder.timeStamp());
    }

    @Then("sales manager sent tracking information and info should be displayed")
    public void salesManagerSentTrackingInformationAndInfoShouldBeDisplayed() {
        shipmentsPage.verifySentTrackingInfoMessage();
    }

    @Given("sales manager is on the credit memos page")
    public void salesManagerIsOnTheCreditMemosPage() {
        creditMemosPage.navigateToCreditMemosPage();
    }

    @When("click on the reset filter and views tab")
    public void clickOnTheResetFilterAndViewsTab() {
        creditMemosPage.clickOnResetFilterAndViewsTab();
    }

    @Then("Sales manager should be able to view credit memos successfully")
    public void salesManagerShouldBeAbleToViewCreditMemosSuccessfully() {
        creditMemosPage.verifyCreditMemos();
    }

    @Then("sales manager add credit memos")
    public void salesManagerAddCreditMemos() {
       creditMemosPage.addCreditMemosComment(TestDataHolder.comments);
    }

    @Then("sales manager successfully added credit memos")
    public void salesManagerSuccessfullyAddedCreditMemos() {
        Assert.assertTrue(creditMemosPage.verifyAddedCreditMemos());
    }
}

