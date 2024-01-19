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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    SeeTagsForProductPage seeTagsForProductsPage=new SeeTagsForProductPage(driver);
    ProductsOrderedPage productsOrderedPage=new ProductsOrderedPage(driver);
    ProductsMostViewedPage productsMostViewedPage=new ProductsMostViewedPage(driver);
    InvoicedPage invoicedPage=new InvoicedPage(driver);
    CouponsPage couponsPage=new CouponsPage(driver);
    ProductInCartPage productInCartPage=new ProductInCartPage(driver);
    CustomersTagsPage customersTagsPage=new CustomersTagsPage(driver);
    SeeProductBestSellerReport seeProductBestSellerReport=new SeeProductBestSellerReport(driver);


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
        Assert.assertTrue(reportViewPage.confirmIsReportAppeared());
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

    @Then("Products reports should be seeable")
    public void productsReportsShouldBeSeeAble() {
        Assert.assertTrue(downloadsPage.verifyProductsDownloadsReport());
    }

    @When("reporting manager opens the reviews page and sees the reviews")
    public void reportingManagerOpensTheReviewsPageAndSeesTheReviews() {
        reviewsPage.setProductsReviews();
    }

    @Then("products reviews should be seeable")
    public void productsReviewsShouldBeSeeAble() {
        Assert.assertTrue(reviewsPage.verifyProductsReviews());
    }


    @When("reporting manager navigate to low stock page")
    public void reportingManagerNavigateToLowStockPage() {
         dashboardPage.navigateToLowStockPage();
    }

    @Then("products low stock report should be displayed")
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
         Assert.assertTrue(orderReportPage.verifyDisplayedTotalOrderReport());
    }

    //*************************** View Tax Report Grouped By Tax Rate ***************************
    @Given("reporting manager is on the Order Taxes Report Page")
    public void reportingManagerIsOnTheOrderTaxesReportPage() {
         dashboardPage.navigateToTaxReportPage();
    }

    @When("reporting manager fills the filter options with {string} and {string}")
    public void reportingManagerFillsTheFilterOptionsWithAnd(String dateFrom) {
         taxReportPage.filterTaxReportDate(dateFrom);
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
         customersOrdersTotalPage.applyFilterToReport(startDate);
    }

    @Then("reporting manager should be able to see Customers - Customers by Orders Total Report")
    public void reportingManagerShouldBeAbleToSeeCustomersCustomersByOrdersTotalReport() {
         Assert.assertTrue(customersOrdersTotalPage.verifyViewCustomersByTotalOrdersReport());
    }

    @Given("reporting manager is on the New Accounts page")
    public void reportingManagerIsOnTheNewAccountsPage() {
         dashboardPage.navigateToNewAccountsPage();
    }

    @When("reporting manager filters customer report and searches for it")
    public void reportingManagerFilterCustomerReportAndSearchForIt() {
         LocalDate localDate=LocalDate.now();
         newAccountsPage.applyFilterToReportNewAccounts("01/01/2022",(localDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))));
    }

    @Then("reporting manager should be able to see Customers Report-New Accounts Report")
    public void reportingManagerShouldBeAbleToSeeCustomersReportNewAccountsReport() {
         Assert.assertTrue(newAccountsPage.verifyViewNewCustomerReport());
    }

    @Given("reporting manager should be navigate to dashboard page")
    public void reportingManagerShouldBeNavigateToDashboardPage() {

    }

    @When("reporting manager see tags for products")
    public void reportingManagerSeeTagsForProducts() {
        seeTagsForProductsPage.seeProductsTag();
    }

    @Then("products tag should be visible on the reporting page")
    public void productsTagShouldBeVisibleOnTheReportingPage() {
        seeTagsForProductsPage.verifyProductsTags();
    }

    @When("Reporting manager see bestseller report")
    public void reportingManagerSeeBestsellerReport() {
        seeProductBestSellerReport.clickOnProducts();
        seeProductBestSellerReport.openBestSellersReportPage();

    }

    @Then("The bestseller report should be display on the page")
    public void theBestsellerReportShouldBeDisplayOnThePage() {
        seeProductBestSellerReport.isProductBestSellersPageDisplayed();

    }

    @Given("reporting manager is on the Products Ordered page")
    public void reportingManagerIsOnTheProductsOrderedPage() {
         dashboardPage.navigateToProductsOrderedPage();
    }

    @When("reporting manager filter Products Ordered Report by date {string} and{string}")
    public void reportingManagerFilterProductsOrderedReportByDateAnd(String dateFrom, String dateTo) {
     productsOrderedPage.viewProductsOrderedReport(dateFrom,dateTo);
    }

    @Then("reporting manager should be able to see Products Ordered Report")
    public void reportingManagerShouldBeAbleToSeeProductsOrderedReport() {
         Assert.assertTrue(productsOrderedPage.verifyProductsOrderedReportDisplayed());
    }

    @When("reporting manager navigate to most viewed page data with {string} and{string}")
    public void reportingManagerNavigateToMostViewedPageDataWithAnd(String dateFrom, String dateTo) {
        productsMostViewedPage.mostViewedProductsReport(dateFrom,dateTo);
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
         Assert.assertFalse(invoicedPage.verifyReport());
    }

