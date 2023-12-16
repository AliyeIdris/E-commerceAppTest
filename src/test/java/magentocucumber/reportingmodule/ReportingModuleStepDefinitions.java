package magentocucumber.reportingmodule;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import magentocucumber.dashboardmodule.AdminDashboardPage;
import magentocucumber.dashboardmodule.AdminLoginPage;
import magentocucumber.dashboardmodule.AdminRole;
import magentocucumber.universalfunctions.BasePage;
import org.junit.Assert;

public class ReportingModuleStepDefinitions extends BasePage {
    AdminLoginPage adminLoginPage =new AdminLoginPage(driver);
    AdminDashboardPage adminDashboardPage=new AdminDashboardPage(driver);
    ReportingDashboardPage dashboardPage=new ReportingDashboardPage(driver);
    ReportViewPage reportViewPage=new ReportViewPage(driver);
    DownloadsPage downloadsPage =new DownloadsPage(driver);
    ProductsReviewsPage reviewsPage=new ProductsReviewsPage(driver);
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


//    @After("@ReportingModule")
//    public void tearDown(){
//        closeBrowser();
//    }
}
