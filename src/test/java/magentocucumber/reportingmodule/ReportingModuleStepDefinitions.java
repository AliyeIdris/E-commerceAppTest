package magentocucumber.reportingmodule;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import magentocucumber.dashboardmodule.AdminDashboardPage;
import magentocucumber.dashboardmodule.AdminLoginPage;
import magentocucumber.dashboardmodule.AdminRole;
import magentocucumber.universalfunctions.BasePage;
import org.junit.Assert;

public class ReportingModuleStepDefinitions extends BasePage {
    AdminLoginPage adminLoginPage;
    AdminDashboardPage adminDashboardPage;
    ReportingDashboardPage dashboardPage;
    ReportViewPage reportViewPage;
    DownloadsPage downloadsPage;
    ProductsReviewsPage reviewsPage;
    @Before
    public void setUp(){
        adminLoginPage=new AdminLoginPage(driver);
        adminLoginPage.login(AdminRole.REPORTINGMANAGER);
        adminDashboardPage=new AdminDashboardPage(driver);
        dashboardPage=new ReportingDashboardPage(driver);
        reportViewPage=new ReportViewPage(driver);
        downloadsPage=new DownloadsPage(driver);
        reportViewPage=new ReportViewPage(driver);

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
    @After("@ReportingModule")
    public void tearDown(){
        closeBrowser();
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
        Assert.assertTrue(adminDashboardPage.verifyReportingManagerDashboardPage());
    }

    @When("reporting manager opens the reviews page and sees the reviews")
    public void reportingManagerOpensTheReviewsPageAndSeesTheReviews() {
        reviewsPage.setProductsReviews();
    }

    @Then("products reviews should be see able")
    public void productsReviewsShouldBeSeeAble() {
        Assert.assertTrue(reviewsPage.verifyProductsReviews());
    }
}
