package com.seleniummastercucumbertest.stepdefinitions;

import com.seleniummastercucumber.pages.dashboardmodule.AdminDashboardPage;
import com.seleniummastercucumber.pages.dashboardmodule.AdminLoginPage;
import com.seleniummastercucumber.pages.dashboardmodule.AdminRole;
import com.seleniummastercucumber.pages.reportingmodule.*;
import com.seleniummastercucumber.utility.BasePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class ReportingModuleStepDefinitions extends BasePage {
    AdminLoginPage adminLoginPage =new AdminLoginPage(driver);
    AdminDashboardPage adminDashboardPage=new AdminDashboardPage(driver);
    ReportingDashboardPage dashboardPage=new ReportingDashboardPage(driver);
    ReportViewPage reportViewPage=new ReportViewPage(driver);
    DownloadsPage downloadsPage =new DownloadsPage(driver);
    ProductsReviewsPage reviewsPage=new ProductsReviewsPage(driver);
    OrderReportPage orderReportPage=new OrderReportPage(driver);
    TaxReportPage taxReportPage=new TaxReportPage(driver);
    CustomersOrdersTotalPage customersOrdersTotalPage=new CustomersOrdersTotalPage(driver);
    NewAccountsPage newAccountsPage=new NewAccountsPage(driver);
    ProductsOrderedPage productsOrderedPage=new ProductsOrderedPage(driver);
    ProductsMostViewedPage productsMostViewedPage=new ProductsMostViewedPage(driver);
    InvoicedPage invoicedPage=new InvoicedPage(driver);

     @Given("report manager login")
     public void reportManagerLogin() {
         adminLoginPage.login(AdminRole.REPORTINGMANAGER);
     }

    @And("report manager is on the dashboard page")
    public void reportManagerIsOnTheDashboardPage() {
        Assert.assertTrue(adminDashboardPage.verifyReportingManagerDashboardPage());
    }
    @Given("reporting manager is on the admin page and clicks shipping report")
    public void reportingManagerIsOnTheAdminPageAndClicksShippingReport() {
        dashboardPage.openShippingPage();
    }

    @When("reporting manager fills out report date for the shipped orders {string} and{string}")
    public void reportingManagerFillsOutReportDateForTheShippedOrdersAnd(String arg0, String arg1) {
        reportViewPage.viewTotalShippedReport(arg0,arg1);
    }

    @Then("total shipped report should appear")
    public void totalShippedReportShouldAppear() {
        reportViewPage.confirmIsReportAppeared();
    }

    //*****************************
    @Given("reporting manager is on the admin dashboard page")
    public void reportingManagerIsOnTheAdminDashboardPage() {
         Assert.assertTrue(adminDashboardPage.verifyReportingManagerDashboardPage());
    }

    @When("reporting manager downloads the existing reports")
    public void reportingManagerDownloadsTheExistingReports() {
        downloadsPage.downloadProducts();
    }

    @Then("Products reports should be see able")
    public void productsReportsShouldBeSeeAble() {
        downloadsPage.verifyProductsDownloadsReport();
    }

    @Given("reporting manager is on the dashboard page")
    public void reportingManagerIsOnTheDashboardPage() {
    }

    @When("reporting manager opens the reviews page and sees the reviews")
    public void reportingManagerOpensTheReviewsPageAndSeesTheReviews() {
        reviewsPage.setProductsReviews();
    }

    @Then("products reviews should be see able")
    public void productsReviewsShouldBeSeeAble() {
        Assert.assertTrue(reviewsPage.verifyProductsReviews());
    }


    @When("reporting manager navigate to low stock page")
    public void reportingManagerNavigateToLowStockPage() {
         dashboardPage.navigateToLowStockPage();
    }

    @Then("products low stock report should be display")
    public void productsLowStockReportShouldBeDisplay() {
         Assert.assertTrue(dashboardPage.viewAllLowStock());
    }

    @Given("reporting manager navigate to order report page")
    public void reportingManagerNavigateToOrderReportPage() {
        dashboardPage.goToOrderedReportPage();
    }
    @When("reporting manager filter total order report with date")
    public void reportingManagerFilterTotalOrderReportWithDate() {
         orderReportPage.seeTotalOrderedReport();
    }

    @Then("total order report should be displayed")
    public void totalOrderReportShouldBeDisplayed() {
         orderReportPage.verifyDisplayedTotalOrderReport();
    }

    //*************************** View Tax Report Grouped By Tax Rate ***************************
    @Given("reporting manager is on the Order Taxes Report Page")
    public void reportingManagerIsOnTheOrderTaxesReportPage() {
         dashboardPage.navigateToTaxReportPage();
    }

    @When("reporting manager fills the filter options with {string} and {string}")
    public void reportingManagerFillsTheFilterOptionsWithAnd(String dateFrom, String dateTo) {
         taxReportPage.filterTaxReportDate(dateFrom,dateTo);
    }

    @Then("reporting manager should be able to see the Sales-Taxes Report Grouped By Tax Rate")
    public void reportingManagerShouldBeAbleToSeeTheSalesTaxesReportGroupedByTaxRate() {
         Assert.assertTrue(taxReportPage.verifyTaxReportViewedSuccessfully());
    }
    //*************************** View Customers By Order Total ***************************
    @Given("reporting manager is on the Customers by Orders Total page")
    public void reportingManagerIsOnTheCustomersByOrdersTotalPage() {
         dashboardPage.navigateToCustomersOrderTotalPage();
    }

    @When("reporting manager apply filters for the report")
    public void reportingManagerApplyFiltersForTheReport() {
         String startDate="1/10/2023";
         String endDate="31/12/2025";
         customersOrdersTotalPage.applyFilterToReport(startDate,endDate);
    }

    @Then("reporting manager should be able to see Customers - Customers by Orders Total Report")
    public void reportingManagerShouldBeAbleToSeeCustomersCustomersByOrdersTotalReport() {
         customersOrdersTotalPage.verifyViewCustomersByTotalOrdersReport();
    }

    @Given("reporting manager is on the New Accounts page")
    public void reportingManagerIsOnTheNewAccountsPage() {
         dashboardPage.navigateToNewAccountsPage();
    }

    @When("reporting manager filter customer report and search for it")
    public void reportingManagerFilterCustomerReportAndSearchForIt() {
         newAccountsPage.applyFilterToReportNewAccounts("01/01/2022","12/28/2023");
    }

    @Then("reporting manager should be able to see Customers Report-New Accounts Report")
    public void reportingManagerShouldBeAbleToSeeCustomersReportNewAccountsReport() {
         newAccountsPage.verifyViewNewCustomerReport();
    }

    @Given("reporting manager is on the Products Ordered page")
    public void reportingManagerIsOnTheProductsOrderedPage() {
         dashboardPage.navigateToProductsOrderedPage();
    }

    @When("reporting manager filter Products Ordered Report by date {string} and{string}")
    public void reportingManagerFilterProductsOrderedReportByDateAnd(String arg0, String arg1) {
     productsOrderedPage.viewProductsOrderedReport(arg0,arg1);
    }

    @Then("reporting manager should be able to see Products Ordered Report")
    public void reportingManagerShouldBeAbleToSeeProductsOrderedReport() {
         productsOrderedPage.verifyProductsOrderedReportDisplayed();
    }

    @When("reporting manager navigate to most viewed page data with {string} and{string}")
    public void reportingManagerNavigateToMostViewedPageDataWithAnd(String arg0, String arg1) {
        productsMostViewedPage.mostViewedProducts(arg0,arg1);
    }

    @Then("most viewed products should be displayed")
    public void mostViewedProductsShouldBeDisplayed() {
        Assert.assertTrue(productsMostViewedPage.checkMostViewedProducts());
    }

//abdugeni *****************able to see Sales - Total Invoiced vs Paid Report
    @Given("Reporting Manager Navigate To Total Invoiced vs Paid Report page")
    public void reportingManagerNavigateToTotalInvoicedVsPaidReportPage() {
         dashboardPage.navigateToInvoicedPage();
    }

    @When("Fill Out Criteria For Search")
    public void fillOutCriteriaForSearch() {
         invoicedPage.showReport("27","Last Invoice Created Date","Month",
                 "01/08/2014","01/08/2024");
    }

    @Then("Reporting Manager Can see Sales - Total Invoiced vs Paid Report")
    public void reportingManagerCanSeeSalesTotalInvoicedVsPaidReport() {
         Assert.assertTrue(invoicedPage.verifyReport());
    }

// abdugeni ********************can see coupons usage report

    @Given("The reporting manager opens coupons page")
    public void theReportingManagerOpensCouponsPage() {
    }

    @When("the coupons report should be displayed")
    public void theCouponsReportShouldBeDisplayed() {
    }

    @And("the manager fills in the filter details{string} {string} {string}")
    public void theManagerFillsInTheFilterDetails(String arg0, String arg1, String arg2) {
    }

    @Then("no records found displayed")
    public void noRecordsFoundDisplayed() {
    }
}
