package magentocucumber.reportingmodule;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import magentocucumber.dashboardmodule.AdminLoginPage;
import magentocucumber.dashboardmodule.AdminRole;
import magentocucumber.universalfunctions.BasePage;

public class ReportingModuleStepDefinitions extends BasePage {
    AdminLoginPage adminLoginPage;
    ReportingDashboardPage dashboardPage;
    ReportViewPage reportViewPage;
    @Before
    public void setUp(){
        adminLoginPage=new AdminLoginPage(driver);
        adminLoginPage.login(AdminRole.REPORTINGMANAGER);
        dashboardPage=new ReportingDashboardPage(driver);
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
}
