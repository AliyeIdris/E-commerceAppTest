package magentocucumber;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import magentocucumber.dashboardmodule.AdminDashboardPage;
import magentocucumber.universalfunctions.BasePage;
import magentocucumber.universalfunctions.DriverType;

import static magentocucumber.universalfunctions.FileUtility.readConfig;

/**
 * @author : user
 * @created : 8.12.2023,17:03
 * @Email :aliyeidiris@gmail.com
 **/
public class Hooks extends BasePage {
    @Before
    public void setUp(Scenario scenario) {
        if (scenario.getSourceTagNames().contains("@UITest")) {
            scenario.log("UI test started...");
            setUp(DriverType.CHROME_DRIVER, readConfig("backendUrl"));
        } else if (scenario.getSourceTagNames().contains("@DatabaseTest")) {
            scenario.log("Database test started...");
        }
    }
    @After("@UITest")
    public void tearDownBrowser(Scenario scenario) {
        scenario.log("UI test ended!");
        AdminDashboardPage dashboardPage = new AdminDashboardPage(driver);
        dashboardPage.logout();
        closeBrowser();
    }
    @After("@DatabaseTest")
    public void tearDownDatabase(Scenario scenario) {
        scenario.log("Database test ended!");
    }
}