// abdugeni ********************can see coupons usage report

    @Given("The reporting manager opens coupons page")
    public void theReportingManagerOpensCouponsPage() {
         dashboardPage.navigateToCouponsPage();
    }

    @When("the coupons report should be displayed")
    public void theCouponsReportShouldBeDisplayed() {
         Assert.assertTrue(couponsPage.isSalesCouponsUsagePageDisplayed());
    }

    @And("the manager fills in the filter details{string} {string} {string}")
    public void theManagerFillsInTheFilterDetails(String arg0, String arg1, String arg2) {
        couponsPage.filterCouponsUsageReports(arg0,arg1,arg2);
    }

    @Then("no records found displayed")
    public void noRecordsFoundDisplayed() {
         Assert.assertTrue(couponsPage.verifyCouponsUsageReport());
    }

    //mihrigul
    @Given("reporting manager navigate to product in carts page")
    public void reportingManagerNavigateToProductInCartsPage() {
         productInCartPage.navigateToProductInCartsPage();
    }

    @When("open the product in cart page")
    public void openTheProductInCartPage() {
         productInCartPage.openProductInCartPage();
    }

    @Then("product in cart should be displayed")
    public void productInCartShouldBeDisplayed() {
         Assert.assertTrue(productInCartPage.verifyViewProductsInShoppingCartsReport());
    }

    @Given("reporting manager navigate to Customers Tags page")
    public void reportingManagerNavigateToCustomersTagsPage() {
         customersTagsPage.navigateToCustomersTagsPage();
    }

    @When("click on the show tags in the report of customer")
    public void clickOnTheShowTagsInTheReportOfCustomer() {
         customersTagsPage.clickTheShowTagsMethod();
    }

    @Then("show customers Tags successful")
    public void showCustomersTagsSuccessful() {
         Assert.assertTrue(customersTagsPage.verifyViewCustomersTagsReport()); }

// İhram
    @Given("reporting Manager Is Navigate To The AbandonedPage")
    public void reportingManagerIsNavigateToTheAbandonedPage() {reportViewPage.navigateToTheReport();
    }

    @When("Reporting Manager Navigate to The Shopping Cart And Choose Abandoned carts")
    public void reportingManagerNavigateToTheShoppingCartAndChooseAbandonedCarts() {
        reportViewPage.clickReportMethod("lakesha.schinner@hotmail.com");
    }


    @Then("Reporting Manager Should Get Report From AbandonedCart")
    public void reportingManagerShouldGetReportFromAbandonedCart() {
        Assert.assertTrue(reportViewPage.seeAbandonedCartsVerify());
    }

    @When("Reporting Manager see the best seller report")
    public void reportingManagerSeeTheBestSellerReport() {
    }

    @Then("Reporting Manager should see the best seller report")
    public void reportingManagerShouldSeeTheBestSellerReport() {
    }
}




